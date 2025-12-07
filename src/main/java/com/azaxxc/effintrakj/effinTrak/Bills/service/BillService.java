package com.azaxxc.effintrakj.effinTrak.Bills.service;

import com.azaxxc.effintrakj.effinTrak.Bills.dtos.BillDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.model.Credit;
import com.azaxxc.effintrakj.effinTrak.Credits.repo.CreditRepository;
import com.azaxxc.effintrakj.effinTrak.Subscription.model.Subscription;
import com.azaxxc.effintrakj.effinTrak.Subscription.repo.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BillService {

    private final CreditRepository creditRepository;
    private final SubscriptionRepository subscriptionRepository;

    public BillService(CreditRepository creditRepository, SubscriptionRepository subscriptionRepository) {
        this.creditRepository = creditRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<BillDTO> getOverdueBills(Long userId) {
        LocalDate now = LocalDate.now();
        List<BillDTO> bills = new ArrayList<>();

        // Get overdue credits
        List<Credit> credits = creditRepository.findByUserId(userId);
        credits.stream()
                .filter(credit -> !credit.getPaid())
                .forEach(credit -> {
                    LocalDate dueDate = credit.getDueDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    if (dueDate.isBefore(now)) {
                        long daysOverdue = ChronoUnit.DAYS.between(dueDate, now);
                        bills.add(new BillDTO(
                                credit.getId(),
                                "CREDIT",
                                credit.getDescription(),
                                credit.getType(),
                                credit.getAmount(),
                                dueDate.toString(),
                                (int) -daysOverdue,
                                true
                        ));
                    }
                });

        // Get overdue subscriptions (past end date but still active)
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        subscriptions.stream()
                .filter(sub -> sub.getIsActive() && sub.getEndDate() != null)
                .forEach(sub -> {
                    LocalDate endDate = sub.getEndDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    if (endDate.isBefore(now)) {
                        long daysOverdue = ChronoUnit.DAYS.between(endDate, now);
                        bills.add(new BillDTO(
                                sub.getId(),
                                "SUBSCRIPTION",
                                sub.getDescription(),
                                sub.getName(),
                                sub.getPrice(),
                                endDate.toString(),
                                (int) -daysOverdue,
                                true
                        ));
                    }
                });

        bills.sort(Comparator.comparing(BillDTO::getDaysUntilDue));
        return bills;
    }
}

