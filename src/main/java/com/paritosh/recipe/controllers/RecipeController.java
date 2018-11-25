package com.paritosh.recipe.controllers;

import com.paritosh.recipe.backingBeans.RecipeBackingBean;
import com.paritosh.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @RequestMapping({"/recipe/show/{id}"})
    public String showById(@PathVariable String id , Model model){

        model.addAttribute("recipe",recipeService.findById(new Long(id)));
        return "recipe/show";
    }
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeBackingBean());
        return "recipe/recipeForm" ;
    }
    @RequestMapping(name="recipe",method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute RecipeBackingBean recipeBackingBean){
        RecipeBackingBean savedRecipeBackingBean = recipeService.saveRecipeBackingBean(recipeBackingBean);
        return "redirect:/recipe/show/"+savedRecipeBackingBean.getId();
    }

}
