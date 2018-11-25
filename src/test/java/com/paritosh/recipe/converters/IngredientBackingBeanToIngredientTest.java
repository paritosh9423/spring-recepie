package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.backingBeans.UnitOfMeasureBackingBean;
import com.paritosh.recipe.domain.Ingredient;
import com.paritosh.recipe.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientBackingBeanToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);

    IngredientBackingBeanToIngredient ingredientBackingBeanToIngredient;


    @Before
    public void setUp() throws Exception {
        ingredientBackingBeanToIngredient = new IngredientBackingBeanToIngredient(new UnitOfMeasureBackingBeanToUnitOfMeasure());

    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(ingredientBackingBeanToIngredient.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(ingredientBackingBeanToIngredient.convert(new IngredientBackingBean()));
    }


    @Test
    public void convert() {

        //given
        IngredientBackingBean ingredientBackingBean = new IngredientBackingBean();
        ingredientBackingBean.setId(ID_VALUE);
        ingredientBackingBean.setAmount(AMOUNT);
        ingredientBackingBean.setDescription(DESCRIPTION);
        UnitOfMeasureBackingBean unitOfMeasureBackingBean = new UnitOfMeasureBackingBean();
        unitOfMeasureBackingBean.setId(UOM_ID);
        ingredientBackingBean.setUnitOfMeasure(unitOfMeasureBackingBean);

        //when
        Ingredient ingredient = ingredientBackingBeanToIngredient.convert(ingredientBackingBean);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
   }

    @Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientBackingBean ingredientBackingBean = new IngredientBackingBean();

        ingredientBackingBean.setId(ID_VALUE);
        ingredientBackingBean.setAmount(AMOUNT);
        ingredientBackingBean.setDescription(DESCRIPTION);
        UnitOfMeasureBackingBean unitOfMeasureBackingBean = new UnitOfMeasureBackingBean();


        //when
        Ingredient ingredient = ingredientBackingBeanToIngredient.convert(ingredientBackingBean);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());

    }

}