package com.paritosh.recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.service.IngredientService;
import com.paritosh.recipe.service.RecipeService;
import com.paritosh.recipe.service.UnitOfMeasureService;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

import java.util.HashSet;

public class IngredientControllerTest {

	
	@Mock
	RecipeService recipeService;
	@Mock
	IngredientService ingredientService;
	
	IngredientController ingredientController;
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		ingredientController = new IngredientController(recipeService,ingredientService, unitOfMeasureService);
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
	
	
	@Test
	public void testShowIngredient() throws Exception{
		//given
		IngredientBackingBean ingredientBackingBean = new IngredientBackingBean();
		//when
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong()))
			.thenReturn(ingredientBackingBean);
		//then
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
				.andExpect(status().isOk())
				.andExpect(view().name("/recipe/ingredients/show"))
				.andExpect(model().attributeExists("ingredient"));
		
	}
	
	@Test
    public void testUpdateIngredientForm() throws Exception {
        //given
		IngredientBackingBean ingredientCommand = new IngredientBackingBean();

        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
        when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

        //then
        mockMvc.perform(get("/recipe/1/ingredient/2/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredients/ingredientForm"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //given
    	IngredientBackingBean command = new IngredientBackingBean();
        command.setId(3L);
        command.setRecipeId(2L);

        //when
        when(ingredientService.saveIngredientBackingBean(any())).thenReturn(command);

        //then
        mockMvc.perform(post("/recipe/2/ingredient")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "some string")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));

    }
	
}
