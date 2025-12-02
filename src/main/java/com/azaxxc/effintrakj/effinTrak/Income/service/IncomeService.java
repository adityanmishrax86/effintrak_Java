package com.azaxxc.effintrakj.effinTrak.Income.service;

import com.azaxxc.effintrakj.effinTrak.Category.dtos.CategoryResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.UpdateIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.repo.IncomeRepository;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.IncomeMapper;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers.PageResponseMapper;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import com.azaxxc.effintrakj.effinTrak.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final CategoryService categoryService;
    private final BankAccountService bankAccountService;
    private final IncomeMapper mapper;
    private final UserService userService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public IncomeService(IncomeRepository incomeRepository,
                         CategoryService categoryService,
                         BankAccountService bankAccountService,
                         UserService userService,
                         IncomeMapper mapper) {
        this.categoryService = categoryService;
        this.bankAccountService = bankAccountService;
        this.incomeRepository = incomeRepository;
        this.userService = userService;
        this.mapper = mapper;

    }

    public Income saveIncome(NewIncomeRequestDTO dto, User user) {

        if(dto.getAmount() <= 0) {
            throw new IllegalArgumentException("Income amount must be greater than zero.");
        } else if (dto.getDescription().isEmpty() || dto.getDate().isEmpty()) {
            throw new IllegalArgumentException("Please fill all the required fields.");
        }

        Category category = categoryService.getCategoryById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("No Category found with id:" + dto.getCategoryId()));
        BankAccount bankAccount = bankAccountService.getBankAccountById(dto.getBankAccountId())
                .orElseThrow(() -> new IllegalArgumentException("No Bank Account found with id:" + dto.getBankAccountId()));

        Income income = mapper.toIncome(dto);
        income.setUser(user);
        income.setCategory(category);
        income.setBankAccount(bankAccount);

        income.setDate(formatter.parse(dto.getDate(), java.time.LocalDate::from));

        return incomeRepository.save(income);
    }

    public Page<IncomeResponse> getIncomeByUserId(Long userId, Pageable pageable) {
        Page<Income> incomes = incomeRepository.findByUserIdOrderByDateDesc(userId, pageable);
        if(incomes.isEmpty()) {
            return Page.empty();
        }

        return PageResponseMapper.mapPageable(incomes, pageable, mapper::toIncomeResponse);

    }

    public Page<IncomeResponse> getIncomeByUserIdBetweenDatePeriods(Long userId, String startDate, String endDate, Pageable pageable) {

        LocalDate start = formatter.parse(startDate, LocalDate::from);
        LocalDate end = formatter.parse(endDate, LocalDate::from);

        Page<Income> incomes = incomeRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, start,end, pageable);
        if(incomes.isEmpty()) {
            return Page.empty();
        }

        return PageResponseMapper.mapPageable(incomes, pageable, mapper::toIncomeResponse);
    }

    public IncomeResponse updateIncomeDetail(Long incomeId,  UpdateIncomeRequestDTO dto) {
        User currentUser = userService.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("Invalid User ID Request"));
        Income currentIncome = incomeRepository.findByUserIdAndId(currentUser.getId(), incomeId).orElseThrow(() -> new RuntimeException("Invalid Income ID"));
        currentIncome.setAmount(dto.getAmount());
        currentIncome.setSource(dto.getSource());
        currentIncome.setNote(dto.getNote());
        currentIncome.setDescription(dto.getDescription());
        LocalDate date = formatter.parse(dto.getDate(), LocalDate::from);
        currentIncome.setDate(date);
        Category ctg = categoryService.getCategories().stream().filter(x -> Objects.equals(x.getId(), dto.getCategoryId()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException(("No Category found with id:" + dto.getCategoryId())));
        currentIncome.setCategory(ctg);

        try {
            incomeRepository.save(currentIncome);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Couldn't update the Income Transaction.");
        }


        return mapper.toIncomeResponse(currentIncome);
    }

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }

}
