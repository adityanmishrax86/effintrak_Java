package com.azaxxc.effintrakj.effinTrak.financetools.services;

import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.repo.BankAccountRepository;
import com.azaxxc.effintrakj.effinTrak.usersettings.dto.UserSettingsResponse;
import com.azaxxc.effintrakj.effinTrak.usersettings.service.UserSettingsService;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AIContextService {
    private static final Logger logger = LoggerFactory.getLogger(AIContextService.class);

    private final CategoryService categoryService;
    private final BankAccountRepository bankAccountRepository;
    private final UserSettingsService userSettingsService;

    public AIContextService(CategoryService categoryService, BankAccountRepository bankAccountRepository,
                            UserSettingsService userSettingsService) {
        this.categoryService = categoryService;
        this.bankAccountRepository = bankAccountRepository;
        this.userSettingsService = userSettingsService;
    }

    /**
     * Build AI context with all categories and bank accounts for a user
     * This context is passed to the AI so it can map natural language to IDs
     */
    public String buildUserContext(Long userId) {
        logger.debug("Building AI context for userId: {}", userId);

        StringBuilder context = new StringBuilder();
        context.append("\n=== USER FINANCIAL CONTEXT ===\n");
        addUserPreferenceContext(context, userId);

        // Add categories
        context.append("AVAILABLE CATEGORIES:\n");
        List<Category> categories = categoryService.getCategories();
        if (categories.isEmpty()) {
            context.append("- No categories available\n");
        } else {
            for (Category category : categories) {
                context.append(String.format("- ID: %d, Name: %s\n", category.getId(), category.getName()));
            }
        }

        // Add bank accounts for user
        context.append("\nUSER'S BANK ACCOUNTS:\n");
        try {
            List<BankAccount> accounts = bankAccountRepository.findByuser_id(userId);
            if (accounts.isEmpty()) {
                context.append("- No bank accounts available\n");
            } else {
                for (BankAccount account : accounts) {
                    context.append(String.format("- ID: %d, Name: %s, Balance: $%.2f\n",
                        account.getId(), account.getName(), account.getBalance()));
                }
            }
        } catch (Exception e) {
            logger.warn("Could not load bank accounts: {}", e.getMessage());
            context.append("- Error loading bank accounts\n");
        }

        context.append("\n=== INSTRUCTIONS FOR AI ===\n");
        context.append("When the user mentions:\n");
        context.append("1. A category name (e.g., 'groceries', 'entertainment', 'bills') - find the matching category ID from above\n");
        context.append("2. A bank account name (e.g., 'credit card', 'checking', 'savings') - find the matching account ID\n");
        context.append("3. Payment method descriptions (e.g., 'my credit card', 'checking account') - match to available accounts\n");
        context.append("Use the IDs provided above. If no exact match, choose the most similar one.\n");

        logger.debug("AI context built successfully");
        return context.toString();
    }

    private void addUserPreferenceContext(StringBuilder context, Long userId) {
        UserSettingsResponse settings = userSettingsService.getEffectiveSettings(userId);
        context.append("USER PREFERENCES:\n");
        context.append(String.format("- Currency: %s%n", settings.getCurrencyCode()));
        context.append(String.format("- Locale: %s%n", settings.getLocale()));
        context.append(String.format("- Time Zone: %s%n", settings.getTimeZone()));
        context.append(String.format("- Date Format: %s%n", settings.getDateFormat()));
        context.append(String.format("- Week Starts On: %s%n", settings.getWeekStartsOn()));
        context.append(String.format("- AI Persona: %s%n", settings.getAiPersona()));
        context.append(String.format("- Include Category Hints: %s%n", settings.isIncludeCategoryHints()));
        context.append(String.format("- Include Proactive Insights: %s%n%n", settings.isIncludeProactiveInsights()));
    }

    /**
     * Get category context for AI in a simple format
     */
    public String getCategoryContext() {
        List<Category> categories = categoryService.getCategories();
        return categories.stream()
                .map(c -> String.format("%d=%s", c.getId(), c.getName()))
                .collect(Collectors.joining(", "));
    }

    /**
     * Get bank account context for user in a simple format
     */
    public String getBankAccountContext(Long userId) {
        List<BankAccount> accounts = bankAccountRepository.findByuser_id(userId);
        return accounts.stream()
                .map(a -> String.format("%d=%s", a.getId(), a.getName()))
                .collect(Collectors.joining(", "));
    }
}
