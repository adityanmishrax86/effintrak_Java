package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.accounts.dtos.BankAccountResponseDTO;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccountResponseDTO toResponseDTO(BankAccount account);
}
