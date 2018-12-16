package com.paritosh.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paritosh.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredientsl(@PathVariable String recipeId , Model model) {
		
		log.debug("Getting ingredient List for recipe ID: "+recipeId);
		
			model.addAttribute("recipe" , recipeService.findRecipeBackingBeanByID(Long.valueOf(recipeId)));
			return "recipe/ingredients/list";
			
		
	}
	
	
}
