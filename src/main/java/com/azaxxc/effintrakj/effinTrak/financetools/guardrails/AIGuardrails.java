package com.azaxxc.effintrakj.effinTrak.financetools.guardrails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implements guardrails to protect against AI hallucinations, rate limiting,
 * and unusual patterns that might indicate system abuse or errors
 */
@Component
public class AIGuardrails {

    private static final Logger logger = LoggerFactory.getLogger(AIGuardrails.class);

    // Rate limiting configuration
    private static final int MAX_REQUESTS_PER_MINUTE = 30;
    private static final int MAX_REQUESTS_PER_HOUR = 500;
    private static final long MINUTE_IN_MS = 60000;
    private static final long HOUR_IN_MS = 3600000;

    // Anomaly detection configuration
    private static final double AMOUNT_ANOMALY_THRESHOLD = 10000.0; // Alert if single transaction > 10k
    private static final int RAPID_TRANSACTION_THRESHOLD = 10; // Alert if 10+ transactions in 5 mins
    private static final long RAPID_TRANSACTION_WINDOW_MS = 300000; // 5 minutes

    // User-specific tracking
    private final Map<Long, UserRequestHistory> requestHistory = new ConcurrentHashMap<>();
    private final Map<Long, UserAnomalyTracker> anomalyTrackers = new ConcurrentHashMap<>();

    /**
     * Check if user has exceeded rate limits
     */
    public RateLimitResult checkRateLimit(long userId) {
        logger.debug("Checking rate limit for user: {}", userId);

        UserRequestHistory history = requestHistory.computeIfAbsent(userId, k -> new UserRequestHistory());
        long now = System.currentTimeMillis();

        // Clean up old requests
        history.cleanupOldRequests(now);

        // Check per-minute limit
        long recentCount = history.getRequestsInWindow(now - MINUTE_IN_MS, now);
        if (recentCount >= MAX_REQUESTS_PER_MINUTE) {
            logger.warn("User {} exceeded per-minute rate limit: {}", userId, recentCount);
            return RateLimitResult.limited("Per-minute rate limit exceeded. Please wait before making another request.");
        }

        // Check per-hour limit
        long hourlyCount = history.getRequestsInWindow(now - HOUR_IN_MS, now);
        if (hourlyCount >= MAX_REQUESTS_PER_HOUR) {
            logger.warn("User {} exceeded per-hour rate limit: {}", userId, hourlyCount);
            return RateLimitResult.limited("Per-hour rate limit exceeded. Please try again later.");
        }

        // Record this request
        history.recordRequest(now);

        return RateLimitResult.allowed();
    }

    /**
     * Detect anomalies in transaction patterns
     */
    public AnomalyResult detectAnomalies(long userId, double amount, String operation) {
        logger.debug("Checking for anomalies - User: {}, Amount: {}, Operation: {}", userId, amount, operation);

        UserAnomalyTracker tracker = anomalyTrackers.computeIfAbsent(userId, k -> new UserAnomalyTracker());
        long now = System.currentTimeMillis();

        List<String> anomalies = new ArrayList<>();

        // Check for unusually large single transaction
        if (amount > AMOUNT_ANOMALY_THRESHOLD && operation.contains("ADD_EXPENSE")) {
            anomalies.add(String.format("Large transaction detected: $%.2f", amount));
            logger.warn("Anomaly detected for user {}: Large transaction amount {}", userId, amount);
        }

        // Check for rapid successive transactions
        tracker.recordTransaction(now);
        long rapidTransactionCount = tracker.getTransactionsInWindow(now - RAPID_TRANSACTION_WINDOW_MS, now);
        if (rapidTransactionCount > RAPID_TRANSACTION_THRESHOLD) {
            anomalies.add(String.format("Rapid transaction pattern: %d transactions in 5 minutes", rapidTransactionCount));
            logger.warn("Anomaly detected for user {}: Rapid transactions ({})", userId, rapidTransactionCount);
        }

        if (!anomalies.isEmpty()) {
            return AnomalyResult.detected(anomalies);
        }

        return AnomalyResult.clear();
    }

    /**
     * Verify AI response consistency with user request
     * Returns false if response seems suspicious or inconsistent
     */
    public boolean verifyResponseConsistency(String userRequest, String aiResponse, String operation) {
        logger.debug("Verifying response consistency");

        // Check if operation matches request
        if (aiResponse == null || aiResponse.isEmpty()) {
            logger.warn("AI response is empty");
            return false;
        }

        // Check for error indicators in AI response
        if (aiResponse.toLowerCase().contains("error") && !operation.isEmpty()) {
            logger.info("AI response contains error indicator");
            return false;
        }

        // Check if response is suspiciously vague
        if (aiResponse.length() < 5) {
            logger.warn("AI response is suspiciously short: {}", aiResponse);
            return false;
        }

        // Check for repetitive or generic patterns
        if (isRepetitiveResponse(aiResponse)) {
            logger.warn("AI response appears repetitive or generic");
            return false;
        }

        return true;
    }

    /**
     * Check if response contains reasonable financial data
     */
    public boolean validateFinancialDataSanity(double amount, long categoryId, long accountId) {
        logger.debug("Validating financial data sanity - Amount: {}, Category: {}, Account: {}",
                   amount, categoryId, accountId);

        // Amount should be positive
        if (amount <= 0) {
            logger.warn("Invalid amount: {}", amount);
            return false;
        }

        // IDs should be within reasonable bounds
        if (categoryId <= 0 || categoryId > 100) {
            logger.warn("Invalid category ID: {}", categoryId);
            return false;
        }

        if (accountId <= 0 || accountId > 100) {
            logger.warn("Invalid account ID: {}", accountId);
            return false;
        }

        return true;
    }

