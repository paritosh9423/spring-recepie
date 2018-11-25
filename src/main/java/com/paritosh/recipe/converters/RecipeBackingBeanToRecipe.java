package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeBackingBeanToRecipe implements Converter<RecipeBackingBean, Recipe> {

    private final CategoryBackingBeanToCategory categoryBackingBeanToCategory;
    private final NotesBackingBeanToNotes notesBackingBeanToNotes;
    private final IngredientBackingBeanToIngredient ingredientBackingBeanToIngredient;

    public RecipeBackingBeanToRecipe(CategoryBackingBeanToCategory categoryBackingBeanToCategory, NotesBackingBeanToNotes notesBackingBeanToNotes, IngredientBackingBeanToIngredient ingredientBackingBeanToIngredient) {
        this.categoryBackingBeanToCategory = categoryBackingBeanToCategory;
        this.notesBackingBeanToNotes = notesBackingBeanToNotes;
        this.ingredientBackingBeanToIngredient = ingredientBackingBeanToIngredient;
    }
    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeBackingBean recipeBackingBean) {
        if(recipeBackingBean == null)
            return null;
        final Recipe recipe = new Recipe();
        recipe.setId(recipeBackingBean.getId());
        recipe.setCookTime(recipeBackingBean.getCookTime());
        recipe.setPrepTime(recipeBackingBean.getPrepTime());
        recipe.setDirections(recipeBackingBean.getDirections());
        recipe.setDescription(recipeBackingBean.getDescription());
        recipe.setDifficulty(recipeBackingBean.getDifficulty());
        recipe.setServings(recipeBackingBean.getServings());
        recipe.setSource(recipeBackingBean.getSource());
        recipe.setUrl(recipeBackingBean.getUrl());
        recipe.setNotes(notesBackingBeanToNotes.convert(recipeBackingBean.getNotes()));

        if(recipeBackingBean.getCategories()!=null && recipeBackingBean.getCategories().size()>0)
        {
            recipeBackingBean.getCategories()
                    .forEach(categoryBackingBean -> recipe.getCategories().add(categoryBackingBeanToCategory.convert(categoryBackingBean)));
        }

        if(recipeBackingBean.getIngredients()!=null && recipeBackingBean.getIngredients().size()>0)
        {
            recipeBackingBean.getIngredients()
                .forEach(ingredientBackingBean -> recipe.getIngredients().add(ingredientBackingBeanToIngredient.convert(ingredientBackingBean)));

        }

        return recipe;
    }
}
