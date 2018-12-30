package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeBackingBean implements Converter<Recipe , RecipeBackingBean> {

    private final CategoryToCategoryBackingBean categoryToCategoryBackingBean;
    private final IngredientToIngredientBackingBean ingredientToIngredientBackingBean;
    private final NotesToNotesBackingBean notesToNotesBackingBean;

    public RecipeToRecipeBackingBean(CategoryToCategoryBackingBean categoryToCategoryBackingBean, IngredientToIngredientBackingBean ingredientToIngredientBackingBean, NotesToNotesBackingBean notesToNotesBackingBean) {
        this.categoryToCategoryBackingBean = categoryToCategoryBackingBean;
        this.ingredientToIngredientBackingBean = ingredientToIngredientBackingBean;
        this.notesToNotesBackingBean = notesToNotesBackingBean;
    }
    @Synchronized
    @Nullable
    @Override
    public RecipeBackingBean convert(Recipe recipe) {
        if(recipe == null)
            return null;
        final RecipeBackingBean recipeBackingBean = new RecipeBackingBean();
        recipeBackingBean.setId(recipe.getId());
        recipeBackingBean.setCookTime(recipe.getCookTime());
        recipeBackingBean.setPrepTime(recipe.getPrepTime());
        recipeBackingBean.setDifficulty(recipe.getDifficulty());
        recipeBackingBean.setDescription(recipe.getDescription());
        recipeBackingBean.setDirections(recipe.getDirections());
        recipeBackingBean.setServings(recipe.getServings());
        recipeBackingBean.setSource(recipe.getSource());
        recipeBackingBean.setUrl(recipe.getUrl());
        recipeBackingBean.setImage(recipe.getImage());
        recipeBackingBean.setNotes(notesToNotesBackingBean.convert(recipe.getNotes()));

        if(recipe.getIngredients()!=null && recipe.getIngredients().size()>0){
            recipe.getIngredients().forEach(ingredient -> recipeBackingBean.getIngredients().
                    add(ingredientToIngredientBackingBean.convert(ingredient)));
        }
        if (recipe.getCategories()!=null && !recipe.getCategories().isEmpty()){
            recipe.getCategories().forEach(category -> recipeBackingBean.getCategories().
                    add(categoryToCategoryBackingBean.convert(category)));
        }
        return recipeBackingBean;
    }
}
