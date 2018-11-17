package com.paritosh.recipe.service;

import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);

    }

    @Test
    public void getRecipeList() {

        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeHashSet = new HashSet<>();
        recipeHashSet.add(recipe);

        Mockito.when(recipeService.getRecipeList()).thenReturn(recipeHashSet);

        Set<Recipe> recipes = recipeService.getRecipeList();

        assertEquals(recipes.size(),1);
        Mockito.verify(recipeRepository,Mockito.times(1)).findAll();

    }
}