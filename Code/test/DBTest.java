package test;

import cookit.Recipe;
import dataAccess.Database_Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        Database_Manager db1 = new Database_Manager();
        if(db1.fetchRecipeList().isEmpty()){
            Recipe recipe = new Recipe("oatmeal", Arrays.asList("oats", "walnuts", "banana"), "1. step 1\n2. step 2\n", "beginner", 30);
            db1.saveRecipe(recipe);
            Recipe recipe1 = new Recipe("banana bread", Arrays.asList("flour", "sugar", "walnuts", "banana"), "1. step 1\n2. step 2\n", "beginner", 20);
            db1.saveRecipe(recipe1);
            Recipe recipe2 = new Recipe("bread", Arrays.asList("flour", "sugar", "baking_powder"), "1. step 1\n2. step 2\n", "beginner", 20);
            db1.saveRecipe(recipe2);
        }

        List<Recipe> recipeList = db1.fetchRecipeList();
        for(Recipe r : recipeList) System.out.println(r.getName());
        System.out.println();
        recipeList = db1.fetchRecipeList(List.of("sugar"));
        for(Recipe r : recipeList) System.out.println(r.getName());
        System.out.println();
        recipeList = db1.fetchRecipeList(List.of("walnuts", "banana"));
        for(Recipe r : recipeList) System.out.println(r.getName());
        System.out.println();
    }

}
