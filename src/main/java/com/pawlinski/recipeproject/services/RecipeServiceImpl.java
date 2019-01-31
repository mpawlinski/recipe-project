package com.pawlinski.recipeproject.services;

import com.pawlinski.recipeproject.model.Recipe;
import com.pawlinski.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);

//        recipeRepository.findAll().iterator().forEachRemaining(recipe -> {
//            recipeSet.add(recipe);
//        });

        return recipeSet;
    }
}
