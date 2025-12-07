package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;

import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetRequestDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.dtos.BudgetResponseDTO;
import com.azaxxc.effintrakj.effinTrak.Budget.model.Budget;
import com.azaxxc.effintrakj.effinTrak.Category.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    BudgetMapper INSTANCE = Mappers.getMapper(BudgetMapper.class);

    @Mapping(target = "categoryName", source = "category", qualifiedByName = "mapCategoryObject")
    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd")
    BudgetResponseDTO toBudgetResponse(Budget budget);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    Budget toBudget(BudgetRequestDTO dto);

    @Named("mapCategoryObject")
    default String mapCategoryObject(Category category) {
        return null != category ? category.getName() : "Overall";
    }
}
