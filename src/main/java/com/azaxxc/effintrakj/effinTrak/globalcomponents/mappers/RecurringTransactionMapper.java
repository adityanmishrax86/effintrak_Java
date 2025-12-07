package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.dtos.RecurringTransactionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.RecurringTransaction.model.RecurringTransaction;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecurringTransactionMapper {

    RecurringTransactionMapper INSTANCE = Mappers.getMapper(RecurringTransactionMapper.class);

    @Mapping(target = "categoryName", source = "category", qualifiedByName = "mapCategoryObject")
    @Mapping(target = "bankAccountName", source = "bankAccount", qualifiedByName = "mapBankAccountObject")
    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "nextDueDate", source = "nextDueDate", dateFormat = "yyyy-MM-dd")
    RecurringTransactionResponseDTO toRecurringTransactionResponse(RecurringTransaction recurringTransaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "bankAccount", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "nextDueDate", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    RecurringTransaction toRecurringTransaction(RecurringTransactionRequestDTO dto);

    @Named("mapCategoryObject")
    default String mapCategoryObject(Category category) {
        return null != category ? category.getName() : "";
    }

    @Named("mapBankAccountObject")
    default String mapBankAccountObject(BankAccount bankAccount) {
        return null != bankAccount ? bankAccount.getName() : "";
    }
}

