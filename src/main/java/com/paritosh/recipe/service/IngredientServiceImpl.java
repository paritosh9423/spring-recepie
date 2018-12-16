package com.paritosh.recipe.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.converters.IngredientToIngredientBackingBean;
import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

	private final IngredientToIngredientBackingBean ingredientToIngredientBackingBean;
	private final RecipeRepository recipeRepository ;
	
	
	public IngredientServiceImpl(IngredientToIngredientBackingBean ingredientToIngredientBackingBean,
			RecipeRepository recipeRepository) {
		super();
		this.ingredientToIngredientBackingBean = ingredientToIngredientBackingBean;
		this.recipeRepository = recipeRepository;
	}


	@Override
	public IngredientBackingBean findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		
		Optional<Recipe> reOptional = recipeRepository.findById(recipeId);
		if(!reOptional.isPresent())
		{
				log.error("Recipe not found .Recipe Id:"+recipeId+" Ingredient Id: "+ingredientId);
		}
		Recipe recipe = reOptional.get();
		Optional<IngredientBackingBean> optionalIngredientBackingBean = recipe.getIngredients().stream()
																		.filter(ingredient ->ingredient.getId().equals(ingredientId))
																		.map(ingredient ->ingredientToIngredientBackingBean.convert(ingredient)).findFirst();
		if(!optionalIngredientBackingBean.isPresent()) {
			log.error("ingredeint Not Found for ingredient Id: "+ingredientId+" recipeId: "+recipeId);
		}
		return optionalIngredientBackingBean.get();
		
	}
	
	

}
