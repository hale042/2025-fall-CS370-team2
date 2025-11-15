import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RecipeApiData{
    public static void main(String[] args){
        try{
            Scanner scanner = new Scanner(System.in);
            String recipe;
            do{
                //getting user input
                System.out.print("Enter Meal Name: ");
                recipe = scanner.next();

                //getting data
                JSONObject mealData = (JSONObject) getRecipeData(recipe);
                string recipeName = mealNameData.get("strMeal");
                string ingredients = ingredientsData.get("strIngredients");
                string category = categoryData.get("strCategory");
                string instructions = instructions.Data.get("strInstructions");

                displayRecipe(strMeal, strIngredients, strCategory, strInstructions);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
private static JSONObject getRecipeData(String recipe) {
    recipe = recipe.replaceAll(" ", "+");

    String urlString = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + strMeal;

    try {
        //fetch api
        HttpURLConnection apiConnection = fetchApiResponse(url);

        //check response
        //if 200 its success
        if (apiConnection.getResponseCode() != 200) {
            System.out.println("Error: Could not connect to the API");
            return null;
        }

        //read response and convert store string type
        String jsonResponse = readApiResponse(apiConnection);

        //parse string into json oject
        JSONParser parser = new JSONParser();
        JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);

        //retrieve data
        JSONArray recipeData = (JSONArray) resultsJsonObj.get("results");
        return (JSONObject) recipeData.get(0);

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


private static String readApiResponse(HttpURLConnection apiConnection) {
    try {
        //create stringbuilder to store resulting json data
        StringBuilder resultJson = new StringBuilder();

        //create scanner to read from inputstream
        Scanner scanner = new Scanner(api.Connection.getInputStream());

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
}


//<dependency>
  //<groupId>com.squareup.okhttp3</groupId>
  //<artifactId>okhttp</artifactId>
  //<version>4.12.0</version>
//</dependency>
