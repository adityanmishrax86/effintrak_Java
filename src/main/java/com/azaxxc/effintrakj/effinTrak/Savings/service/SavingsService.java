package com.azaxxc.effintrakj.effinTrak.Savings.service;

import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.UpdateSavingsRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.model.Savings;
import com.azaxxc.effintrakj.effinTrak.Savings.repo.SavingsRepository;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.SavingsMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SavingsService {
    private final SavingsRepository savingsRepository;
    private final UserService userService;
    private final SavingsMapper mapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public SavingsService(SavingsRepository savingsRepository,
                         UserService userService,
                         SavingsMapper mapper) {
        this.savingsRepository = savingsRepository;
        this.userService = userService;
        this.mapper = mapper;
    }

    public SavingsResponseDTO saveSavings(SavingsRequestDTO dto, User user) {
        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (dto.getBalance() != null && dto.getBalance() < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }

        Savings savings = mapper.toSavings(dto);
        savings.setUser(user);

        if (dto.getTargetDate() != null && !dto.getTargetDate().isEmpty()) {
            try {
                LocalDate localDate = LocalDate.parse(dto.getTargetDate(), formatter);
                savings.setTargetDate(Date.valueOf(localDate));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
            }
        }

        Savings savedSavings = savingsRepository.save(savings);
        return mapper.toSavingsResponse(savedSavings);
    }

    public List<SavingsResponseDTO> getSavingsByUserId(Long userId) {
        List<Savings> savings = savingsRepository.findByUserId(userId);
        return savings.stream()
                .map(mapper::toSavingsResponse)
                .collect(Collectors.toList());
    }

    public SavingsResponseDTO updateSavings(Long id, UpdateSavingsRequestDTO dto) {
        Savings currentSavings = savingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Savings not found with id: " + id));

        if (dto.getName() != null) {
            currentSavings.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            currentSavings.setDescription(dto.getDescription());
        }
        if (dto.getBalance() != null) {
            if (dto.getBalance() < 0) {
                throw new IllegalArgumentException("Balance cannot be negative.");
            }
            currentSavings.setBalance(dto.getBalance());
        }
        if (dto.getTargetAmount() != null) {
            currentSavings.setTargetAmount(dto.getTargetAmount());
        }
        if (dto.getTargetDate() != null && !dto.getTargetDate().isEmpty()) {
            try {
                LocalDate localDate = LocalDate.parse(dto.getTargetDate(), formatter);
                currentSavings.setTargetDate(Date.valueOf(localDate));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
            }
        }
        if (dto.getDepositFrequency() != null) {
            currentSavings.setDepositFrequency(dto.getDepositFrequency());
        }

        try {
            Savings updatedSavings = savingsRepository.save(currentSavings);
            return mapper.toSavingsResponse(updatedSavings);
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't update the savings.");
        }
    }

    public Optional<Savings> getSavingsById(Long id) {
        return savingsRepository.findById(id);
    }

    public SavingsResponseDTO getSavingsResponseById(Long id) {
        Savings savings = savingsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Savings not found with id: " + id));
        return mapper.toSavingsResponse(savings);
    }

    public void deleteSavings(Long id) {
        savingsRepository.deleteById(id);
    }
}

