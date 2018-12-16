package com.paritosh.recipe.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.converters.IngredientToIngredientBackingBean;
import com.paritosh.recipe.converters.UnitOfMeasureToUnitOfMeasureBackingBean;
import com.paritosh.recipe.domain.Ingredient;
import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

	
	private final IngredientToIngredientBackingBean ingredientToIngredientBackingBean;
	
	@Mock
	RecipeRepository recipeRepository;
	IngredientService ingredientService;
	public IngredientServiceImplTest() {
		super();
		this.ingredientToIngredientBackingBean = new IngredientToIngredientBackingBean(new UnitOfMeasureToUnitOfMeasureBackingBean());
	
	}
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(ingredientToIngredientBackingBean, recipeRepository);
			
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
	
}
