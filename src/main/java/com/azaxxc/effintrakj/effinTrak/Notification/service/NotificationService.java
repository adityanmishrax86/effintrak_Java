package com.azaxxc.effintrakj.effinTrak.Notification.service;

import com.azaxxc.effintrakj.effinTrak.Budget.model.Budget;
import com.azaxxc.effintrakj.effinTrak.Budget.repo.BudgetRepository;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Expense.repo.ExpenseRepository;
import com.azaxxc.effintrakj.effinTrak.Notification.dtos.NotificationPreferencesDTO;
import com.azaxxc.effintrakj.effinTrak.Notification.dtos.NotificationResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Notification.model.Notification;
import com.azaxxc.effintrakj.effinTrak.Notification.model.NotificationPreferences;
import com.azaxxc.effintrakj.effinTrak.Notification.repo.NotificationPreferencesRepository;
import com.azaxxc.effintrakj.effinTrak.Notification.repo.NotificationRepository;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationPreferencesRepository preferencesRepository;
    private final UserService userService;

    public NotificationService(BudgetRepository budgetRepository,
                               ExpenseRepository expenseRepository,
                               NotificationRepository notificationRepository,
                               NotificationPreferencesRepository preferencesRepository,
                               UserService userService) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
        this.notificationRepository = notificationRepository;
        this.preferencesRepository = preferencesRepository;
        this.userService = userService;
    }

    public void checkBudgetExceeded(Long userId) {
        Optional<NotificationPreferences> prefsOpt = preferencesRepository.findByUserId(userId);
        if (prefsOpt.isPresent() && !prefsOpt.get().getBudgetAlerts()) {
            return; // User has disabled budget alerts
        }

        List<Budget> budgets = budgetRepository.findByUserId(userId);
        LocalDate now = LocalDate.now();

        for (Budget budget : budgets) {
            if (now.isAfter(budget.getStartDate()) && now.isBefore(budget.getEndDate())) {
                Double totalExpense = 0.0;
                if (budget.getCategory() != null) {
                    List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId,
                            budget.getStartDate(), budget.getEndDate());
                    totalExpense = expenses.stream()
                            .filter(e -> e.getCategory() != null && e.getCategory().getId().equals(budget.getCategory().getId()))
                            .mapToDouble(Expense::getAmount).sum();
                } else {
                    List<Expense> expenses = expenseRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId,
                            budget.getStartDate(), budget.getEndDate());
                    totalExpense = expenses.stream().mapToDouble(Expense::getAmount).sum();
                }

                if (totalExpense > budget.getAmount()) {
                    createNotification(userId, "Budget Exceeded for "
                            + (budget.getCategory() != null ? budget.getCategory().getName() : "Overall"),
                            "BUDGET_EXCEEDED");
                } else if (totalExpense > (budget.getAmount() * (budget.getAlertThreshold() / 100))) {
                    createNotification(userId,
                            "Budget Alert: You have reached " + budget.getAlertThreshold() + "% of your "
                                    + (budget.getCategory() != null ? budget.getCategory().getName() : "Overall")
                                    + " budget.",
                            "BUDGET_EXCEEDED");
                }
            }
        }
    }

    @Transactional
    public void createNotification(Long userId, String message, String type) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setType(type);
        notification.setIsRead(false);
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
    }

    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return notifications.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<NotificationResponseDTO> getUnreadNotificationsByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserIdAndIsReadFalseOrderByCreatedAtDesc(userId);
        return notifications.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    @Transactional
    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }

    public NotificationPreferencesDTO getPreferences(Long userId) {
        Optional<NotificationPreferences> prefsOpt = preferencesRepository.findByUserId(userId);
        if (prefsOpt.isPresent()) {
            NotificationPreferences prefs = prefsOpt.get();
            NotificationPreferencesDTO dto = new NotificationPreferencesDTO();
            dto.setBudgetAlerts(prefs.getBudgetAlerts());
            dto.setBillReminders(prefs.getBillReminders());
            dto.setSubscriptionRenewals(prefs.getSubscriptionRenewals());
            dto.setGoalAchievements(prefs.getGoalAchievements());
            dto.setLowBalanceAlerts(prefs.getLowBalanceAlerts());
            dto.setUnusualSpendingAlerts(prefs.getUnusualSpendingAlerts());
            dto.setEmailNotifications(prefs.getEmailNotifications());
            dto.setPushNotifications(prefs.getPushNotifications());
            return dto;
        }
        // Return default preferences
        NotificationPreferencesDTO dto = new NotificationPreferencesDTO();
        dto.setBudgetAlerts(true);
        dto.setBillReminders(true);
        dto.setSubscriptionRenewals(true);
        dto.setGoalAchievements(true);
        dto.setLowBalanceAlerts(true);
        dto.setUnusualSpendingAlerts(true);
        dto.setEmailNotifications(false);
        dto.setPushNotifications(false);
        return dto;
    }

    @Transactional
    public NotificationPreferencesDTO updatePreferences(Long userId, NotificationPreferencesDTO dto) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        NotificationPreferences prefs = preferencesRepository.findByUser(user)
                .orElse(new NotificationPreferences());

        prefs.setUser(user);
        if (dto.getBudgetAlerts() != null) prefs.setBudgetAlerts(dto.getBudgetAlerts());
        if (dto.getBillReminders() != null) prefs.setBillReminders(dto.getBillReminders());
        if (dto.getSubscriptionRenewals() != null) prefs.setSubscriptionRenewals(dto.getSubscriptionRenewals());
        if (dto.getGoalAchievements() != null) prefs.setGoalAchievements(dto.getGoalAchievements());
        if (dto.getLowBalanceAlerts() != null) prefs.setLowBalanceAlerts(dto.getLowBalanceAlerts());
        if (dto.getUnusualSpendingAlerts() != null) prefs.setUnusualSpendingAlerts(dto.getUnusualSpendingAlerts());
        if (dto.getEmailNotifications() != null) prefs.setEmailNotifications(dto.getEmailNotifications());
        if (dto.getPushNotifications() != null) prefs.setPushNotifications(dto.getPushNotifications());

        preferencesRepository.save(prefs);

        NotificationPreferencesDTO response = new NotificationPreferencesDTO();
        response.setBudgetAlerts(prefs.getBudgetAlerts());
        response.setBillReminders(prefs.getBillReminders());
        response.setSubscriptionRenewals(prefs.getSubscriptionRenewals());
        response.setGoalAchievements(prefs.getGoalAchievements());
        response.setLowBalanceAlerts(prefs.getLowBalanceAlerts());
        response.setUnusualSpendingAlerts(prefs.getUnusualSpendingAlerts());
        response.setEmailNotifications(prefs.getEmailNotifications());
        response.setPushNotifications(prefs.getPushNotifications());
        return response;
    }

    public Long getUnreadCount(Long userId) {
        return notificationRepository.countByUserIdAndIsReadFalse(userId);
    }

    private NotificationResponseDTO mapToDTO(Notification notification) {
        return new NotificationResponseDTO(
                notification.getId(),
                notification.getMessage(),
                notification.getType(),
                notification.getIsRead(),
                notification.getCreatedAt()
        );
    }
}
