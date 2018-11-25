package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.CategoryBackingBean;
import com.paritosh.recipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryBackingBean implements Converter<Category , CategoryBackingBean> {
    @Synchronized
    @Nullable
    @Override
    public CategoryBackingBean convert(Category category) {
        if(category==null)
            return null;
        CategoryBackingBean categoryBackingBean = new CategoryBackingBean();
        categoryBackingBean.setId(category.getId());
        categoryBackingBean.setDescription(category.getDescription());
        return categoryBackingBean;
    }
}
