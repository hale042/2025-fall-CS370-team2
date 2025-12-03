import recipe.Ingredient;
import recipe.Recipe;
import appServer.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
// import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
// import javax.swing.JFileChooser;
// import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
import javax.swing.JTextField;
// import javax.swing.SwingConstants;
// import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
// import java.awt.Dimension;
import java.awt.Font;
// import java.awt.Color;
// import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// import java.io.File;

public class SearchTab extends TabFrameTemplate {
    final private int NEWRECIPETABINDEX = 4;
    final private int RECIPETABINDEX = 2;

    private CookItGUI mainGUI;
    private JTextField ingredientField;
    private JComboBox<String> skillFilter;
    private JComboBox<String> timeFilter;
    private JList<String> resultArea = new JList<>();
    private JButton searchButton, clearButton, viewButton, newRecipeButton;
    private RecipeFinder finder;
    private Recipe selectedRecipe;
    private List<Recipe> results;

    private Font tabFont = new Font("Segoe UI", Font.PLAIN, 13);

    public SearchTab(CookItGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @Override
    public void initializeTabContents() {
        // super("Cook It!");
        finder = new RecipeFinder();

        // Layout setup
        mainPanel.setLayout(new BorderLayout(10, 10));
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setPreferredSize(new Dimension(800, 600));

        //  Top panel / "Search Field" panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBorder(new TitledBorder("Search Recipes"));

        ingredientField = new JTextField(25);
        ingredientField.setToolTipText("Enter ingredients (comma-separated)");
        skillFilter = new JComboBox<>(new String[]{"Any Skill", "Beginner", "Intermediate", "Expert"});
        timeFilter = new JComboBox<>(new String[]{"Any Time", "Under 30 min", "30-60 min", "Over 60 min"});

        searchButton = new JButton("🔍 Find Recipes");
        clearButton = new JButton("Clear");

        topPanel.add(new JLabel("Ingredients:"));
        topPanel.add(ingredientField);
        topPanel.add(new JLabel("Skill:"));
        topPanel.add(skillFilter);
        topPanel.add(new JLabel("Time:"));
        topPanel.add(timeFilter);
        topPanel.add(searchButton);
        topPanel.add(clearButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Center panel / search result panel 
        // clearSearch();
        resultArea.setFont(tabFont);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("Matching Recipes"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Right panel / details panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new TitledBorder("Recipe Details"));

        viewButton = new JButton("View Selected Recipe");
        newRecipeButton = new JButton("New Recipe");
        
        rightPanel.add(viewButton);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(newRecipeButton);

        mainPanel.add(rightPanel, BorderLayout.EAST);

        //Event listeners for buttons
        searchButton.addActionListener(e -> findRecipes());
        clearButton.addActionListener(e -> clearSearch());
        // viewButton.addActionListener(e -> showRecipeDetails());
        viewButton.addActionListener(e -> openRecipe());
        // addImageButton.addActionListener(e -> chooseImage());
        newRecipeButton.addActionListener(e -> openNewRecipeTab());

        // pack();
        // setLocationRelativeTo(null);
        // setVisible(true);
        // setupSampleData();  // temporary local data
    }

    // Sample data 
    private void setupSampleData() {
        Recipe r1 = new Recipe("Pancakes",
                Arrays.asList(new Ingredient("flour"), new Ingredient("egg"), new Ingredient("milk")),
                "1. Mix ingredients.\n2. Fry on a pan until golden.\n3. Serve hot.",
                "Beginner", 15);
        Recipe r2 = new Recipe("Scrambled Eggs",
                Arrays.asList(new Ingredient("egg"), new Ingredient("butter")),
                "1. Whisk eggs.\n2. Melt butter.\n3. Stir until fluffy.",
                "Beginner", 10);
        Recipe r3 = new Recipe("Beef Stir Fry",
                Arrays.asList(new Ingredient("beef"), new Ingredient("soy sauce"), new Ingredient("onion")),
                "1. Slice beef and onions.\n2. Stir fry in soy sauce.\n3. Serve with rice.",
                "Intermediate", 25);

        finder.addRecipe(r1);
        finder.addRecipe(r2);
        finder.addRecipe(r3);

        results = Arrays.asList(r1, r2, r3);
        displayResults();
    }

    // Core methods 
    private void findRecipes() {
        String inputText = ingredientField.getText().trim().toLowerCase();
        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "Please enter at least one ingredient.");
            return;
        }

        List<String> inputList = Arrays.asList(inputText.split(",\\s*"));
        String skill = skillFilter.getSelectedItem().toString();
        String time = timeFilter.getSelectedItem().toString();

        results = finder.findRecipes(inputList, skill, time);
        displayResults();
    }

    private void displayResults() {
        // resultArea.setText("");
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(mainPanel, "No recipes found with those ingredients or filters.");
        } else {
            List<String> temp = new ArrayList<String>();
            for (Recipe recipe : results) {
                temp.add(recipe.getName());
            }
            String recipeNames[] = temp.toArray(new String[temp.size()]);
            resultArea.setListData(recipeNames);
        }
    }

    private void clearSearch() {
        ingredientField.setText("");

        String temp[] = {};
        resultArea.setListData(temp);

        selectedRecipe = null;
    }

    private void openNewRecipeTab() {
        mainGUI.switchTab(NEWRECIPETABINDEX);
    }

    private void openRecipe() {
        selectedRecipe = new Recipe();
        
        int recipeIndex = resultArea.getSelectedIndex();

        if (recipeIndex == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Please select a recipe first.");
        }
        else {
            selectedRecipe = results.get(recipeIndex);

            mainGUI.recipeTab.viewRecipe(selectedRecipe);
            mainGUI.switchTab(RECIPETABINDEX);
        }

    }

    public static void main(String args[]) {}
}