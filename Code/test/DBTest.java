package test;

import cookit.Recipe;
import dataAccess.Database_Manager;

import java.util.Arrays;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        Database_Manager db1 = new Database_Manager();
        Recipe recipe = new Recipe("oatmeal", Arrays.asList("oats", "walnuts", "banana"), "1. step 1\n2. step 2\n", "Level 3", 30);
        db1.saveRecipe(recipe);
        List<Recipe> recipeList = db1.fetchRecipeList();
        Recipe recipe1 = recipeList.getLast();

        recipe1.printRecipe();
    }
}
