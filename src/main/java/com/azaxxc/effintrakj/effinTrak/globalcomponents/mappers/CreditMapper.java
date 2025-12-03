package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.dtos.CreditResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Credits.model.Credit;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface CreditMapper {

    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(target = "creditorName", source = "creditor", qualifiedByName = "mapCreditorObject")
    @Mapping(target = "dueDate", source = "dueDate", qualifiedByName = "mapDateToString")
    CreditResponseDTO toCreditResponse(Credit credit);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "creditor", ignore = true)
    @Mapping(target = "dueDate", ignore = true)
    Credit toCredit(CreditRequestDTO dto);

    @Named("mapCreditorObject")
    default String mapCreditorObject(BankAccount creditor) {
        return null != creditor ? creditor.getName() : "";
    }

    @Named("mapDateToString")
    default String mapDateToString(Date date) {
        if (date == null) return "";
        return date.toLocalDate().format(DATE_FORMATTER);
    }
}

