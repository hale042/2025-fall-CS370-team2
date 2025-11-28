import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeApiData {
    public static void main(String[] args){
        // getRecipeFromName("spaghetti");
        // getRandomRecipeData();
        // getRandomRecipe();

        try{
            Scanner scanner = new Scanner(System.in);
            String recipe;
            do{
                //getting user input
                System.out.print("Enter Meal Name (Say no to quit):  ");
                recipe = scanner.nextLine();

                if(recipe.equalsIgnoreCase("no")) break;

                //getting data
                JSONObject mealData = (JSONObject) getRecipeData(recipe);

                String recipeName = mealData.get("strMeal").toString();
                //String ingredients = mealData.get("strIngredient1").toString();
                String category = mealData.get("strCategory").toString();
                String instructions = mealData.get("strInstructions").toString();

                //displayRecipe(recipeName, ingredients, category, instructions);
                List<String> ingredients = extractIngredients(mealData);

                // Display
                System.out.println("Meal: " + recipeName);
                System.out.println("Category: " + category);
                System.out.println("\nIngredients:");
                for (String ing : ingredients) {
                    System.out.println(" - " + ing);
                }
                System.out.println("\nInstructions: \n" + instructions);

            }while(!recipe.equalsIgnoreCase("no"));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Recipe getRecipeFromName(String mealName) {
        // added by RArnold
        JSONObject mealData = (JSONObject) getRecipeData(mealName);

        String recipeName = mealData.get("strMeal").toString();
        // List<String> ingredients = extractIngredients(mealData);
        List<Ingredient> ingredients = stringToIngredientsList(extractIngredients(mealData));;
        // String category = mealData.get("strCategory").toString();
        String instructions = mealData.get("strInstructions").toString();
        
        Recipe recipe = new Recipe(recipeName, ingredients, instructions, "null", 0);
        return recipe;
    }

    public static Recipe getRandomRecipe() {
        // added by RArnold
        JSONObject mealData = (JSONObject) getRandomRecipeData();

        String recipeName = mealData.get("strMeal").toString();
        // List<String> ingredients = extractIngredients(mealData);
        List<Ingredient> ingredients = stringToIngredientsList(extractIngredients(mealData));;
        // String category = mealData.get("strCategory").toString();
        String instructions = mealData.get("strInstructions").toString();
        
        System.out.println(recipeName + " - " + instructions);
        Recipe recipe = new Recipe(recipeName, ingredients, instructions, "null", 0);
        return recipe;
    }

    private static List<Ingredient> stringToIngredientsList(List<String> ingredients) {
        // added by RArnold
        List<Ingredient> ingredientsList = new ArrayList<Ingredient>();

        for (String ingredientString : ingredients) {
            String ingredientAmount = ingredientString.split(" ")[0];
            String ingredientName = ingredientString.split(" ")[1];
            // System.out.println(ingredientAmount + " " + ingredientName);

            Ingredient ingredient = new Ingredient(ingredientName, ingredientAmount);
            ingredientsList.add(ingredient);
        }

        return ingredientsList;
    }

    private static List<String> extractIngredients(JSONObject mealData) {
        List<String> ingredients = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            String ingKey = "strIngredient" + i;
            String measKey = "strMeasure" + i;

            String ingredient = (String) mealData.get(ingKey);
            String measure = (String) mealData.get(measKey);

            // Skip blank/null entries
            if (ingredient == null || ingredient.trim().isEmpty()) continue;

            // Some meals have empty measure fields
            if (measure == null) measure = "";

            ingredients.add(measure.trim() + " " + ingredient.trim());
        }

        return ingredients;
    }

    private static JSONObject getRecipeData(String recipe) {
        recipe = recipe.replaceAll(" ", "+");

        String urlString = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + recipe;

        try {
            //fetch api
            HttpURLConnection apiConnection = fetchApiResponse(urlString);

            //check response
            //if 200 its success
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to the API");
                return null;
            }

            //read response and convert store string type
            String jsonResponse = readApiResponse(apiConnection);

            //parse string into json object
            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

            //retrieve data
            //  JSONArray recipeData = (JSONArray) resultsJsonObj.get("meals");

            JSONArray mealArray = (JSONArray) resultsJsonObj.get("meals");

            if (mealArray == null) {
                System.out.println("No recipe found for: " + recipe.replace("+", " "));
                return null;
            }

            //  return (JSONObject) recipeData.get(0);
            return (JSONObject) mealArray.get(0);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject getRandomRecipeData() {
        // added by RArnold; only real change is the url string, used the one for the random recipe
        String urlString = "https://www.themealdb.com/api/json/v1/1/random.php";

        try {
            //fetch api
            HttpURLConnection apiConnection = fetchApiResponse(urlString);

            //check response
            //if 200 its success
            if (apiConnection.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to the API");

                return null;
            }

            //read response and convert store string type
            String jsonResponse = readApiResponse(apiConnection);

            //parse string into json object
            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

            //retrieve data
            //  JSONArray recipeData = (JSONArray) resultsJsonObj.get("meals");

            JSONArray mealArray = (JSONArray) resultsJsonObj.get("meals");

            if (mealArray == null) {
                System.out.println("No recipe found.");

                return null;
            }

            //  return (JSONObject) recipeData.get(0);
            return (JSONObject) mealArray.get(0);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static void displayRecipe(String StrMeal, String strIngredients, String strCategory, String strInstructions){}

    private static String readApiResponse(HttpURLConnection apiConnection) {
        try {
            //create stringbuilder to store resulting json data
            StringBuilder resultJson = new StringBuilder();

            //create scanner to read from inputstream
            Scanner scanner = new Scanner(apiConnection.getInputStream());

            //loop through every line and append to stringbuilder
            while (scanner.hasNext()) {
                resultJson.append(scanner.next());
            }

            //close
            scanner.close();

            //return json data as string
            return resultJson.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            //attempt to create a connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //set the request method to get
            conn.setRequestMethod("GET");

            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //could not make connection
        return null;
    }

    void main() {
    }}