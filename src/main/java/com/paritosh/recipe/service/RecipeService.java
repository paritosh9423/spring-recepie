package com.paritosh.recipe.service;

import com.paritosh.recipe.domain.Recipe;

import java.util.Set;

public interface RecipeService   {

    Set<Recipe> getRecipeList();
    public Recipe findById(Long id);

}
