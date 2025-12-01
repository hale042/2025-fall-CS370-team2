package dataAccess;

import cookit.Recipe;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class File_Manager {
    private final File SAVED_RECIPES;
    String path;
    public File_Manager() {
        path = "./Code/Resources/";
        SAVED_RECIPES = new File(path, "recipes.txt");
        try {
            SAVED_RECIPES.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> fetchRecipeList() {
        List<String> recipeNames = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(SAVED_RECIPES))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    recipeNames.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Database_Manager db = new Database_Manager();

        return db.fetchRecipeList(recipeNames);
    }

    public boolean saveRecipe(Recipe recipe) {
        Database_Manager db = new Database_Manager();
        if(db.fetchRecipeList(Arrays.asList(recipe.getName())).isEmpty()){
            return false;
        }
        List<String> recipe_names = new ArrayList<>();
        try (BufferedReader recipe_br = new BufferedReader(new FileReader(SAVED_RECIPES))) {
            String line;
            while ((line = recipe_br.readLine()) != null) {
                recipe_names.add(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        if(recipe_names.contains(recipe.getName())){
            return true;
        }
        try (BufferedWriter recipe_bw = new BufferedWriter(new FileWriter(SAVED_RECIPES, true))) {
            recipe_bw.append(recipe.getName()).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean saveNote(String title, String contents) {
        title = title.replaceAll("[^a-zA-Z0-9-_ ]", "_");
        File f = new File(this.path, title + ".txt");
        f.getParentFile().mkdirs();
        try (BufferedWriter note_bf = new BufferedWriter(new FileWriter(f, false))) {
            note_bf.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public String getNoteContents(String title){
        title = title.replaceAll("[^a-zA-Z0-9-_ ]", "_");
        StringBuilder contents = new StringBuilder();
        File f = new File(this.path, title + ".txt");
        if (!f.exists()) return "";
        try (BufferedReader note_br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = note_br.readLine()) != null) {
                contents.append(line).append("\n");
            }
            if (!contents.isEmpty()) {
                contents.setLength(contents.length() - 1);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return contents.toString();
    }

    public static void main(String[] args){
    }
}
