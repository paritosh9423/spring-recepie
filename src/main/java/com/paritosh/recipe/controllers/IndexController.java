package com.paritosh.recipe.controllers;

import com.paritosh.recipe.domain.Category;
import com.paritosh.recipe.domain.UnitOfMeasure;
import com.paritosh.recipe.repositories.CategoryRepository;
import com.paritosh.recipe.repositories.UnitOfMeasureRespository;
import com.paritosh.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/","","index","index.html"})
    public String getIndexPage(Model model){
        System.out.println("abcd ------------------------------");
        model.addAttribute("recipes",recipeService.getRecipeList());

        return "index";
    }

}
