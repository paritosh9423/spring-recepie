package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.UnitOfMeasureBackingBean;
import com.paritosh.recipe.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;



@Component
public class UnitOfMeasureBackingBeanToUnitOfMeasure  implements Converter<UnitOfMeasureBackingBean , UnitOfMeasure> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureBackingBean unitOfMeasureBackingBean) {
        if(unitOfMeasureBackingBean == null)
            return null;
        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(unitOfMeasureBackingBean.getId());
        unitOfMeasure.setUom(unitOfMeasureBackingBean.getUom());

        return unitOfMeasure;
    }
}
