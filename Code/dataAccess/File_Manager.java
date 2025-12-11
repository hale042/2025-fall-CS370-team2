package dataAccess;

import note.Note;
import recipe.*;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class File_Manager {
    private final File SAVED_RECIPES;
    String path;
    public File_Manager() {
        path = "./Resources/";
        File directory = new File(path);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        SAVED_RECIPES = new File(directory, "recipes.txt");
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

        if(recipeNames.isEmpty()) {
            return new ArrayList<>();
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
        try (BufferedWriter recipe_bw = new BufferedWriter(new FileWriter(SAVED_RECIPES, true))) {
            recipe_bw.append(recipe.getName()).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteRecipe(Recipe recipe) {
        List<String> lines;

        try {
            lines = Files.readAllLines(SAVED_RECIPES.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        List<String> remaining = new ArrayList<>();
        for (String line : lines) {
            if (!line.trim().equalsIgnoreCase(recipe.getName().trim())) {
                remaining.add(line);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SAVED_RECIPES, false))) {
            for (String name : remaining) {
                bw.write(name);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean saveNote(String title, String contents) {
        title = title.replaceAll("[^a-zA-Z0-9-_ ]", "_");
        File f = new File(this.path + "Notes/", title + ".txt");
        f.getParentFile().mkdirs();
        try (BufferedWriter note_bf = new BufferedWriter(new FileWriter(f, false))) {
            note_bf.write(contents);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Note> fetchNotesList() {
        List<Note> notes = new ArrayList<>();
        File notesDir = new File(this.path + "Notes/");

        // If folder does not exist or is empty
        if (!notesDir.exists() || !notesDir.isDirectory()) {
            return notes;
        }

        File[] files = notesDir.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files == null) {
            return notes;
        }

        for (File file : files) {
            try {
                // Read file contents
                String contents = Files.readString(file.toPath());

                // File name without ".txt"
                String name = file.getName().replaceFirst("\\.txt$", "");

                notes.add(new Note(name, contents));

            } catch (IOException e) {
                e.printStackTrace();
                // Optionally continue or return
            }
        }

        return notes;
    }
    public boolean deleteNote(String title) {
        title = title.replaceAll("[^a-zA-Z0-9-_ ]", "_");

        File f = new File(this.path + "Notes/", title + ".txt");

        if (!f.exists()) {
            return false;
        }

        return f.delete();
    }

    public String getNoteContents(String title){
        title = title.replaceAll("[^a-zA-Z0-9-_ ]", "_");
        StringBuilder contents = new StringBuilder();
        File f = new File(this.path + "Notes/", title + ".txt");
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
