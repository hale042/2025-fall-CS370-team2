import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class WelcomeTab extends TabFrameTemplate {
    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 35);
    private final Font bodyFont = new Font("Segoe UI", Font.PLAIN, 15);
    
    private CookItGUI mainGUI;

    private String welcomeBlurb = "Welcome to CookIt! This is the \"How to\" / Welcome Blurb!";

    private JPanel titlePanel, blurbPanel, favoritesPanel, buttonsPanel;
    private JLabel title, introBlurb, noFavoritesLabel;
    private JButton openButton, deleteButton;
    private JList<String> favoritesJList = new JList<>();
    private JScrollPane scrollableFavorites = new JScrollPane();

    public WelcomeTab(CookItGUI GUI) {
        this.mainGUI = GUI;
    }

    @Override
    public void initializeTabContents() {
        // mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 
        titlePanel = new JPanel(new BorderLayout(10, 10));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45)); // set max height of panel

        title = new JLabel("Welcome to CookIt!");
        title.setFont(titleFont);
        title.setHorizontalAlignment(JLabel.CENTER);

        titlePanel.add(title, BorderLayout.CENTER);
        mainPanel.add(titlePanel);

        // welcome / "how to" blurb
        blurbPanel = new JPanel(new BorderLayout());
        // blurbPanel.setBorder(new TitledBorder("Welcome to CookIt!"));

        introBlurb = new JLabel(welcomeBlurb);
        introBlurb.setFont(bodyFont);
        introBlurb.setHorizontalAlignment(JLabel.CENTER);

        blurbPanel.add(introBlurb, BorderLayout.CENTER);
        mainPanel.add(blurbPanel);

        // list of favorite recipes
        favoritesPanel = new JPanel(new BorderLayout(10, 10));
        favoritesPanel.setBorder(new TitledBorder("Your Favorite Recipes"));

        noFavoritesLabel = new JLabel("No Favorited Recipes");
        noFavoritesLabel.setFont(bodyFont);
        noFavoritesLabel.setHorizontalAlignment(JLabel.CENTER);
        noFavoritesLabel.setVisible(true);
        
        updateFavoritesList();
        favoritesJList.setFont(bodyFont);

        scrollableFavorites = new JScrollPane(favoritesJList); // make it scrollable
        scrollableFavorites.setVisible(false);

        // buttons
        buttonsPanel = new JPanel();

        openButton = new JButton("Open");
        deleteButton = new JButton("Delete");

        buttonsPanel.add(openButton);
        buttonsPanel.add(deleteButton);

        favoritesPanel.add(noFavoritesLabel, BorderLayout.NORTH);
        favoritesPanel.add(scrollableFavorites, BorderLayout.CENTER);
        favoritesPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // mainPanel.add(scrollableFavorites, BorderLayout.CENTER);
        mainPanel.add(favoritesPanel);

        // button bindings
        openButton.addActionListener(e -> openRecipe());
        deleteButton.addActionListener(e -> unfavoriteRecipe());
    }

    public void updateFavoritesList() {
        // savedNotes = Notes;
        // mainGUI.favoriteRecipes

        // if there are no recipes in the list, notify the user with a label
        if (mainGUI.favoriteRecipes.isEmpty()) {
            noFavoritesLabel.setVisible(true);
            scrollableFavorites.setVisible(false);
        }
        else {
            List<String> temp = new ArrayList<>();
            for (Recipe recipe : mainGUI.favoriteRecipes) {
                temp.add(recipe.getName());
            }
            String favoriteRecipeNames[] = temp.toArray(new String[temp.size()]);
            
            favoritesJList.setListData(favoriteRecipeNames);
            
            noFavoritesLabel.setVisible(false);
            scrollableFavorites.setVisible(true);
        }
        // get the names of each recipe in the favorites list and set the displayed list to its contents

    }

    public void unfavoriteRecipe() {
        // delete the recipe selected from the list and update the displayed list
        int recipeIndex = favoritesJList.getSelectedIndex();
        // String recipeName = (String) favoritesJList.getSelectedValue();
        // System.out.println("Selected note index: " + recipeIndex + " and title: " + recipeName);
        
        if (recipeIndex == -1) {
            // System.out.println("No Recipe Selected");
            JOptionPane.showMessageDialog(this.mainPanel, "No Recipe Selected.");
        }
        else {
            mainGUI.favoriteRecipes.remove(recipeIndex);
            updateFavoritesList();
        }
    }
    
    public void openRecipe() {
        int recipeIndex = favoritesJList.getSelectedIndex();
        
        if (recipeIndex == -1) {
            // System.out.println("No Recipe Selected");
            JOptionPane.showMessageDialog(this.mainPanel, "No Recipe Selected.");
        }
        else {
            Recipe selectedRecipe = mainGUI.favoriteRecipes.get(recipeIndex);
            mainGUI.recipeTab.viewRecipe(selectedRecipe);
            mainGUI.switchTab(2);
        }
    }
}
