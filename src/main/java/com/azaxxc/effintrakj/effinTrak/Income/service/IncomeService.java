package com.azaxxc.effintrakj.effinTrak.Income.service;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Category.service.CategoryService;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.AllIncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.Income.repo.IncomeRepository;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.accounts.service.BankAccountService;
import com.azaxxc.effintrakj.effinTrak.globalcomponents.GlobalResponseService;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    private final CategoryService categoryService;
    private final BankAccountService bankAccountService;


    @Autowired
    public IncomeService(IncomeRepository incomeRepository, CategoryService categoryService, BankAccountService bankAccountService, GlobalResponseService globalResponseService) {
        this.categoryService = categoryService;
        this.bankAccountService = bankAccountService;
        this.incomeRepository = incomeRepository;

    }

    public Income saveIncome(NewIncomeRequestDTO dto, User user) {

        Category category = categoryService.getCategoryById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("No Category found with id:" + dto.getCategoryId()));
        BankAccount bankAccount = bankAccountService.getBankAccountById(dto.getBankAccountId())
                .orElseThrow(() -> new IllegalArgumentException("No Bank Account found with id:" + dto.getBankAccountId()));

        Income income = new Income();
        income.setUser(user);
        income.setCategory(category);
        income.setBankAccount(bankAccount);
        income.setAmount(dto.getAmount());
        income.setDescription(dto.getDescription());
        income.setDate(new Date());
        income.setNote(dto.getNote());
        income.setSource(dto.getSource());
        income.setAmount(dto.getAmount());

        return incomeRepository.save(income);
    }

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Page<Income> getIncomeByUserId(Long userId, Date startDate, Date endDate, Pageable pageable) {
        return incomeRepository.findByUserIdAndDateBetweenOrderByDateDesc(userId, startDate, endDate, pageable);
    }

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}
