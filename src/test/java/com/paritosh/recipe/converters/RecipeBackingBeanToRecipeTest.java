package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.CategoryBackingBean;
import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.backingBeans.NotesBackingBean;
import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.domain.Difficulty;
import com.paritosh.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeBackingBeanToRecipeTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeBackingBeanToRecipe recipeBackingBeanToRecipe;

    @Before
    public void setUp() throws Exception {
        recipeBackingBeanToRecipe = new RecipeBackingBeanToRecipe(
                new CategoryBackingBeanToCategory(),
                new NotesBackingBeanToNotes(),
                new IngredientBackingBeanToIngredient(new UnitOfMeasureBackingBeanToUnitOfMeasure())
        );

    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(recipeBackingBeanToRecipe.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(recipeBackingBeanToRecipe.convert(new RecipeBackingBean()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeBackingBean recipeBackingBean = new RecipeBackingBean();
        recipeBackingBean.setId(RECIPE_ID);
        recipeBackingBean.setCookTime(COOK_TIME);
        recipeBackingBean.setPrepTime(PREP_TIME);
        recipeBackingBean.setDescription(DESCRIPTION);
        recipeBackingBean.setDifficulty(DIFFICULTY);
        recipeBackingBean.setDirections(DIRECTIONS);
        recipeBackingBean.setServings(SERVINGS);
        recipeBackingBean.setSource(SOURCE);
        recipeBackingBean.setUrl(URL);

        NotesBackingBean notesBackingBean = new NotesBackingBean();
        notesBackingBean.setId(NOTES_ID);

        recipeBackingBean.setNotes(notesBackingBean);

        CategoryBackingBean categoryBackingBean1 = new CategoryBackingBean();
        categoryBackingBean1.setId(CAT_ID_1);

        CategoryBackingBean categoryBackingBean2 = new CategoryBackingBean();
        categoryBackingBean2.setId(CAT_ID2);

        recipeBackingBean.getCategories().add(categoryBackingBean1);
        recipeBackingBean.getCategories().add(categoryBackingBean2);

        IngredientBackingBean ingredientBackingBean1 = new IngredientBackingBean();
        ingredientBackingBean1.setId(INGRED_ID_1);

        IngredientBackingBean ingredientBackingBean2 = new IngredientBackingBean();
        ingredientBackingBean2.setId(INGRED_ID_2);

        recipeBackingBean.getIngredients().add(ingredientBackingBean1);
        recipeBackingBean.getIngredients().add(ingredientBackingBean2);

        //when
        Recipe recipe  = recipeBackingBeanToRecipe.convert(recipeBackingBean);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}