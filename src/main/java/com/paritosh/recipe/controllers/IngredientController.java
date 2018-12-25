package com.paritosh.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.paritosh.recipe.backingBeans.IngredientBackingBean;
import com.paritosh.recipe.service.IngredientService;
import com.paritosh.recipe.service.RecipeService;
import com.paritosh.recipe.service.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class IngredientController {

	private final RecipeService recipeService;
	private final IngredientService ingredientService;
	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureService = unitOfMeasureService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredientsl(@PathVariable String recipeId , Model model) {
		
		log.debug("Getting ingredient List for recipe ID: "+recipeId);
		
			model.addAttribute("recipe" , recipeService.findRecipeBackingBeanByID(Long.valueOf(recipeId)));
			return "recipe/ingredients/list";
			
		
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/show")
	public String showRecipeIngredient(@PathVariable String recipeId,
										@PathVariable String id,
										Model model) {
		
		model.addAttribute("ingredient" , 
				ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		return "/recipe/ingredients/show";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/update")
	public String updateRecpeIngredient(@PathVariable String recipeId,
			@PathVariable String id,
			Model model) {
		model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
		model.addAttribute("uomList",unitOfMeasureService.listAllUoms());
		return "recipe/ingredients/ingredientForm";
	}
	
	@PostMapping
	@RequestMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdate(@ModelAttribute IngredientBackingBean ingredientBackingBean) {
			IngredientBackingBean ingredientBackingBeanSaved = ingredientService.saveIngredientBackingBean(ingredientBackingBean);
			return "redirect:/recipe/"+ingredientBackingBeanSaved.getRecipeId()+"/ingredient/"+ingredientBackingBeanSaved.getId()+"/show";
	}
	
	@GetMapping
	@RequestMapping("recipe/{recipeId}/ingredient/{id}/delete")
	public String deleteIngredient(@PathVariable String recipeId,
			@PathVariable String id,
			Model model) {
		System.out.println("bbbbbbbbbbbbbbbbbbbb----------------------------------------------");
		return null;
	}
	
	
	
	
}
