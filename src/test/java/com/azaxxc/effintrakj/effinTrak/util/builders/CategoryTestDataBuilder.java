package com.azaxxc.effintrakj.effinTrak.util.builders;

import com.azaxxc.effintrakj.effinTrak.Category.model.Category;

public class CategoryTestDataBuilder {
    private Long id;
    private String name = "Test Category";

    public CategoryTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public CategoryTestDataBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public Category build() {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }

    public static CategoryTestDataBuilder aCategory() {
        return new CategoryTestDataBuilder();
    }
}

