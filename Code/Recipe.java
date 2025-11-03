import java.util.*;

public class Recipe {
    private String name;
    private List<Ingredient> ingredients;
    private String instructions;
    private String skillLevel;
    private int cookTime; // in minutes

    public Recipe(String name, List<Ingredient> ingredients, String instructions, String skillLevel, int cookTime) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.skillLevel = skillLevel;
        this.cookTime = cookTime;
    }

    public String getName() { return name; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public String getInstructions() { return instructions; }
    public String getSkillLevel() { return skillLevel; }
    public int getCookTime() { return cookTime; }

    public String getIngredientList() {
        StringBuilder sb = new StringBuilder();
        for (Ingredient ing : ingredients) sb.append("• ").append(ing.getName()).append("\n");
        return sb.toString();
    }
}

