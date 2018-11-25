package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.domain.Ingredient;
import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientBackingBeanTest {


    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long UOM_ID = new Long(2L);
    public static final Long ID_VALUE = new Long(1L);

    IngredientToIngredientBackingBean ingredientToIngredientBackingBean;

    @Before
    public void setUp() throws Exception {
        ingredientToIngredientBackingBean  = new IngredientToIngredientBackingBean(new UnitOfMeasureToUnitOfMeasureBackingBean());
    }

    @Test
    public void testNullConvert() throws Exception {
        assertNull(ingredientToIngredientBackingBean.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(ingredientToIngredientBackingBean.convert(new Ingredient()));
    }


    @Test
    public void testConvertNullUOM() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUnitOfMeasure(null);
        //when
        IngredientBackingBean ingredientBackingBean = ingredientToIngredientBackingBean.convert(ingredient);
        //then
        assertNull(ingredientBackingBean.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredientBackingBean.getId());
        // assertEquals(RECIPE, ingredientCommand.get);
        assertEquals(AMOUNT, ingredientBackingBean.getAmount());
        assertEquals(DESCRIPTION, ingredientBackingBean.getDescription());
    }

    @Test
    public void testConvertWithUom() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        ingredient.setUnitOfMeasure(uom);
        //when
        IngredientBackingBean ingredientBackingBean = ingredientToIngredientBackingBean.convert(ingredient);
        //then
        assertEquals(ID_VALUE, ingredientBackingBean.getId());
        assertNotNull(ingredientBackingBean.getUnitOfMeasure());
        assertEquals(UOM_ID, ingredientBackingBean.getUnitOfMeasure().getId());
        assertEquals(AMOUNT, ingredientBackingBean.getAmount());
        assertEquals(DESCRIPTION, ingredientBackingBean.getDescription());
    }
}