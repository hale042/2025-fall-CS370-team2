package dataAccess;

import cookit.Recipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class File_Manager {
    private File savedRecipes;
    private File savedNotes;

    public File_Manager() {
        savedNotes = new File("./Code/Resources/notes.txt");
        savedRecipes = new File("./Code/Resources/recipes.txt");
        try {
            savedNotes.createNewFile();
            savedRecipes.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> fetchRecipeList() {
        List<Recipe> recipes = new ArrayList<>();

        return recipes;
    }

    public boolean saveRecipe(Recipe recipe) {

        return true;
    }

    public boolean saveNote(String title, String contents) {

        return true;
    }

    public static void main(String[] args){
        File_Manager fm = new File_Manager();
    }
}
