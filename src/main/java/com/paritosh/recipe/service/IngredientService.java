package com.paritosh.recipe.service;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;

public interface IngredientService {

	IngredientBackingBean findByRecipeIdAndIngredientId(Long recipeId , Long ingredientId);
	
	IngredientBackingBean saveIngredientBackingBean(IngredientBackingBean ingredientBackingBean);
}
