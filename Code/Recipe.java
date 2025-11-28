import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private List<Ingredient> ingredients;
    // private String instructions;
    private String instructions;
    private String skillLevel;
    private int cookTime; // in minutes

    // public Recipe() {
    //     this.name = null;
    //     this.ingredients = new ArrayList<Ingredient>();
    //     this.instructions = null;
    //     this.skillLevel = null;
    //     this.cookTime = 0;
    // }

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
    
    public boolean isEmpty() {
        // return (title == null && contents == null) || (title == "" && contents == "");
        // return (name.isBlank() && ingredients.isEmpty() && instructions.isBlank() && (cookTime == 0) && skillLevel.isBlank());
        return ((name == null) && (ingredients == null) && (instructions == null) && (cookTime == 0) && (skillLevel == null));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Note)) {
            return false;
        }

        Recipe recipeObj = (Recipe) obj;
        return (recipeObj.getName().equals(name) && recipeObj.getIngredients().equals(ingredients) && recipeObj.getInstructions().equals(instructions) && (recipeObj.getCookTime() == cookTime) && recipeObj.getSkillLevel().equals(skillLevel));
    }
}

