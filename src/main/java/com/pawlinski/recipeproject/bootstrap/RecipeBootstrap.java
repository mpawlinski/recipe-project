package com.pawlinski.recipeproject.bootstrap;

import com.pawlinski.recipeproject.model.*;
import com.pawlinski.recipeproject.repositories.CategoryRepository;
import com.pawlinski.recipeproject.repositories.RecipeRepository;
import com.pawlinski.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Profile("default")
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    //ContextRefreshedEvent gets thrown when the Spring Context starts up. When app is running this event loads the data

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Loading bootstrap data.");
        recipeRepository.saveAll(loadData());
    }

    private List<Recipe> loadData() {

        List<Recipe> recipes = new ArrayList<>(2);

        //getting UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()) {
            throw new RuntimeException("UOM has not been found.");
        }

        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("UOM has not been found");
        }

        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("UOM has not been found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("UOM has not been found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if(!pinchUomOptional.isPresent()) {
            throw new RuntimeException("UOM has not been found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()) {
            throw new RuntimeException("UOM has not been found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()) {
            throw new RuntimeException("UOM has not been found");
        }

        //get UOMs
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure teaspoonUom =teaspoonUomOptional.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();


        //categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");

        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Category has not been found");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");

        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Category has not been found");
        }

        //get categories
        Category americanCategory = americanCategoryOptional.get();
        Category mexioanCategory = mexicanCategoryOptional.get();

        //Guacamole recipe
        Recipe guacamoleRecipe = new Recipe();
        guacamoleRecipe.setDescription("Perfect Guacamole");
        guacamoleRecipe.setPrepTime(10);
        guacamoleRecipe.setCookTime(0);
        guacamoleRecipe.setDifficulty(Difficulty.EASY);
        guacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl." +
                                        "\n" +
                                       "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                                        "\n" +
                                       "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                                       "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                                       "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." +
                                        "\n" +
                                       "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                                       "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving." +
                                        "\n" +
                                        "\n" +
                                       "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                                      "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                                      "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                                      "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                                      "\n" +
                                      "\n" +
                                      "Read more: https://www.simplyrecipes.com/recipes/perfect_guacamole");

        guacamoleRecipe.setNotes(guacamoleNotes);

        guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaspoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(1), tablespoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
        guacamoleRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoonUom));
        guacamoleRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(2), dashUom));
        guacamoleRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(1), eachUom));

        guacamoleRecipe.getCategories().add(americanCategory);
        guacamoleRecipe.getCategories().add(mexioanCategory);

        guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamoleRecipe.setServings(4);
        guacamoleRecipe.setSource("Simply Recipes");

        //add to return list
        recipes.add(guacamoleRecipe);

        //Yummy Tacos
        Recipe chickenTacosRecipe = new Recipe();
        chickenTacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        chickenTacosRecipe.setPrepTime(20);
        chickenTacosRecipe.setCookTime(15);
        chickenTacosRecipe.setDifficulty(Difficulty.MODERATE);
        chickenTacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                                         "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over." +
                                         "\n" +
                                         "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                                         "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                                         "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side." +
                                         "\n" +
                                         "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                                         "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges." +
                                         "\n" +
                                         "\n" +
                                         "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos");

        Notes chickenTacosNotes = new Notes();
        chickenTacosNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                                         "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                                         "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                                         "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                                         "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                                         "\n" +
                                         "\n" +
                                         "Read more: https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos");

        chickenTacosRecipe.setNotes(chickenTacosNotes);

        chickenTacosRecipe.addIngredient(new Ingredient("ancho chili powder", new BigDecimal(2), tablespoonUom));
        chickenTacosRecipe.addIngredient(new Ingredient("dried oregano", new BigDecimal(1), teaspoonUom));
        chickenTacosRecipe.addIngredient(new Ingredient("dried cumin", new BigDecimal(1), teaspoonUom));
        chickenTacosRecipe.addIngredient(new Ingredient("sugar", new BigDecimal(1), teaspoonUom));
        chickenTacosRecipe.addIngredient(new Ingredient("salt", new BigDecimal(0.5), teaspoonUom));
        chickenTacosRecipe.addIngredient(new Ingredient("clove garlic, finely chopped", new BigDecimal(1), eachUom));
        chickenTacosRecipe.addIngredient(new Ingredient("finely grated orange zest", new BigDecimal(1), tablespoonUom));
        chickenTacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        chickenTacosRecipe.addIngredient(new Ingredient("olive oil", new BigDecimal(2), tablespoonUom));
        chickenTacosRecipe.addIngredient(new Ingredient("skinless, boneless chicken thighs", new BigDecimal(6), eachUom));
        chickenTacosRecipe.addIngredient(new Ingredient("small corn tortillas", new BigDecimal(8), eachUom));
        chickenTacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        chickenTacosRecipe.addIngredient(new Ingredient("ripe avocados, sliced", new BigDecimal(2), eachUom));
        chickenTacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(0.5), pintUom));
        chickenTacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(0.25), eachUom));
        chickenTacosRecipe.addIngredient(new Ingredient("roughly chopped cilantro", new BigDecimal(2), eachUom));
        chickenTacosRecipe.addIngredient(new Ingredient("sour cream thinned with 1/4 cup milk", new BigDecimal(0.5), cupUom));
        chickenTacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(1), eachUom));

        chickenTacosRecipe.getCategories().add(americanCategory);
        chickenTacosRecipe.getCategories().add(mexioanCategory);

        chickenTacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        chickenTacosRecipe.setServings(8);
        chickenTacosRecipe.setSource("Simply Recipes");

        //add to return list
        recipes.add(chickenTacosRecipe);

        return recipes;
    }
}




