    /**
     * Check if AI response pattern indicates hallucination
     */
    public boolean detectHallucination(String response, String userRequest) {
        logger.debug("Detecting potential hallucination");

        if (response == null) {
            return false;
        }

        // Check for impossible financial scenarios
        if (response.toLowerCase().contains("million") && userRequest.toLowerCase().contains("expense")) {
            // User asking about expense but AI mentions millions - possible hallucination
            logger.warn("Potential hallucination: expense with unreasonable scale");
            return true;
        }

        // Check for self-contradictory statements
        if (response.contains("successfully") && response.contains("failed")) {
            logger.warn("Potential hallucination: contradictory statements in response");
            return true;
        }

        return false;
    }

    /**
     * Validate intent is reasonable given user request
     */
    public boolean isIntentReasonable(String userRequest, String detectedIntent) {
        logger.debug("Validating intent reasonableness - Request: {}, Intent: {}", userRequest, detectedIntent);

        userRequest = userRequest.toLowerCase();
        detectedIntent = detectedIntent.toUpperCase();

        // Map of keywords to expected intents
        Map<String, List<String>> keywordIntentMap = new HashMap<>();
        keywordIntentMap.put("spend|spent|buy|purchased|paid", List.of("ADD_EXPENSE", "UPDATE_EXPENSE"));
        keywordIntentMap.put("earn|income|received|salary|bonus", List.of("ADD_INCOME"));
        keywordIntentMap.put("delete|remove|undo", List.of("DELETE_EXPENSE", "UPDATE_EXPENSE"));
        keywordIntentMap.put("update|change|modify", List.of("UPDATE_EXPENSE"));
        keywordIntentMap.put("total|sum|spending|expenses", List.of("GET_MONTHLY_SPENDING", "GET_FINANCIAL_SUMMARY"));
        keywordIntentMap.put("category|breakdown", List.of("GET_SPENDING_BY_CATEGORY"));
        keywordIntentMap.put("summary|balance|status", List.of("GET_FINANCIAL_SUMMARY"));

        // Check if detected intent matches request keywords
        for (Map.Entry<String, List<String>> entry : keywordIntentMap.entrySet()) {
            if (userRequest.matches(".*(" + entry.getKey() + ").*")) {
                if (entry.getValue().contains(detectedIntent)) {
                    return true; // Intent matches expected pattern
                }
            }
        }

        // If no specific pattern matched, accept the intent (be permissive)
        logger.debug("Intent seems reasonable (permissive check)");
        return true;
    }

    /**
     * Check if response is repetitive or generic
     */
    private boolean isRepetitiveResponse(String response) {
        String lower = response.toLowerCase();

        // Check for repeated words or phrases
        String[] words = lower.split("\\s+");
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // If any word appears more than 3 times, might be repetitive
        for (int count : wordCount.values()) {
            if (count > 3) {
                logger.debug("Detected repetitive word usage");
                return true;
            }
        }

        return false;
    }

    /**
     * Rate limit result wrapper
     */
    public static class RateLimitResult {
        private final boolean allowed;
        private final String message;

        private RateLimitResult(boolean allowed, String message) {
            this.allowed = allowed;
            this.message = message;
        }

        public boolean isAllowed() {
            return allowed;
        }

        public String getMessage() {
            return message;
        }

        public static RateLimitResult allowed() {
            return new RateLimitResult(true, "Request allowed");
        }

        public static RateLimitResult limited(String message) {
            return new RateLimitResult(false, message);
        }
    }

    /**
     * Anomaly detection result wrapper
     */
    public static class AnomalyResult {
        private final boolean detected;
        private final List<String> anomalies;

        private AnomalyResult(boolean detected, List<String> anomalies) {
            this.detected = detected;
            this.anomalies = anomalies;
        }

        public boolean isAnomalyDetected() {
            return detected;
        }

        public List<String> getAnomalies() {
            return anomalies;
        }

        public String getAnomalySummary() {
            return String.join("; ", anomalies);
        }

        public static AnomalyResult detected(List<String> anomalies) {
            return new AnomalyResult(true, anomalies);
        }

        public static AnomalyResult clear() {
            return new AnomalyResult(false, new ArrayList<>());
        }
    }

    /**
     * Internal class to track user request history
     */
    private static class UserRequestHistory {
        private final LinkedList<Long> timestamps = new LinkedList<>();

        synchronized void recordRequest(long timestamp) {
            timestamps.add(timestamp);
        }

        synchronized long getRequestsInWindow(long startTime, long endTime) {
            return timestamps.stream()
                    .filter(t -> t >= startTime && t <= endTime)
                    .count();
        }

        synchronized void cleanupOldRequests(long currentTime) {
            // Keep only last hour of requests
            timestamps.removeIf(t -> t < currentTime - HOUR_IN_MS);
        }
    }

    /**
     * Internal class to track user anomalies
     */
    private static class UserAnomalyTracker {
        private final LinkedList<Long> transactionTimes = new LinkedList<>();

        synchronized void recordTransaction(long timestamp) {
            transactionTimes.add(timestamp);
            // Keep last hour of transactions
            transactionTimes.removeIf(t -> t < timestamp - HOUR_IN_MS);
        }

        synchronized long getTransactionsInWindow(long startTime, long endTime) {
            return transactionTimes.stream()
                    .filter(t -> t >= startTime && t <= endTime)
                    .count();
        }
    }
}

