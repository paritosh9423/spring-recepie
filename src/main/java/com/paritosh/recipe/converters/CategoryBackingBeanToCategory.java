package com.paritosh.recipe.converters;


import com.paritosh.recipe.backingBeans.CategoryBackingBean;
import com.paritosh.recipe.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryBackingBeanToCategory implements Converter<CategoryBackingBean, Category> {
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryBackingBean categoryBackingBean) {
        if(categoryBackingBean==null)
            return  null;
        final Category category = new Category();
        category.setId(categoryBackingBean.getId());
        category.setDescription(categoryBackingBean.getDescription());
        return category;
    }
}
