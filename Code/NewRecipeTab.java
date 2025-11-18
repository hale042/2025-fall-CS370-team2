import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class NewRecipeTab extends TabFrameTemplate {
    JPanel titlePanel, ingredientsPanel, instructionsPanel, controlsPanel;
    JTextArea ingredientsInputArea, instructionsInputArea;
    JTextField titleInput;
    JButton saveButton, clearButton;

    @Override
    public void initializeTabContents() {
        /*
            add the panels with border names, add input fields and buttons to panels, add fxn that returns recipe given the inputed strings
        */
        mainPanel.setLayout(new GridLayout(4, 1));

        // title
        titlePanel = new JPanel(new BorderLayout(10, 10));
        titlePanel.setBorder(new TitledBorder("Recipe Title"));

        titleInput = new JTextField();
        titlePanel.add(titleInput, BorderLayout.CENTER);

        mainPanel.add(titlePanel);
        
        // ingredients
        ingredientsPanel = new JPanel(new BorderLayout(10, 10));
        ingredientsPanel.setBorder(new TitledBorder("Ingredients"));
        
        ingredientsInputArea = new JTextArea();
        ingredientsPanel.add(ingredientsInputArea, BorderLayout.CENTER);
        
        mainPanel.add(ingredientsPanel);
        
        // instructions
        instructionsPanel = new JPanel(new BorderLayout(10, 10));
        instructionsPanel.setBorder(new TitledBorder("Instructions"));
        
        instructionsInputArea = new JTextArea();
        instructionsPanel.add(instructionsInputArea, BorderLayout.CENTER);

        mainPanel.add(instructionsPanel);
        
        // controls
        controlsPanel = new JPanel();

        saveButton = new JButton("Save");
        clearButton = new JButton("Clear");
        controlsPanel.add(saveButton);
        controlsPanel.add(clearButton);
        
        mainPanel.add(controlsPanel);

        // button bindings
        saveButton.addActionListener(e -> saveRecipe());
        clearButton.addActionListener(e -> clear());
    }

    private void saveRecipe() {
        String recipeName = titleInput.getText();
        
        Recipe recipe = new Recipe(recipeName, null, null, null, 0);

        System.out.println(recipe.getName());

        // send recipe to the file management system
        // should also probably switch back to the recipe search page
        // return recipe;
    }

    private void clear() {
        titleInput.setText("");
        ingredientsInputArea.setText("");
        instructionsInputArea.setText("");
    }
}
