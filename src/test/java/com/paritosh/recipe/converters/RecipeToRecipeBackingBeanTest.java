package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.domain.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeBackingBeanTest {

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

    RecipeToRecipeBackingBean recipeToRecipeBackingBean;


    @Before
    public void setUp() throws Exception {
        recipeToRecipeBackingBean =  new RecipeToRecipeBackingBean(
                new CategoryToCategoryBackingBean(),
                new IngredientToIngredientBackingBean(new UnitOfMeasureToUnitOfMeasureBackingBean()),
                new NotesToNotesBackingBean()
        );
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(recipeToRecipeBackingBean.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(recipeToRecipeBackingBean.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Category category1 = new Category();
        category1.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);

        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGRED_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeBackingBean recipeBackingBean = recipeToRecipeBackingBean.convert(recipe);

        //then
        assertNotNull(recipeBackingBean);
        assertEquals(RECIPE_ID, recipeBackingBean.getId());
        assertEquals(COOK_TIME, recipeBackingBean.getCookTime());
        assertEquals(PREP_TIME, recipeBackingBean.getPrepTime());
        assertEquals(DESCRIPTION, recipeBackingBean.getDescription());
        assertEquals(DIFFICULTY, recipeBackingBean.getDifficulty());
        assertEquals(DIRECTIONS, recipeBackingBean.getDirections());
        assertEquals(SERVINGS, recipeBackingBean.getServings());
        assertEquals(SOURCE, recipeBackingBean.getSource());
        assertEquals(URL, recipeBackingBean.getUrl());
        assertEquals(NOTES_ID, recipeBackingBean.getNotes().getId());
        assertEquals(2, recipeBackingBean.getCategories().size());
        assertEquals(2, recipeBackingBean.getIngredients().size());

    }

}