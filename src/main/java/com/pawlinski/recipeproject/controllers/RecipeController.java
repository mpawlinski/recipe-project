package com.pawlinski.recipeproject.controllers;

import com.pawlinski.recipeproject.commands.RecipeCommand;
import com.pawlinski.recipeproject.exceptions.NotFoundException;
import com.pawlinski.recipeproject.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
public class RecipeController {

    private static final String RECIPE_FORM_URL = "recipe/recipeform";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String getRecipeById(@PathVariable String id, Model model) {

        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";
    }

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("saveRecipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult) { // binding result on the model is tied through fields in html

        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.debug(objectError.toString()));

            return RECIPE_FORM_URL;
        }

        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(new Long(id)));

        return "recipe/recipeform";
    }

    @GetMapping("/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteById(new Long(id));

        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundError(Exception exception) {
        log.error("Handling not found exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("recipe/404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
