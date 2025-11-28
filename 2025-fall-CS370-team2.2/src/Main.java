import javax.swing.JMenuItem; //add more
//import java.util.list;
import java.util.*;


//get ingredients from the user using text field
//integrate the api
//way to store the recipes (the name, ingredients, steps, difficulty rating)
//match recipes with users input



public class Main { //testing without api use
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




