package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.Category.dtos.CategoryResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponseDTO toResponseDTO(Category category);
}
