package com.paritosh.recipe.service;

import com.paritosh.recipe.converters.RecipeBackingBeanToRecipe;
import com.paritosh.recipe.converters.RecipeToRecipeBackingBean;
import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeBackingBean recipeToRecipeBackingBean;
    @Mock
    RecipeBackingBeanToRecipe recipeBackingBeanToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository,recipeBackingBeanToRecipe,recipeToRecipeBackingBean) ;

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

    @Test
    public void findById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.findById(1L);
        assertNotNull(recipeReturned);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());


    }
    
    
    @Test
    public void testDeleteById() throws Exception {
         //given
        Long idToDelete = Long.valueOf(2L);
         //when
        recipeService.deleteById(idToDelete);
         //no 'when', since method has void return type
         //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}