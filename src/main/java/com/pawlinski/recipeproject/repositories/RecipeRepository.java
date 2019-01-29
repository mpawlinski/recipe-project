package com.pawlinski.recipeproject.repositories;

import com.pawlinski.recipeproject.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
