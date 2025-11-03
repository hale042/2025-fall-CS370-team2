import java.util.*;

public class RecipeFinder {
    private List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public List<Recipe> findRecipes(List<String> availableIngredients, String skill, String timeFilter) {
        List<Recipe> matched = new ArrayList<>();

        for (Recipe recipe : recipes) {
            boolean canMake = recipe.getIngredients().stream()
                    .allMatch(ing -> availableIngredients.contains(ing.getName()));

            if (!canMake) continue;

            // Filter by skill
            if (!skill.equals("Any Skill") && !recipe.getSkillLevel().equalsIgnoreCase(skill))
                continue;

            // Filter by time
            int time = recipe.getCookTime();
            if (timeFilter.equals("Under 30 min") && time >= 30) continue;
            if (timeFilter.equals("30-60 min") && (time < 30 || time > 60)) continue;
            if (timeFilter.equals("Over 60 min") && time <= 60) continue;

            matched.add(recipe);
        }
        return matched;
    }
}

