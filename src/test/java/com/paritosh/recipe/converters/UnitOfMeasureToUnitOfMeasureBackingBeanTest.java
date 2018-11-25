package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.UnitOfMeasureBackingBean;
import com.paritosh.recipe.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureBackingBeanTest {

    public static final String DESCRIPTION = "description";
    public static final Long LONG_VALUE = new Long(1L);

    UnitOfMeasureToUnitOfMeasureBackingBean unitOfMeasureToUnitOfMeasureBackingBean;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureToUnitOfMeasureBackingBean = new UnitOfMeasureToUnitOfMeasureBackingBean();
    }
    @Test
    public void testNullObjectConvert() throws Exception {
        assertNull(unitOfMeasureToUnitOfMeasureBackingBean.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception {
        assertNotNull(unitOfMeasureToUnitOfMeasureBackingBean.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setUom(DESCRIPTION);
        //when
        UnitOfMeasureBackingBean unitOfMeasureBackingBean = unitOfMeasureToUnitOfMeasureBackingBean.convert(uom);

        //then
        assertEquals(LONG_VALUE, unitOfMeasureBackingBean.getId());
        assertEquals(DESCRIPTION, unitOfMeasureBackingBean.getUom());
    }
}