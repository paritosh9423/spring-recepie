package com.paritosh.recipe.bootloader;
//Created by ppradeep on 10/11/18, 1:14 PM


import com.paritosh.recipe.domain.*;
import com.paritosh.recipe.repositories.CategoryRepository;
import com.paritosh.recipe.repositories.RecipeRepository;
import com.paritosh.recipe.repositories.UnitOfMeasureRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRespository unitOfMeasureRespository;
    @Autowired
    public DataLoader(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRespository unitOfMeasureRespository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRespository = unitOfMeasureRespository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipeList());
    }

    private void loadData() {

    }
    private List<Recipe> getRecipeList(){

        List<Recipe> recipeList = new ArrayList<>();


try {
    //Get uom
    Optional<UnitOfMeasure> optionalUnitOfMeasureTeaSpoon = unitOfMeasureRespository.findByUom("TeaSpoon");
    Optional<UnitOfMeasure> optionalUnitOfMeasureTableSpoon = unitOfMeasureRespository.findByUom("TableSpoon");
    Optional<UnitOfMeasure> optionalUnitOfMeasureCup = unitOfMeasureRespository.findByUom("Cup");
    Optional<UnitOfMeasure> optionalUnitOfMeasurePinch = unitOfMeasureRespository.findByUom("Pinch");
    Optional<UnitOfMeasure> optionalUnitOfMeasureOunce = unitOfMeasureRespository.findByUom("Ounce");
    Optional<UnitOfMeasure> optionalUnitOfMeasureEach = unitOfMeasureRespository.findByUom("Each");
    Optional<UnitOfMeasure> optionalUnitOfMeasureDash = unitOfMeasureRespository.findByUom("Dash");
    Optional<UnitOfMeasure> optionalUnitOfMeasurePint = unitOfMeasureRespository.findByUom("Pint");

    //Get Category

    Optional<Category> optionalCategoryAmerican = categoryRepository.findByDescription("American");
    Optional<Category> optionalCategoryMexican = categoryRepository.findByDescription("Mexican");

    Recipe recipeGuac = new Recipe();
    recipeGuac.setDescription("GUAC RECIPE");
    recipeGuac.setPrepTime(10);
    recipeGuac.setCookTime(30);
    recipeGuac.setDifficulty(Difficulty.HARD);
    recipeGuac.setDirections("DIRECTIONS GOES HERE");

    Notes notes = new Notes();
    notes.setRecipeNotes("Reipe Notes goes here");
    notes.setRecipe(recipeGuac);
    recipeGuac.setNotes(notes);


    recipeGuac.getIngredients().add(new Ingredient("INGREDIENT-1",new BigDecimal(2), optionalUnitOfMeasureCup.get(),recipeGuac));
    recipeGuac.getCategories().add(optionalCategoryAmerican.get());
    recipeGuac.getCategories().add(optionalCategoryMexican.get());

    recipeList.add(recipeGuac);

}
    catch (Exception ex){
    log.error("Exception occurred"+ex.getLocalizedMessage());
    throw new RuntimeException("UOM NOT FOUND");
    }
    return recipeList;
    }

}
