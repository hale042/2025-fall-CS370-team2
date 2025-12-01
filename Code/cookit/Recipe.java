package cookit;

import java.util.*;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private String instructions;
    private String skillLevel;
    private int cookTime; // in minutes

    public Recipe(String name, List<String> ingredients, String instructions, String skillLevel, int cookTime) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.skillLevel = skillLevel;
        this.cookTime = cookTime;
    }

    public String getName() { return name; }
    public List<String> getIngredients() { return ingredients; }
    public String getInstructions() { return instructions; }
    public String getSkillLevel() { return skillLevel; }
    public int getCookTime() { return cookTime; }

    public String getIngredientList() {
        StringBuilder sb = new StringBuilder();
        for (String ing : ingredients) sb.append("• ").append(ing).append("\n");
        return sb.toString();
    }

    public String ingredientsToString() {
        StringBuilder sb = new StringBuilder();
        for (String ing : ingredients) sb.append(ing).append(" ");
        return sb.toString();
    }

    public void printRecipe() {
        System.out.println(this.getName());
        System.out.println(this.ingredientsToString());
        System.out.println(this.getInstructions());
        System.out.println(this.getSkillLevel());
        System.out.println(this.getCookTime());
    }
}

