package com.paritosh.recipe.controllers;

import com.paritosh.recipe.domain.Category;
import com.paritosh.recipe.domain.UnitOfMeasure;
import com.paritosh.recipe.repositories.CategoryRepository;
import com.paritosh.recipe.repositories.UnitOfMeasureRespository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;

    private UnitOfMeasureRespository unitOfMeasureRespository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRespository unitOfMeasureRespository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRespository = unitOfMeasureRespository;
    }


    @RequestMapping({"/","","index","index.html"})
    public String getIndexPage(){
        System.out.println("abcd ------------------------------");

        Optional<Category> optionalCategory = categoryRepository.findByDescription("AMERICAN");
        System.out.println(optionalCategory.get().getDescription());

        Optional<UnitOfMeasure> optionalUnitOfMeasure = unitOfMeasureRespository.findByUom("Grams");
        System.out.println(optionalUnitOfMeasure.get().getUom());
        return "index";
    }

}
