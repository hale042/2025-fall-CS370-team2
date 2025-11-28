import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//name, single random meal, categories, main ingredient, category, area
public class RecipeApiData{
    public static void main(String[] args){
        // try{
        Scanner scanner = new Scanner(System.in);
        String recipe;
        //String userInput;
        //String assignedVariable;

        while(true) {
            //getting user input
            //System.out.print("Enter Meal Name (Say no to quit):  ");
            System.out.println("Choose search type:");
            System.out.println("1) Search by Name");
            System.out.println("2) Single Random Meal");
            System.out.println("3) List Categories");
            System.out.println("4) Filter by Main Ingredient");
            System.out.println("5) Filter by Category");
            System.out.println("6) Filter by Area"); //added
            System.out.println("7) List Areas");
            System.out.println("8) Quit");
            System.out.print("Enter search type: ");

            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 8) break;

            String userInput = "";
            if (choice != 2 && choice != 3 && choice != 7) { //if choice is 1, 4, 5 ask to enter search term
                System.out.print("Enter your search term: ");
                userInput = scanner.nextLine();
            }
            //building api url
            String apiUrl = buildApiUrl(choice, userInput);

            if (apiUrl == null) {
                System.out.println("Invalid search term");
                continue;
            }

            if (choice == 3) {  // Categories
                fetchAndShowCategories(apiUrl);
                continue;
            }

            if (choice >= 4 && choice <= 6) {  // Filter results (no ingredients provided)
                fetchAndShowFilteredMeals(apiUrl);
                continue;
            }

            if(choice == 7){
                fetchAndShowAreas(apiUrl);
                continue;
            }

            JSONObject mealData = getRecipeData(apiUrl);

            if (mealData == null) {
                System.out.println("Invalid recipe data");
                continue;
            }
            //  if(recipe.equalsIgnoreCase("no")) break;

            //getting data
            //JSONObject mealData = (JSONObject) getRecipeData(recipe);

            String recipeName = mealData.get("strMeal").toString();
            String area = mealData.get("strArea").toString();
            String category = mealData.get("strCategory").toString();
            String instructions = mealData.get("strInstructions").toString();

            //displayRecipe(recipeName, ingredients, category, instructions);
            List<String> ingredients = extractIngredients(mealData);

            // Display
            System.out.println("Meal: " + recipeName);
            System.out.println("Category: " + category);
            System.out.println("Area: " + area);

            System.out.println("\nIngredients:");
            for (String ing : ingredients) {
                System.out.println(" - " + ing);
            }
            System.out.println("\nInstructions: \n" + instructions);

            //  }while(!recipe.equalsIgnoreCase("no"));

            //  }catch(Exception e){
            // e.printStackTrace();
        }
    }

    //show meals that match filter input
    private static void fetchAndShowFilteredMeals(String url) {
        try {
            HttpURLConnection conn = fetchApiResponse(url);
            String response = readApiResponse(conn);

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(response);

            JSONArray meals = (JSONArray) obj.get("meals");

            if (meals == null) {
                System.out.println("No results found.");
                return;
            }

            System.out.println("\n=== Matching Meals ===");
            for (Object m : meals) {
                JSONObject meal = (JSONObject) m;
                System.out.println(" - " + meal.get("strMeal"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //show all categories
    private static void fetchAndShowCategories(String url) {
        try{
            HttpURLConnection conn = fetchApiResponse(url);
            String response = readApiResponse(conn);

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(response);

            JSONArray categories = (JSONArray) obj.get("categories");

            System.out.println("\n=== Categories ===");
            for (Object c : categories) {
                JSONObject cat = (JSONObject) c;
                System.out.println(" - " + cat.get("strCategory"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //show all areas
    private static void fetchAndShowAreas(String url) {
        try{
            HttpURLConnection conn = fetchApiResponse(url);
            String response = readApiResponse(conn);

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(response);

            JSONArray area = (JSONArray) obj.get("meals");

            System.out.println("\n=== Areas ===");
            for (Object a : area) {
                JSONObject are = (JSONObject) a;
                System.out.println(" - " + are.get("strArea"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> extractIngredients(JSONObject mealData) {
        List<String> ingredients = new ArrayList<>();

        //loop thru ingredients and measures
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

    private static JSONObject getRecipeData(String urlString) {
        // recipe = recipe.replaceAll(" ", "+");

        //String urlString = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + recipe;

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

//           if (mealArray == null) {
//              System.out.println("No recipe found for: " + recipe.replace("+", " "));
//                return null;
//            }

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
            while (scanner.hasNextLine()) {
                resultJson.append(scanner.nextLine());
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

    public static String buildApiUrl(int choice, String userInput) {
        switch (choice) {
            case 1: // Search by Name
                return "https://www.themealdb.com/api/json/v1/1/search.php?s=" + userInput;

            case 2: // Random meal
                return "https://www.themealdb.com/api/json/v1/1/random.php";

            case 3: // Categories list
                return "https://www.themealdb.com/api/json/v1/1/categories.php";

            case 4: // Filter by Main Ingredient
                return "https://www.themealdb.com/api/json/v1/1/filter.php?i=" + userInput;

            case 5: // Filter by Category
                return "https://www.themealdb.com/api/json/v1/1/filter.php?c=" + userInput;

            case 6: //filter by areas
                return "https://www.themealdb.com/api/json/v1/1/filter.php?a=" + userInput;

            case 7: // list Area
                return "https://www.themealdb.com/api/json/v1/1/list.php?a=list";

            default:
                return null;
        }
    }

    void main() {
    }}

