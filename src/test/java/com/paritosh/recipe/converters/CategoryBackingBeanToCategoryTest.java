package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.CategoryBackingBean;
import com.paritosh.recipe.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryBackingBeanToCategoryTest {


    public static final Long ID_VALUE = new Long(1L);
    public static final String DESCRIPTION = "description";
    CategoryBackingBeanToCategory categoryBackingBeanToCategory;


    @Before
    public void setUp() throws Exception {
        categoryBackingBeanToCategory = new CategoryBackingBeanToCategory();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(categoryBackingBeanToCategory.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(categoryBackingBeanToCategory.convert(new CategoryBackingBean()));
    }

    @Test
    public void convert() throws  Exception{
        //given

        CategoryBackingBean categoryBackingBean = new CategoryBackingBean();
        categoryBackingBean.setId(ID_VALUE);
        categoryBackingBean.setDescription(DESCRIPTION);


        //when

        Category category = categoryBackingBeanToCategory.convert(categoryBackingBean);


        //then

        assertEquals(ID_VALUE,category.getId());
        assertEquals(DESCRIPTION,category.getDescription());
    }
}