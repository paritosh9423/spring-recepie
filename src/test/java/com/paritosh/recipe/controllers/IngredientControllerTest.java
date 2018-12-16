package com.paritosh.recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.service.RecipeService;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {

	
	@Mock
	RecipeService recipeService;
	
	IngredientController ingredientController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		ingredientController = new IngredientController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}
	
	@Test
	public void testListIngredient()  throws Exception{
		//given
		RecipeBackingBean recipeBackingBean = new RecipeBackingBean();
		when(recipeService.findRecipeBackingBeanByID(anyLong())).thenReturn(recipeBackingBean);
		
		//when
		mockMvc.perform(get("/recipe/1/ingredients"))
				.andExpect(view().name("recipe/ingredients/list"))
				.andExpect(model().attributeExists("recipe"));
		
		//then
		verify(recipeService,times(1)).findRecipeBackingBeanByID(anyLong());
		
	}
	
}
