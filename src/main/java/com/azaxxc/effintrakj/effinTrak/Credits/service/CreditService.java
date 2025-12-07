package com.azaxxc.effintrakj.effinTrak.Credits.service;

import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.UpdateCreditRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.model.Credit;
import com.azaxxc.effintrakj.effinTrak.Credits.repo.CreditRepository;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.CreditMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditService {
    private final CreditRepository creditRepository;
    private final UserService userService;
    private final BankAccountService bankAccountService;
    private final CreditMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public CreditService(CreditRepository creditRepository,
                         UserService userService,
                         BankAccountService bankAccountService,
                         CreditMapper mapper) {
        this.creditRepository = creditRepository;
        this.userService = userService;
        this.bankAccountService = bankAccountService;
        this.mapper = mapper;
    }

    public CreditResponseDTO saveCredit(CreditRequestDTO dto, User user) {
        if (dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Credit amount must be greater than zero.");
        }
        if (dto.getDescription() == null || dto.getDescription().isEmpty() || dto.getDueDate() == null || dto.getDueDate().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields.");
        }

        Credit credit = mapper.toCredit(dto);
        credit.setUser(user);

        if (dto.getCreditorId() != null) {
            BankAccount creditor = bankAccountService.getBankAccountById(dto.getCreditorId())
                    .orElseThrow(() -> new IllegalArgumentException("No Bank Account found with id: " + dto.getCreditorId()));
            credit.setCreditor(creditor);
        }

        try {
            LocalDate localDate = LocalDate.parse(dto.getDueDate(), formatter);
            credit.setDueDate(Date.valueOf(localDate));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
        }

        Credit savedCredit = creditRepository.save(credit);
        return mapper.toCreditResponse(savedCredit);
    }

    public List<CreditResponseDTO> getCreditsByUserId(Long userId) {
        List<Credit> credits = creditRepository.findByUserId(userId);
        return credits.stream()
                .map(mapper::toCreditResponse)
                .collect(Collectors.toList());
    }

    public List<CreditResponseDTO> getCreditsByUserIdBetweenDates(Long userId, String startDate, String endDate) {
        try {
            LocalDate startLocal = LocalDate.parse(startDate, formatter);
            LocalDate endLocal = LocalDate.parse(endDate, formatter);
            Date start = Date.valueOf(startLocal);
            Date end = Date.valueOf(endLocal);
            List<Credit> credits = creditRepository.findByUserIdAndDueDateBetween(userId, start, end);
            return credits.stream()
                    .map(mapper::toCreditResponse)
                    .collect(Collectors.toList());
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
        }
    }

    public CreditResponseDTO updateCredit(Long id, UpdateCreditRequestDTO dto) {
        Credit currentCredit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));

        if (dto.getDescription() != null) {
            currentCredit.setDescription(dto.getDescription());
        }
        if (dto.getAmount() != null) {
            if (dto.getAmount() <= 0) {
                throw new IllegalArgumentException("Credit amount must be greater than zero.");
            }
            currentCredit.setAmount(dto.getAmount());
        }
        if (dto.getDueDate() != null) {
            try {
                LocalDate localDate = LocalDate.parse(dto.getDueDate(), formatter);
                currentCredit.setDueDate(Date.valueOf(localDate));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
            }
        }
        if (dto.getCreditorId() != null) {
            BankAccount creditor = bankAccountService.getBankAccountById(dto.getCreditorId())
                    .orElseThrow(() -> new IllegalArgumentException("No Bank Account found with id: " + dto.getCreditorId()));
            currentCredit.setCreditor(creditor);
        }
        if (dto.getType() != null) {
            currentCredit.setType(dto.getType());
        }
        if (dto.getInterestRate() != null) {
            currentCredit.setInterestRate(dto.getInterestRate());
        }
        if (dto.getPaymentMethod() != null) {
            currentCredit.setPaymentMethod(dto.getPaymentMethod());
        }
        if (dto.getPaid() != null) {
            currentCredit.setPaid(dto.getPaid());
        }

        try {
            Credit updatedCredit = creditRepository.save(currentCredit);
            return mapper.toCreditResponse(updatedCredit);
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't update the credit transaction.");
        }
    }

    public Optional<Credit> getCreditById(Long id) {
        return creditRepository.findById(id);
    }

    public CreditResponseDTO getCreditResponseById(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
        return mapper.toCreditResponse(credit);
    }

    public List<CreditResponseDTO> getUpcomingCredits(Long userId, Integer days) {
        LocalDate now = LocalDate.now();
        LocalDate futureDate = now.plusDays(days != null ? days : 30);
        
        Date startDate = Date.valueOf(now);
        Date endDate = Date.valueOf(futureDate);
        
        List<Credit> credits = creditRepository.findByUserIdAndDueDateBetween(userId, startDate, endDate);
        return credits.stream()
                .filter(credit -> !credit.getPaid())
                .map(mapper::toCreditResponse)
                .collect(Collectors.toList());
    }

    public List<CreditResponseDTO> getOverdueCredits(Long userId) {
        LocalDate now = LocalDate.now();
        Date today = Date.valueOf(now);
        
        List<Credit> allCredits = creditRepository.findByUserId(userId);
        return allCredits.stream()
                .filter(credit -> !credit.getPaid() && credit.getDueDate().before(today))
                .map(mapper::toCreditResponse)
                .collect(Collectors.toList());
    }

    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }
}

