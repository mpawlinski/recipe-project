package com.pawlinski.recipeproject.controllers;

import com.pawlinski.recipeproject.model.Category;
import com.pawlinski.recipeproject.model.UnitOfMeasure;
import com.pawlinski.recipeproject.repositories.CategoryRepository;
import com.pawlinski.recipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Category Id: " + categoryOptional.get().getId());
        System.out.println("Unut of Measure Id: " + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
