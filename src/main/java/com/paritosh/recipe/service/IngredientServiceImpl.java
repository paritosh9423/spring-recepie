package com.paritosh.recipe.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.converters.IngredientBackingBeanToIngredient;
import com.paritosh.recipe.converters.IngredientToIngredientBackingBean;
import com.paritosh.recipe.domain.Ingredient;
import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;
import com.paritosh.recipe.repositories.UnitOfMeasureRespository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

	private final IngredientToIngredientBackingBean ingredientToIngredientBackingBean;
	private final IngredientBackingBeanToIngredient ingredientBackingBeanToIngredient;
	private final RecipeRepository recipeRepository ;
	private final UnitOfMeasureRespository unitOfMeasureRespository;
	



	public IngredientServiceImpl(IngredientToIngredientBackingBean ingredientToIngredientBackingBean,
			IngredientBackingBeanToIngredient ingredientBackingBeanToIngredient, RecipeRepository recipeRepository,
			UnitOfMeasureRespository unitOfMeasureRespository) {
		super();
		this.ingredientToIngredientBackingBean = ingredientToIngredientBackingBean;
		this.ingredientBackingBeanToIngredient = ingredientBackingBeanToIngredient;
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRespository = unitOfMeasureRespository;
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
																		.map(ingredient -> ingredientToIngredientBackingBean.convert(ingredient)).findFirst();
		if(!optionalIngredientBackingBean.isPresent()) {
			log.error("ingredeint Not Found for ingredient Id: "+ingredientId+" recipeId: "+recipeId);
		}
		return optionalIngredientBackingBean.get();
		
	}


	@Override
	@Transactional
	public IngredientBackingBean saveIngredientBackingBean(IngredientBackingBean ingredientBackingBean) {
		Optional<Recipe> reOptional = recipeRepository.findById(ingredientBackingBean.getRecipeId());
		if(!reOptional.isPresent())
		{
			log.error("Recipe Not Found for Id: "+ingredientBackingBean.getRecipeId());
			return new IngredientBackingBean();
		}
		Recipe recipe = reOptional.get();
		Optional<Ingredient> ingredientOptional = recipe.getIngredients()
														.stream()
														.filter(ingredient -> ingredient.getId().equals(ingredientBackingBean.getId()))
														.findFirst();
		if(!ingredientOptional.isPresent())
		{
			recipe.addIngredients(ingredientBackingBeanToIngredient.convert(ingredientBackingBean));
		}
		else {
			Ingredient ingredientFound = ingredientOptional.get();
			ingredientFound.setDescription(ingredientBackingBean.getDescription());
			ingredientFound.setAmount(ingredientBackingBean.getAmount());
			ingredientFound.setUnitOfMeasure(unitOfMeasureRespository.findById(ingredientBackingBean.getUnitOfMeasure().getId()).
								orElseThrow(()->new RuntimeException("UOM Not Found")));
		}
		Recipe savedRecipe = recipeRepository.save(recipe);
		
		return ingredientToIngredientBackingBean.convert(savedRecipe.getIngredients().stream()
													.filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientBackingBean.getId()))
													.findFirst().get());
		
		
	}
	
	
	
	

}
