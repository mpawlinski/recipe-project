package com.pawlinski.recipeproject.converters;

import com.pawlinski.recipeproject.commands.IngredientCommand;
import com.pawlinski.recipeproject.model.Ingredient;
import com.pawlinski.recipeproject.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    public static final Long ID_VALUE = 1L;
    public static final String DESCRIPTION = "description";
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Long UOM_ID = 2L;

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter);
    }

    @Test
    public void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(UOM_ID, ingredientCommand.getUom().getId());
        assertNotNull(ingredientCommand.getUom());
   }

   @Test
    public void convertWithNullUom() {
        //given
       Ingredient ingredient = new Ingredient();
       ingredient.setId(ID_VALUE);
       ingredient.setDescription(DESCRIPTION);
       ingredient.setAmount(AMOUNT);

       //when
       IngredientCommand ingredientCommand = converter.convert(ingredient);

       //then
       assertEquals(ID_VALUE, ingredientCommand.getId());
       assertEquals(DESCRIPTION, ingredientCommand.getDescription());
       assertEquals(AMOUNT, ingredientCommand.getAmount());
       assertNull(ingredientCommand.getUom());
   }
}