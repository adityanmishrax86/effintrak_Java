package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.ExpenseResponse;
import com.azaxxc.effintrakj.effinTrak.Expense.dtos.NewExpenseRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Expense.model.Expense;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.IncomeResponse;
import com.azaxxc.effintrakj.effinTrak.Income.dtos.NewIncomeRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Income.model.Income;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseMapper INSTANCE  = Mappers.getMapper(ExpenseMapper.class);

    @Mapping(target = "category", source="category", qualifiedByName = "mapCategoryObject")
    @Mapping(target = "bankAccount", source="bankAccount", qualifiedByName = "mapBankAccountObject")
    ExpenseResponse toExpenseResponse(Expense expense);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "bankAccount", ignore = true)
    @Mapping(target = "date", ignore = true)
    Expense toExpense(NewExpenseRequestDTO dto);

    @Named("mapCategoryObject")
    default String mapCategoryObject(Category category) {
        return null != category ? category.getName() : "";
    }

    @Named("mapBankAccountObject")
    default String mapBankAccountObject(BankAccount bankAccount) {
        return null != bankAccount ? bankAccount.getName() : "";
    }
}
