package com.paritosh.recipe.controllers;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping({"/recipe/show/{id}"})
    public String showById(@PathVariable String id , Model model){

        model.addAttribute("recipe",recipeService.findById(new Long(id)));
        return "recipe/show";
    }
    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeBackingBean());
        return "recipe/recipeForm" ;
    }
    @PostMapping(name="recipe")
    public String saveOrUpdate(@ModelAttribute RecipeBackingBean recipeBackingBean){
        RecipeBackingBean savedRecipeBackingBean = recipeService.saveRecipeBackingBean(recipeBackingBean);
        return "redirect:/recipe/show/"+savedRecipeBackingBean.getId();
    }
    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id ,  Model model){
        model.addAttribute("recipe",recipeService.findRecipeBackingBeanByID(Long.valueOf(id)));
        return  "recipe/recipeForm";
    }
     //Get mapping is default
    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
         log.debug("Deleting id: " + id);
         recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}
