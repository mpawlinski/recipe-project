package com.pawlinski.recipeproject.converters;

import com.pawlinski.recipeproject.commands.CategoryCommand;
import com.pawlinski.recipeproject.commands.IngredientCommand;
import com.pawlinski.recipeproject.commands.NotesCommand;
import com.pawlinski.recipeproject.commands.RecipeCommand;
import com.pawlinski.recipeproject.model.Difficulty;
import com.pawlinski.recipeproject.model.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    public static final Integer PREP_TIME = 5;
    public static final Integer COOK_TIME = 10;
    public static final Integer SERVINGS = 6;
    public static final String SOURCE = "source";
    public static final String URL = "www.test.org";
    public static final String DIRECTIONS = "directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Long NOTES_ID = 2L;
    public static final Long INGREDIENT_1_ID = 3L;
    public static final Long INGREDIENT_2_ID = 4L;
    public static final Long CATEGORY_1_ID = 5L;
    public static final Long CATEGORY_2_ID = 6L;

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                                              new NotesCommandToNotes(), new CategoryCommandToCategory());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID_VALUE);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(NOTES_ID);
        recipeCommand.setNotes(notesCommand);

        IngredientCommand ingredientCommand1 = new IngredientCommand();
        ingredientCommand1.setId(INGREDIENT_1_ID);
        recipeCommand.getIngredients().add(ingredientCommand1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGREDIENT_2_ID);
        recipeCommand.getIngredients().add(ingredientCommand2);

        CategoryCommand categoryCommand1 = new CategoryCommand();
        categoryCommand1.setId(CATEGORY_1_ID);
        recipeCommand.getCategories().add(categoryCommand1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CATEGORY_2_ID);
        recipeCommand.getCategories().add(categoryCommand2);

        //when
        Recipe recipe = converter.convert(recipeCommand);

        //then
        assertNotNull(recipe);
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(2, recipe.getIngredients().size());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
    }
}