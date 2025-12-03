package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.Transfer.dtos.TransferRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Transfer.dtos.TransferResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Transfer.model.Transfer;
import com.azaxxc.effintrakj.effinTrak.accounts.model.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    @Mapping(target = "fromAccountName", source = "fromAccount", qualifiedByName = "mapBankAccountObject")
    @Mapping(target = "toAccountName", source = "toAccount", qualifiedByName = "mapBankAccountObject")
    @Mapping(target = "transferDate", source = "transferDate", dateFormat = "yyyy-MM-dd")
    TransferResponseDTO toTransferResponse(Transfer transfer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "fromAccount", ignore = true)
    @Mapping(target = "toAccount", ignore = true)
    @Mapping(target = "transferDate", ignore = true)
    Transfer toTransfer(TransferRequestDTO dto);

    @Named("mapBankAccountObject")
    default String mapBankAccountObject(BankAccount bankAccount) {
        return null != bankAccount ? bankAccount.getName() : "";
    }
}

