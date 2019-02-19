package com.pawlinski.recipeproject.services;

import com.pawlinski.recipeproject.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

    void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long ingredientIdToDelete);
}
