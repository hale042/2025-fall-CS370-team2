package test;

import cookit.Recipe;
import dataAccess.Database_Manager;
import dataAccess.File_Manager;

import java.util.Arrays;
import java.util.List;

public class FileTest {
    public static void main(String[] args) {
        Database_Manager db1 = new Database_Manager();

        Recipe recipe = new Recipe("oatmeal", Arrays.asList("oats", "walnuts", "banana"), "1. step 1\n2. step 2\n", "beginner", 30);
        db1.saveRecipe(recipe);
        Recipe recipe1 = new Recipe("banana bread", Arrays.asList("flour", "sugar", "walnuts", "banana"), "1. step 1\n2. step 2\n", "beginner", 20);
        db1.saveRecipe(recipe1);
        Recipe recipe2 = new Recipe("bread", Arrays.asList("flour", "sugar", "baking_powder"), "1. step 1\n2. step 2\n", "beginner", 20);
        db1.saveRecipe(recipe2);
        Recipe recipe3 = new Recipe("apple pie", Arrays.asList("flour", "sugar", "apple", "butter"), "1. step 1\n2. step 2\n", "intermediate", 90);
        db1.saveRecipe(recipe3);

        Recipe recipe4 = new Recipe("burrito", Arrays.asList("tortilla", "beans"), "1.2.3.", "beginner", 15);

        File_Manager file_manager = new File_Manager();
        file_manager.saveRecipe(recipe1);
        file_manager.saveRecipe(recipe2);
        file_manager.saveRecipe(recipe3);
        file_manager.saveRecipe(recipe4);
        file_manager.saveRecipe(recipe1);

        List<Recipe> recipeList = file_manager.fetchRecipeList();

        for( Recipe r : recipeList) System.out.println(r.getName());

        String contents = "line 1\nline 2\nline3\n";
        String title1 = "note 1";
        String title2 = "n/ote 2";
        file_manager.saveNote(title1, contents);
        file_manager.saveNote(title2, contents);
        System.out.println(title1);
        System.out.println(file_manager.getNoteContents(title1));
        System.out.println(title2);
        System.out.println(file_manager.getNoteContents(title2));
    }
}
