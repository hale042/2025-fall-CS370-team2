import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
// import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;

import java.util.Arrays;
import java.util.List;

import java.io.File;

public class SearchTab extends TabFrameTemplate {
    final private int NEWRECIPETABINDEX = 4;

    private CookItGUI mainGUI;
    private JTextField ingredientField;
    private JComboBox<String> skillFilter;
    private JComboBox<String> timeFilter;
    private JTextArea resultArea;
    private JButton searchButton, clearButton, viewButton, addImageButton, newRecipeButton;
    private JLabel imageLabel;
    private RecipeFinder finder;
    private Recipe selectedRecipe;

    private Font tabFont = new Font("Segoe UI", Font.PLAIN, 13);

    public SearchTab(CookItGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    @Override
    public void initializeTabContents() {
        // super("Cook It!");
        finder = new RecipeFinder();
        setupSampleData();  // temporary local data

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
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(tabFont);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("Matching Recipes"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Right panel / details panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(new TitledBorder("Recipe Details"));

        viewButton = new JButton("View Selected Recipe");
        addImageButton = new JButton("Upload PNG");
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 200));
        imageLabel.setBorder(new LineBorder(Color.GRAY));
        newRecipeButton = new JButton("New Recipe");
        
        rightPanel.add(viewButton);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(addImageButton);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(imageLabel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(newRecipeButton);

        mainPanel.add(rightPanel, BorderLayout.EAST);

        //Event listeners for buttons
        searchButton.addActionListener(e -> findRecipes());
        clearButton.addActionListener(e -> clearSearch());
        viewButton.addActionListener(e -> showRecipeDetails());
        addImageButton.addActionListener(e -> chooseImage());
        newRecipeButton.addActionListener(e -> openNewRecipeTab());

        // pack();
        // setLocationRelativeTo(null);
        // setVisible(true);
    }

    // Sample data 
    private void setupSampleData() {
        finder.addRecipe(new Recipe("Pancakes",
                Arrays.asList(new Ingredient("flour"), new Ingredient("egg"), new Ingredient("milk")),
                "1. Mix ingredients.\n2. Fry on a pan until golden.\n3. Serve hot.",
                "Beginner", 15));

        finder.addRecipe(new Recipe("Scrambled Eggs",
                Arrays.asList(new Ingredient("egg"), new Ingredient("butter")),
                "1. Whisk eggs.\n2. Melt butter.\n3. Stir until fluffy.",
                "Beginner", 10));

        finder.addRecipe(new Recipe("Beef Stir Fry",
                Arrays.asList(new Ingredient("beef"), new Ingredient("soy sauce"), new Ingredient("onion")),
                "1. Slice beef and onions.\n2. Stir fry in soy sauce.\n3. Serve with rice.",
                "Intermediate", 25));
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

        List<Recipe> results = finder.findRecipes(inputList, skill, time);
        displayResults(results);
    }

    private void displayResults(List<Recipe> results) {
        resultArea.setText("");
        if (results.isEmpty()) {
            resultArea.setText(" No recipes found with those ingredients or filters.");
        } else {
            resultArea.append(" Recipes you can make:\n\n");
            for (int i = 0; i < results.size(); i++) {
                Recipe r = results.get(i);
                resultArea.append((i + 1) + ". " + r.getName() +
                        " (" + r.getSkillLevel() + ", " + r.getCookTime() + " min)\n");
            }

            String choice = JOptionPane.showInputDialog("Enter recipe number to select:");
            if (choice != null && !choice.isEmpty()) {
                try {
                    int index = Integer.parseInt(choice) - 1;
                    if (index >= 0 && index < results.size()) {
                        selectedRecipe = results.get(index);
                        JOptionPane.showMessageDialog(mainPanel, "Selected: " + selectedRecipe.getName());
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
    }

    private void clearSearch() {
        ingredientField.setText("");
        resultArea.setText("");
        selectedRecipe = null;
        imageLabel.setIcon(null);
    }

    private void showRecipeDetails() {
        if (selectedRecipe == null) {
            JOptionPane.showMessageDialog(mainPanel, "Please select a recipe first.");
            return;
        }

        JTextArea details = new JTextArea(
                "🍽️ " + selectedRecipe.getName() + "\n\n" +
                "Skill: " + selectedRecipe.getSkillLevel() + "\n" +
                "Cook Time: " + selectedRecipe.getCookTime() + " minutes\n\n" +
                "Ingredients:\n" + selectedRecipe.getIngredientList() + "\n\n" +
                "Instructions:\n" + selectedRecipe.getInstructions()
        );
        details.setEditable(false);
        details.setCaretPosition(0);

        JScrollPane pane = new JScrollPane(details);
        pane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(mainPanel, pane, "Recipe Details", JOptionPane.PLAIN_MESSAGE);
    }

    private void chooseImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Images", "png"));
        int result = chooser.showOpenDialog(mainPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());
            Image scaled = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        }
    }

    private void openNewRecipeTab() {
        mainGUI.switchTab(NEWRECIPETABINDEX);
        // GUIInterface.switchTab(NEWRECIPETABINDEX);
    }

    private void openRecipe() {
        mainGUI.switchTab(NEWRECIPETABINDEX);
        // GUIInterface.switchTab(NEWRECIPETABINDEX);
    }

    public static void main(String args[]) {
        // SearchTab gui = new SearchTab();

        // //Schedule a job for the event-dispatching thread("Swing data structures aren't thread-safe")
        // javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //     public void run() {
        //         System.out.println(gui.getAsPanel());
        //     }
        // });
    }
}
