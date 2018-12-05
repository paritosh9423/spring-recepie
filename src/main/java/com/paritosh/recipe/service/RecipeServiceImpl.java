package com.paritosh.recipe.service;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.converters.RecipeBackingBeanToRecipe;
import com.paritosh.recipe.converters.RecipeToRecipeBackingBean;
import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeBackingBeanToRecipe recipeBackingBeanToRecipe;
    private final RecipeToRecipeBackingBean recipeToRecipeBackingBean;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeBackingBeanToRecipe recipeBackingBeanToRecipe,
                             RecipeToRecipeBackingBean recipeToRecipeBackingBean) {

        this.recipeRepository = recipeRepository;
        this.recipeBackingBeanToRecipe = recipeBackingBeanToRecipe;
        this.recipeToRecipeBackingBean = recipeToRecipeBackingBean;
    }

    @Override
    public Set<Recipe> getRecipeList() {
        log.debug("Inside RecipeServiceImpl::getRecipeList()");
        Set<Recipe> recipes  =  new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    public Recipe findById(Long id){
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent())
            throw new RuntimeException("Recipe with RecipeID : "+id+" Not Found");
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeBackingBean saveRecipeBackingBean(RecipeBackingBean recipeBackingBean) {
        Recipe detachedrecipe = recipeBackingBeanToRecipe.convert(recipeBackingBean);
        Recipe savedRecipe = recipeRepository.save(detachedrecipe);

        log.debug("Saved Recipe Id:= "+savedRecipe.getId());
        return recipeToRecipeBackingBean.convert(savedRecipe);

    }

    @Override
    @Transactional
    public RecipeBackingBean findRecipeBackingBeanByID(Long l) {
        return recipeToRecipeBackingBean.convert(findById(l));
    }
}
