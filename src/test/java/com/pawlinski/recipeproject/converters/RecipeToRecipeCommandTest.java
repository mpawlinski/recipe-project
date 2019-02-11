package com.pawlinski.recipeproject.converters;

import com.pawlinski.recipeproject.commands.RecipeCommand;
import com.pawlinski.recipeproject.model.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class RecipeToRecipeCommandTest {

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

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                                              new NotesToNotesCommand(), new CategoryToCategoryCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(ID_VALUE);
        recipe.setDescription(DESCRIPTION);
        recipe.setPrepTime(PREP_TIME);
        recipe.setCookTime(COOK_TIME);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);
        recipe.setDirections(DIRECTIONS);
        recipe.setDifficulty(DIFFICULTY);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(INGREDIENT_1_ID);
        recipe.getIngredients().add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGREDIENT_2_ID);
        recipe.getIngredients().add(ingredient2);

        Category category1 = new Category();
        category1.setId(CATEGORY_1_ID);
        recipe.getCategories().add(category1);

        Category category2 = new Category();
        category2.setId(CATEGORY_2_ID);
        recipe.getCategories().add(category2);

        //when
        RecipeCommand recipeCommand = converter.convert(recipe);

        //then
        assertNotNull(recipeCommand);
        assertEquals(ID_VALUE, recipeCommand.getId());
        assertEquals(DESCRIPTION, recipeCommand.getDescription());
        assertEquals(PREP_TIME, recipeCommand.getPrepTime());
        assertEquals(COOK_TIME, recipeCommand.getCookTime());
        assertEquals(SERVINGS, recipeCommand.getServings());
        assertEquals(SOURCE, recipeCommand.getSource());
        assertEquals(URL, recipeCommand.getUrl());
        assertEquals(DIRECTIONS, recipeCommand.getDirections());
        assertEquals(DIFFICULTY, recipeCommand.getDifficulty());
        assertEquals(NOTES_ID, recipeCommand.getNotes().getId());
        assertEquals(2, recipeCommand.getIngredients().size());
        assertEquals(2, recipeCommand.getCategories().size());
    }
}