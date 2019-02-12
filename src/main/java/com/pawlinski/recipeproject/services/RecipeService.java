package com.pawlinski.recipeproject.services;

import com.pawlinski.recipeproject.commands.RecipeCommand;
import com.pawlinski.recipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);
}
