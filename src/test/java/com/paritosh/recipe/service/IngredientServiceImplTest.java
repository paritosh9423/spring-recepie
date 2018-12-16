package com.paritosh.recipe.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.converters.IngredientBackingBeanToIngredient;
import com.paritosh.recipe.converters.IngredientToIngredientBackingBean;
import com.paritosh.recipe.converters.UnitOfMeasureBackingBeanToUnitOfMeasure;
import com.paritosh.recipe.converters.UnitOfMeasureToUnitOfMeasureBackingBean;
import com.paritosh.recipe.domain.Ingredient;
import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;
import com.paritosh.recipe.repositories.UnitOfMeasureRespository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

	
	private final IngredientToIngredientBackingBean ingredientToIngredientBackingBean;
	private final IngredientBackingBeanToIngredient ingredientBackingBeanToIngredient;
	
	@Mock
	RecipeRepository recipeRepository;
	@Mock
	UnitOfMeasureRespository unitOfMeasureRespository;
	IngredientService ingredientService;
	
	public IngredientServiceImplTest() {
		super();
		this.ingredientToIngredientBackingBean = new IngredientToIngredientBackingBean(new UnitOfMeasureToUnitOfMeasureBackingBean());
		this.ingredientBackingBeanToIngredient = new IngredientBackingBeanToIngredient(new UnitOfMeasureBackingBeanToUnitOfMeasure());
	
	}
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(ingredientToIngredientBackingBean, ingredientBackingBeanToIngredient,
							recipeRepository,unitOfMeasureRespository);
			
	}
	
	
	@Test
    public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
		
		//given
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Ingredient ingredient1 = new  Ingredient();
		ingredient1.setId(1L);
		Ingredient ingredient2 = new  Ingredient();
		ingredient2.setId(2L);
		Ingredient ingredient3 = new  Ingredient();
		ingredient3.setId(3L);
		
		recipe.addIngredients(ingredient1);
        recipe.addIngredients(ingredient2);
        recipe.addIngredients(ingredient3);
        
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        
        //then
        IngredientBackingBean ingredientBackingBean = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);
        
        //when
        assertEquals(Long.valueOf(3L), ingredientBackingBean.getId());
        assertEquals(Long.valueOf(1L), ingredientBackingBean.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
		
	}
	
	
	@Test
    public void testSaveRecipeCommand() throws Exception {
		//given
		IngredientBackingBean ingredientBackingBean = new IngredientBackingBean();
		ingredientBackingBean.setId(3L);
		ingredientBackingBean.setRecipeId(2L);
		
		Optional<Recipe> recipeOptional = Optional.of(new Recipe());
		Recipe savedRecipe = new Recipe();
		savedRecipe.addIngredients(new Ingredient());
		savedRecipe.getIngredients().iterator().next().setId(3L);
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(savedRecipe);
		
		//when
		IngredientBackingBean savedIngredientBackingBean = ingredientService.saveIngredientBackingBean(ingredientBackingBean);
		
		//then
		assertEquals(Long.valueOf(3L), savedIngredientBackingBean.getId());
		verify(recipeRepository,times(1)).findById(anyLong());
		verify(recipeRepository,times(1)).save(any(Recipe.class));
	}
	
	
	
}
