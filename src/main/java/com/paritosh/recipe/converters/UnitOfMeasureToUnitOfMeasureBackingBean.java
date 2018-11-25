package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.UnitOfMeasureBackingBean;
import com.paritosh.recipe.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureBackingBean implements Converter<UnitOfMeasure, UnitOfMeasureBackingBean> {

    @Override
    public UnitOfMeasureBackingBean convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure == null)
            return null;
        final UnitOfMeasureBackingBean unitOfMeasureBackingBean = new UnitOfMeasureBackingBean();
        unitOfMeasureBackingBean.setId(unitOfMeasure.getId());
        unitOfMeasureBackingBean.setUom(unitOfMeasure.getUom());
        return unitOfMeasureBackingBean;


    }
}
