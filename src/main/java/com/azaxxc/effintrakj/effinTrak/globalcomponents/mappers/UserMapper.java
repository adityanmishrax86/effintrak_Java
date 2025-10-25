package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.users.dto.UserResponseDTO;
import com.azaxxc.effintrakj.effinTrak.users.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toResponseDTO(User user);
}
