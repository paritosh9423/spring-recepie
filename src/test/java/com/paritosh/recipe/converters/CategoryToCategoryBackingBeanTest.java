package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.CategoryBackingBean;
import com.paritosh.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryBackingBeanTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "descript";
    CategoryToCategoryBackingBean categoryToCategoryBackingBean;

    @Before
    public void setUp() throws Exception {
    categoryToCategoryBackingBean = new CategoryToCategoryBackingBean();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(categoryToCategoryBackingBean.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(categoryToCategoryBackingBean.convert(new Category()));
    }

    @Test
    public void convert() {

        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryBackingBean categoryBackingBean = categoryToCategoryBackingBean.convert(category);

        //then
        assertEquals(ID_VALUE, categoryBackingBean.getId());
        assertEquals(DESCRIPTION, categoryBackingBean.getDescription());

    }
}