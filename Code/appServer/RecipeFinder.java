package appServer;

import dataAccess.Database_Manager;
import recipe.*;


import java.util.ArrayList;
import java.util.List;

public class RecipeFinder {

    private final Database_Manager DB;


    public RecipeFinder(){
        DB = new Database_Manager();
    }

    public void addRecipe(Recipe recipe){
        DB.saveRecipe(recipe);
    }

    public List<Recipe> findRecipes(List<String> ingredients, String skillLevel, String cookTime){
        return DB.fetchRecipeList(ingredients, skillLevel, cookTime);
    }
    public List<Recipe> findRecipes(List<String> ingredients){
        return DB.fetchRecipeList(ingredients);
    }
}
