import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RecipeTab extends TabFrameTemplate {
    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 35);
    private final Font bodyFont = new Font("Segoe UI", Font.PLAIN, 15);
    private final String html = "<html><body style='width: %1spx'>%1s";

    private JPanel header, body;

    private Recipe currentRecipe;
    
    @Override
    public void initializeTabContents() {
        // need: title, description, ingredients, and instructions
        mainPanel.setLayout(new BorderLayout(10, 10));

        sampleRecipe();

        // header / recipe title(subtitle? with author?)
        header = new JPanel();

        JLabel title = new JLabel(currentRecipe.getName());
        title.setFont(titleFont);
        header.add(title);
        
        mainPanel.add(header, BorderLayout.NORTH);
        
        // body / recipe contents
        body = new JPanel();
        // body.setLayout(new FlowLayout());
        // body.setLayout(new FlowLayout(FlowLayout.CENTER));
        body.setLayout(new BoxLayout(body, BoxLayout.PAGE_AXIS));
        
        JPanel ingredientsPanel = new JPanel();
        // ingredientsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.PAGE_AXIS));
        // ingredientsPanel.setBackground(Color.BLUE);
        
        for (Ingredient currIngredient : currentRecipe.getIngredients()) {
            JLabel ingredientLabel = new JLabel(currIngredient.getName() + " - " + currIngredient.getAmount() + "\n", JLabel.CENTER);
            ingredientLabel.setFont(bodyFont);
            ingredientsPanel.add(ingredientLabel);
        }
        // JLabel ingredientsLabel = new JLabel();
        body.add(ingredientsPanel);
        
        JPanel instructionsPanel = new JPanel();
        // instructionsPanel.setBackground(Color.BLUE);

        // instructionsPanel.setLayout(new BoxLayout(instructionsPanel, BoxLayout.Y_AXIS));
        JLabel instructionsLabel = new JLabel(String.format(html, 1000, currentRecipe.getInstructions()), JLabel.CENTER);
        instructionsLabel.setFont(bodyFont);
        

        instructionsPanel.add(instructionsLabel);
        body.add(instructionsPanel);
        
        mainPanel.add(body, BorderLayout.CENTER);
    }

    public void viewRecipe(Recipe recipe) {
        this.currentRecipe = recipe;

        // System.out.println(this.currentRecipe.getName() + " - " + this.currentRecipe.getInstructions());
    }

    public void sampleRecipe() {
        String name = "Spaghetti";
        // List<Ingredient> ingredients = Arrays.asList(new Ingredient("water"), new Ingredient("dry spaghetti noodles"), new Ingredient("salt"), new Ingredient("Ground Meat"), new Ingredient("Tomatoes"));
        List<Ingredient> ingredients = Arrays.asList(new Ingredient("water"), new Ingredient("dry spaghetti noodles"), new Ingredient("salt"), new Ingredient("Ground Meat"), new Ingredient("Tomatoes"));
        String instructions = "1 - Brown the meat: Heat the oil in a large pot over medium-high heat (we use a Dutch oven). Add the meat and cook until browned, about 8 minutes. Use a wooden spoon to break the meat into smaller crumbles as the meat cooks.\n" + //
                        "2 - Build the sauce: Add the onions and cook, stirring every once in a while, until softened, about 5 minutes. Stir in the garlic, tomato paste, oregano, and red pepper flakes and cook, stirring continuously for about 1 minute.\n" + //
                        "3 - Add liquid and tomatoes: Pour in the water and use a wooden spoon to scrape up any bits of meat or onion stuck to the bottom of the pot. Stir in the tomatoes, ¾ teaspoon of salt, and a generous pinch of black pepper.\n" + //
                        "4 - Simmer: Bring the sauce to a low simmer. Cook uncovered for 25 minutes. As it cooks, stir and taste the sauce a few times so you can adjust the seasoning accordingly (see notes for seasoning suggestions).\n" + //
                        "5 - Cook spaghetti: About 15 minutes before the spaghetti sauce finishes cooking, bring a large pot of salted water to a boil. Then, cook the pasta according to the package directions, but check for doneness a minute or two before the suggested cooking time. Drain.\n" + //
                        "6 - To serve: Remove the sauce from the heat and stir in the basil. Toss in the cooked pasta and leave for a minute so that it absorbs some of the sauce. Toss again, and then serve with grated parmesan cheese on top.";
        String skillLevel = "Beginner";
        int cookTime = 8;

        Recipe sampleRecipe = new Recipe(name, ingredients, instructions, skillLevel, cookTime);

        viewRecipe(sampleRecipe);
    }
}
