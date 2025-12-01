import recipe.Ingredient;
import recipe.Recipe;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// public class RecipeTab extends TabFrameTemplate implements GUIInterface {
public class RecipeTab extends TabFrameTemplate {
    private CookItGUI mainGUI;

    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 35);
    private final Font labelFont = new Font("Segoe UI", Font.PLAIN, 20);
    private final Font bodyFont = new Font("Segoe UI", Font.PLAIN, 15);

    private JPanel header, leftPanel, instructionsPanel;
    private JButton favoriteButton;
    private JList<String> ingredients;
    private JLabel title, skillLabel, timeLabel, ingredientsLabel;
    JTextArea instructionsArea;
    JScrollPane scrollableIngredientsList, scrollableInstructions;

    String ingredientsArray[];

    private Recipe currentRecipe = new Recipe();

    public RecipeTab(CookItGUI GUI) {
        this.mainGUI = GUI;
    }
    
    @Override
    public void initializeTabContents() {
        // need: title, description, ingredients, and instructions
        mainPanel.setLayout(new BorderLayout(10, 10));

        // header / recipe title(subtitle? with author?)
        header = new JPanel();
        
        // set sample recipe
        // viewRecipe(new recipe.Recipe("", [], "", "", 0));
        sampleRecipe();

        title = new JLabel(currentRecipe.getName());
        title.setFont(titleFont);
        header.add(title);
        
        // recipe contents
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        // leftPanel.setLayout(new GridLayout(3, 1));

        // skills, cooking time, and ingredients
        skillLabel = new JLabel("Skill Level: " + currentRecipe.getSkillLevel());
        skillLabel.setFont(labelFont);
        leftPanel.add(skillLabel);
        
        leftPanel.add(Box.createVerticalStrut(10));
        
        timeLabel = new JLabel("Time: " + currentRecipe.getCookTime() + " minutes");
        timeLabel.setFont(labelFont);
        leftPanel.add(timeLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        
        // ingredients list
        // get the names of each ingredient
        List<String> ingredientStrings = new ArrayList<String>();
        for (Ingredient currIngredient : currentRecipe.getIngredients()) {
            ingredientStrings.add(currIngredient.getName());
        }
        // leftPanel.add(new JLabel("Ingredients: " + String.join(", ", ingredientStrings)));

        ingredientsLabel = new JLabel("Ingredients:");
        ingredientsLabel.setFont(labelFont);
        leftPanel.add(ingredientsLabel);
        leftPanel.add(Box.createVerticalStrut(10));

        ingredientsArray = ingredientStrings.toArray(new String[ingredientStrings.size()]);
        ingredients = new JList<String>(ingredientsArray);
        ingredients.setFont(labelFont);
        // ingredients.setFont(bodyFont);
        scrollableIngredientsList = new JScrollPane(ingredients); // make it scrollable
        leftPanel.add(scrollableIngredientsList, BorderLayout.CENTER);

        // try using a JList?
        // add "favorite recipe" button; should add 

        // favorite button
        favoriteButton = new JButton("Favorite");
        leftPanel.add(favoriteButton);
        
        // instructions
        instructionsPanel = new JPanel(new BorderLayout(10, 10));
        // instructionsPanel.setLayout(new GridLayout(0, 1));
        
        instructionsArea = new JTextArea();
        instructionsArea.setEditable(false);
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setFont(bodyFont);
        instructionsArea.setText(currentRecipe.getInstructions());
        // instructionsPanel.add(instructionsArea, BorderLayout.CENTER);        

        scrollableInstructions = new JScrollPane(instructionsArea); // make it scrollable
        instructionsPanel.add(scrollableInstructions, BorderLayout.CENTER);        
        
        // colors for debugging
        // header.setBackground(Color.RED);
        // leftPanel.setBackground(Color.GREEN);
        // instructionsPanel.setBackground(Color.BLUE);
        
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(instructionsPanel, BorderLayout.CENTER);

        // button bindings
        favoriteButton.addActionListener(e -> favoriteRecipe());
    }

    public void viewRecipe(Recipe recipe) {
        this.currentRecipe = recipe;
        // mainPanel.revalidate();

        if ((title != null) && (skillLabel != null) && (timeLabel != null) && (scrollableIngredientsList != null) && (scrollableInstructions != null)) {
            title.setText(currentRecipe.getName());
    
            skillLabel.setText("Skill Level: " + currentRecipe.getSkillLevel());
    
            timeLabel.setText("Time: " + currentRecipe.getCookTime());
    
            // ingredients
            // get the ingredient names
            List<String> ingredientStrings = new ArrayList<String>();
            for (Ingredient currIngredient : currentRecipe.getIngredients()) {
                ingredientStrings.add(currIngredient.getName());
            }
            // set contents of ingredients list to the ingredient names
            ingredientsArray = ingredientStrings.toArray(new String[ingredientStrings.size()]);
            ingredients.setListData(ingredientsArray);
    
            // instructions
            instructionsArea.setText(currentRecipe.getInstructions());
            // title.setText(currentRecipe.getName());
        }

        // System.out.println(this.currentRecipe.getName() + " - " + this.currentRecipe.getInstructions());
    }

    private void favoriteRecipe() {
        // add recipe to list of favorite recipes
        // needs similar logic to the noteslist; a recipe shouldn't be savable once it's been saved(i.e. no duplicates)
        
        if (currentRecipe.isEmpty()) {
            // System.out.println("Empty recipe.Recipe");
            JOptionPane.showMessageDialog(mainPanel, "Empty recipe.Recipe.");
        } else {
           int foundRecipeIndex = -1;
           for (int i = 0; i < mainGUI.favoriteRecipes.size(); i++) {
            //    System.out.println(mainGUI.favoriteRecipes.get(i).getName() + " - " + currentRecipe.getName());
                // if(currentRecipe.title.equals(mainGUI.favoriteRecipes.get(i).title)) {
                if(currentRecipe.equals(mainGUI.favoriteRecipes.get(i))) {
                    foundRecipeIndex = i;
                }
            }
            
            if (foundRecipeIndex == -1) { // if we didn't find a duplicate recipe, add it to the list
                mainGUI.favoriteRecipes.add(currentRecipe);
                mainGUI.welcomeTab.updateFavoritesList();
            }
            else {
                // recipe is already in the list; don't save
                // probably should notify the user
                JOptionPane.showMessageDialog(mainPanel, "recipe.Recipe already favorited.");
                // System.out.println("recipe.Recipe already favorited");
            }
        }

        // favorite recipes list will also have to be saved in the file system or the database or whatever
    }

    public void sampleRecipe() {
        // source: https://www.inspiredtaste.net/38940/spaghetti-with-meat-sauce-recipe/
        String name = "Spaghetti";
        // List<recipe.Ingredient> ingredients = Arrays.asList(new recipe.Ingredient("water"), new recipe.Ingredient("dry spaghetti noodles"), new recipe.Ingredient("salt"), new recipe.Ingredient("Ground Meat"), new recipe.Ingredient("Tomatoes"));
        List<Ingredient> ingredients = Arrays.asList(new Ingredient("water"), new Ingredient("dry spaghetti noodles"), new Ingredient("salt"), new Ingredient("Ground Meat"), new Ingredient("Tomatoes"));
        // String instructions = "1 - Brown the meat: Heat the oil in a large pot over medium-high heat (we use a Dutch oven). Add the meat and cook until browned, about 8 minutes. Use a wooden spoon to break the meat into smaller crumbles as the meat cooks.\n";
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
