package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientBackingBeanToIngredient implements Converter<IngredientBackingBean, Ingredient> {

    private final UnitOfMeasureBackingBeanToUnitOfMeasure unitOfMeasureBackingBeanToUnitOfMeasure;

    public IngredientBackingBeanToIngredient(UnitOfMeasureBackingBeanToUnitOfMeasure unitOfMeasureBackingBeanToUnitOfMeasure) {
        this.unitOfMeasureBackingBeanToUnitOfMeasure = unitOfMeasureBackingBeanToUnitOfMeasure;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientBackingBean ingredientBackingBean) {
        if(ingredientBackingBean==null)
            return null;
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientBackingBean.getId());
        ingredient.setAmount(ingredientBackingBean.getAmount());
        ingredient.setDescription(ingredientBackingBean.getDescription());
        ingredient.setUnitOfMeasure(unitOfMeasureBackingBeanToUnitOfMeasure.convert(ingredientBackingBean.getUnitOfMeasure()));
        return  ingredient;

    }
}
