import javax.swing.JMenuItem; //add more
import java.util.List;
import java.util.*;


//get ingredients from the user using text field
//integrate the api
//way to store the recipes (the name, ingredients, steps, difficulty rating)
//match recipes with users input



/*
public class main { //testing without api use
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RecipeFinder finder = new RecipeFinder();

        // Example recipes
        finder.addRecipe(new Recipe("Pancakes",
                Arrays.asList(new Ingredient("flour"), new Ingredient("egg"), new Ingredient("milk")),
                "Mix ingredients and fry on a pan."
        ));

        finder.addRecipe(new Recipe("Scrambled Eggs",
                Arrays.asList(new Ingredient("egg"), new Ingredient("butter")),
                "Whisk eggs and cook in butter."
        ));

        System.out.println("Enter ingredients you have (comma-separated): ");
        List<String> input = Arrays.asList(sc.nextLine().toLowerCase().split(",\\s*"));

        List<Recipe> results = finder.findRecipes(input);
        if (results.isEmpty()) {
            System.out.println("No recipes found with those ingredients.");
        } else {
            System.out.println("You can make:");
            for (Recipe r : results) {
                System.out.println("- " + r.getName());
            }
        }
    }
}

public class Ingredient {
    private String name;
    
    public Ingredient(String name) {
        this.name = name.toLowerCase();
    }
    
    public String getName() {
        return name;
    }
}

public class Recipe {
    private String name;
    private List<Ingredient> ingredients = new ArrayList<>();
    private String instructions;

    public Recipe(String name, List<Ingredient> ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    
    public String getName() {
        return name;
    }
    
    public String getInstructions() {
        return instructions;
    }
}

import java.util.*;

public class RecipeFinder {
    private List<Recipe> recipes = new ArrayList<>();
    
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    
    public List<Recipe> findRecipes(List<String> availableIngredients) {
        List<Recipe> matched = new ArrayList<>();
        
        for (Recipe recipe : recipes) {
            boolean canMake = recipe.getIngredients().stream()
            .allMatch(ing -> availableIngredients.contains(ing.getName()));
            if (canMake) {
                matched.add(recipe);
            }
        }
        return matched;
    }
}
*/