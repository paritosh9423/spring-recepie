package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.UnitOfMeasureBackingBean;
import com.paritosh.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureBackingBeanToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    UnitOfMeasureBackingBeanToUnitOfMeasure unitOfMeasureBackingBeanToUnitOfMeasure;


    @Before
    public void setUp() throws Exception {
        unitOfMeasureBackingBeanToUnitOfMeasure = new UnitOfMeasureBackingBeanToUnitOfMeasure();
    }


    @Test
    public void testNullParamter() throws Exception {
        assertNull(unitOfMeasureBackingBeanToUnitOfMeasure.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(unitOfMeasureBackingBeanToUnitOfMeasure.convert(new UnitOfMeasureBackingBean()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasureBackingBean unitOfMeasureBackingBean = new UnitOfMeasureBackingBean();
        unitOfMeasureBackingBean.setId(LONG_VALUE);
        unitOfMeasureBackingBean.setUom(DESCRIPTION);

        //when
        UnitOfMeasure uom = unitOfMeasureBackingBeanToUnitOfMeasure.convert(unitOfMeasureBackingBean);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getUom());

    }
}