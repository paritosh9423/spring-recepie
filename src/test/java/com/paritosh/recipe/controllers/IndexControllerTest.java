package com.paritosh.recipe.controllers;

import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;
import com.paritosh.recipe.service.RecipeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class IndexControllerTest {

    IndexController indexController;
    @Mock
    RecipeServiceImpl recipeService;
    @Mock
    Model model;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);

    }

    @Test
    public void testMockMVC() throws Exception{

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("index"));

    }

    @Test
    public void getIndexPage() {

        //Given
        Set<Recipe> recipeSet = new HashSet<>();
        recipeSet.add(new Recipe());
        recipeSet.add(new Recipe());
        Recipe recipe  = new Recipe();
        recipe.setId(1L);
        recipeSet.add(recipe);

        Mockito.when(recipeService.getRecipeList()).thenReturn(recipeSet);

        //When
        String viewName = indexController.getIndexPage(model);
        ArgumentCaptor<Set<Recipe>> setArgumentCaptor = ArgumentCaptor.forClass(Set.class);


        //then
        assertEquals("index",viewName);
        Mockito.verify(recipeService,Mockito.times(1)).getRecipeList();
        //Mockito.verify(model,Mockito.times(1)).addAttribute(ArgumentMatchers.eq("recipes"), ArgumentMatchers.anySet());

        Mockito.verify(model,Mockito.times(1))
                .addAttribute(ArgumentMatchers.eq("recipes"),setArgumentCaptor.capture());
        Set<Recipe> recipes = setArgumentCaptor.getValue();
        System.out.println(recipes.size());
        assertEquals(2,recipes.size());
    }
}