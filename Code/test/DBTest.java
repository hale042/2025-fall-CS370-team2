package test;

import recipe.*;
import dataAccess.Database_Manager;

import java.util.Arrays;
import java.util.List;

public class DBTest {
    public static void main(String[] args) {
        Database_Manager db1 = new Database_Manager("test_table");

        Ingredient oats = new Ingredient("oats");
        Ingredient walnuts = new Ingredient("walnuts");
        Ingredient banana = new Ingredient("banana");
        Ingredient flour = new Ingredient("flour");
        Ingredient sugar = new Ingredient("sugar");
        Ingredient baking_powder = new Ingredient("baking powder");
        Ingredient apple = new Ingredient("apple");
        Ingredient butter = new Ingredient("butter");
        Recipe recipe = new Recipe("oatmeal", Arrays.asList(oats, walnuts, banana), "1. step 1\n2. step 2\n", "beginner", 30);
        db1.saveRecipe(recipe);
        Recipe recipe1 = new Recipe("banana bread", Arrays.asList(flour, sugar, walnuts, banana), "1. step 1\n2. step 2\n", "beginner", 20);
        db1.saveRecipe(recipe1);
        Recipe recipe2 = new Recipe("bread", Arrays.asList(flour, sugar, baking_powder), "1. step 1\n2. step 2\n", "beginner", 20);
        db1.saveRecipe(recipe2);
        Recipe recipe3 = new Recipe("apple pie", Arrays.asList(flour, sugar, apple, butter), "1. step 1\n2. step 2\n", "intermediate", 90);
        db1.saveRecipe(recipe3);

        List<Recipe> recipeList = db1.fetchRecipeList();
        for(Recipe r : recipeList) System.out.println(r.getName());
        System.out.println();
        recipeList = db1.fetchRecipeList(List.of("sugar"), "beginner", "under 30");
        for(Recipe r : recipeList) System.out.println(r.getName());
        System.out.println();
        recipeList = db1.fetchRecipeList(List.of("walnuts", "banana"), "any skill", "30-60");
        for(Recipe r : recipeList) System.out.println(r.getName());
        System.out.println();
        recipeList = db1.fetchRecipeList(List.of(), "any skill", "over 60");
        for(Recipe r : recipeList) System.out.println(r.getName());
        System.out.println();
    }

}
