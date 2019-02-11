package com.pawlinski.recipeproject.services;

import com.pawlinski.recipeproject.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l);
}
