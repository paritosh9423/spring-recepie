package com.paritosh.recipe.service;


import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.converters.RecipeBackingBeanToRecipe;
import com.paritosh.recipe.converters.RecipeToRecipeBackingBean;
import com.paritosh.recipe.domain.Recipe;
import com.paritosh.recipe.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";
    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    RecipeBackingBeanToRecipe recipeBackingBeanToRecipe;
    @Autowired
    RecipeToRecipeBackingBean recipeToRecipeBackingBean;

    @Transactional
    @Test
    public  void testSaveOfDescription() throws Exception{
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeBackingBean testrecipeBackingBean = recipeToRecipeBackingBean.convert(testRecipe);

        //when
        testrecipeBackingBean.setDescription(NEW_DESCRIPTION);
        RecipeBackingBean savedRecipeBackingBean = recipeService.saveRecipeBackingBean(testrecipeBackingBean);

        //then
        Assert.assertEquals(NEW_DESCRIPTION, savedRecipeBackingBean.getDescription());
        Assert.assertEquals(testRecipe.getId(), savedRecipeBackingBean.getId());
        Assert.assertEquals(testRecipe.getCategories().size(), savedRecipeBackingBean.getCategories().size());
        Assert.assertEquals(testRecipe.getIngredients().size(), savedRecipeBackingBean.getIngredients().size());
    }
}
