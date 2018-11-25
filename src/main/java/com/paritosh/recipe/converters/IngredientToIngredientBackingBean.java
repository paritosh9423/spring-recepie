package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientBackingBean implements Converter<Ingredient, IngredientBackingBean> {

    private final UnitOfMeasureToUnitOfMeasureBackingBean unitOfMeasureToUnitOfMeasureBackingBean;

    public IngredientToIngredientBackingBean(UnitOfMeasureToUnitOfMeasureBackingBean unitOfMeasureToUnitOfMeasureBackingBean) {
        this.unitOfMeasureToUnitOfMeasureBackingBean = unitOfMeasureToUnitOfMeasureBackingBean;
    }

    @Override
    public IngredientBackingBean convert(Ingredient ingredient) {
        if(ingredient == null)
            return null;
        IngredientBackingBean ingredientBackingBean = new IngredientBackingBean();
        ingredientBackingBean.setId(ingredient.getId());
        ingredientBackingBean.setAmount(ingredient.getAmount());
        ingredientBackingBean.setDescription(ingredient.getDescription());
        ingredientBackingBean.setUnitOfMeasure(unitOfMeasureToUnitOfMeasureBackingBean.convert(ingredient.getUnitOfMeasure()));
        return ingredientBackingBean;
    }
}
