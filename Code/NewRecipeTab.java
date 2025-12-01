import recipe.Ingredient;
import recipe.Recipe;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
// import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;

public class NewRecipeTab extends TabFrameTemplate {
    private CookItGUI mainGUI;
    private final int SEARCHTABINDEX = 1;
    
    // private recipe.Recipe newRecipe = new recipe.Recipe(null, null, null, null, 0);
    
    private JPanel titlePanel, ingredientsPanel, instructionsPanel, skillNTimePanel, controlsPanel;
    private JTextArea ingredientsInputArea, instructionsInputArea;
    private JTextField titleInput;
    private JButton saveButton, clearButton, cancelButton;
    // private JComboBox<String> skillFilter, timeFilter;
    private JComboBox<String> skillFilter;
    private JSpinner timeFilter;

    private Font tabFont = new Font("Segoe UI", Font.PLAIN, 15);

    public NewRecipeTab(CookItGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @Override
    public void initializeTabContents() {
        /*
            add the panels with border names, add input fields and buttons to panels, add fxn that returns recipe given the inputed strings
        */
        // mainPanel.setLayout(new GridLayout(5, 1));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // title
        titlePanel = new JPanel(new BorderLayout(10, 10));
        titlePanel.setBorder(new TitledBorder("recipe.Recipe Title"));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10)); // set max height for title panel

        titleInput = new JTextField();
        titleInput.setFont(tabFont);
        // titleInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, requiredMaxHeigh));
        // titleInput.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        titlePanel.add(titleInput, BorderLayout.CENTER);

        mainPanel.add(titlePanel);

        // skill and time input
        skillNTimePanel = new JPanel();

        skillFilter = new JComboBox<>(new String[]{"Beginner", "Intermediate", "Expert"});
        // timeFilter = new JComboBox<>(new String[]{"Under 30 min", "30-60 min", "Over 60 min"});
        // timeFilter = new JSpinner();
        timeFilter = new JSpinner(new SpinnerNumberModel(30, 1, 180, 1));
        // timeFilter.setBounds(70, 70, 50, 40);
        
        skillNTimePanel.add(new JLabel("Skill Level: "));
        skillNTimePanel.add(skillFilter);
        skillNTimePanel.add(new JLabel("Time(in minutes): "));
        skillNTimePanel.add(timeFilter);

        mainPanel.add(skillNTimePanel);
        
        // ingredients
        ingredientsPanel = new JPanel(new BorderLayout(10, 10));
        ingredientsPanel.setBorder(new TitledBorder("Ingredients"));
        
        ingredientsInputArea = new JTextArea();
        ingredientsInputArea.setFont(tabFont);
        ingredientsPanel.add(ingredientsInputArea, BorderLayout.CENTER);
        
        mainPanel.add(ingredientsPanel);
        
        // instructions
        instructionsPanel = new JPanel(new BorderLayout(10, 10));
        instructionsPanel.setBorder(new TitledBorder("Instructions"));
        
        instructionsInputArea = new JTextArea();
        instructionsInputArea.setFont(tabFont);
        instructionsPanel.add(instructionsInputArea, BorderLayout.CENTER);

        mainPanel.add(instructionsPanel);
        
        // controls
        controlsPanel = new JPanel();

        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
        cancelButton = new JButton("Cancel");
        controlsPanel.add(saveButton);
        controlsPanel.add(clearButton);
        controlsPanel.add(cancelButton);
        
        mainPanel.add(controlsPanel);

        // button bindings
        saveButton.addActionListener(e -> saveRecipe());
        clearButton.addActionListener(e -> clear());
        cancelButton.addActionListener(e -> mainGUI.switchTab(SEARCHTABINDEX)); // cancel button to go back to the search page
    }

    private void saveRecipe() {
        String recipeName = titleInput.getText();
        String recipeSkill = skillFilter.getSelectedItem().toString();
        int recipeTime = ((int)timeFilter.getValue());
        // int  recipeTime = timeFilter.getValue();
        // System.out.println(timeFilter.getValue() + " - " + recipeTime);
        String instructionsString = instructionsInputArea.getText();
        
        // String regex = "[,\\.\\s]";
        String[] ingredientStrings = ingredientsInputArea.getText().split("\n");
        
        List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
        for (String str : ingredientStrings) {
            // System.out.println(str);
            ingredientsList.add(new Ingredient(str));
        }
        
        // newRecipe = new recipe.Recipe(recipeName, ingredientsList, instructionsString, recipeSkill, 0);
        Recipe recipeToSave = new Recipe(recipeName, ingredientsList, instructionsString, recipeSkill, recipeTime);
        // recipe.Recipe recipe = new recipe.Recipe(recipeName, ingredients, instructionsString, recipeSkill, recipeTime); // figure out the time thing
        // System.out.println(newRecipe.getName());

        // then "return" recipe i.e. send it to the file management system
        // do a try on the save function? give a popup if error, else signal success and go back to search page
        mainGUI.switchTab(SEARCHTABINDEX); // switch back to the recipe search page
    }

    private void clear() {
        titleInput.setText("");
        skillFilter.setSelectedItem("Beginner");
        timeFilter.setValue(30);
        ingredientsInputArea.setText("");
        instructionsInputArea.setText("");
    }
}
