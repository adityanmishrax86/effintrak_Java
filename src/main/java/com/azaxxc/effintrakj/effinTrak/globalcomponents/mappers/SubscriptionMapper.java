package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.SubscriptionRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.dtos.SubscriptionResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Subscription.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);
    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "mapDateToString")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "mapDateToString")
    SubscriptionResponseDTO toSubscriptionResponse(Subscription subscription);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    Subscription toSubscription(SubscriptionRequestDTO dto);

    @Named("mapDateToString")
    default String mapDateToString(Date date) {
        if (date == null) return "";
        return date.toLocalDate().format(DATE_FORMATTER);
    }
}

