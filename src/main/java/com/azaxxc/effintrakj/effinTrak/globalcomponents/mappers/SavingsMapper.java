package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.dtos.SavingsResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Savings.model.Savings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SavingsMapper {

    SavingsMapper INSTANCE = Mappers.getMapper(SavingsMapper.class);
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(target = "targetDate", source = "targetDate", qualifiedByName = "mapDateToString")
    SavingsResponseDTO toSavingsResponse(Savings savings);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "targetDate", ignore = true)
    Savings toSavings(SavingsRequestDTO dto);

    @Named("mapDateToString")
    default String mapDateToString(Date date) {
        if (date == null) return "";
        return date.toLocalDate().format(DATE_FORMATTER);
    }
}

