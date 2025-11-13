import java.awt.Dimension;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Cook It! - Front-End GUI for Recipe Finder Application
 * Version 1.0
 * Developed by Team 2 (Luke Hale, Lundon Dotson, Nikita Sharma, Ricky Arnold)
 */
public class CookItGUI extends JFrame {
    private JFrame mainFrame = new JFrame("My First GUI"); // main window

    // frame size
    int frameWidth = 300;
    int frameHeight = 300;
    private Dimension defaultWindowSize = new Dimension(frameWidth, frameHeight);

    private CookItGUI () {
        this.mainFrame.setMinimumSize(defaultWindowSize);
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // start fullscreen
        // this.mainFrame.setUndecorated(true);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what function to run when the window is closed

        // tabs
        SearchTab searchTab = new SearchTab();
        WelcomeTab welcomeTab = new WelcomeTab();
        RecipeTab recipeTab = new RecipeTab();
        NoteTab noteTab = new NoteTab();

        // tabbed panes?
        JTabbedPane tabbedPane = new JTabbedPane();

        // JComponent mainTab = gui_test.makeTextPanel("Panel #1");
        JComponent startPanel = welcomeTab.getAsPanel();
        // tabbedPane.addTab("Tab 1", icon, panel1, "Does nothing");
        tabbedPane.addTab("Welcome!", null, startPanel, "Does nothing");
        // tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JComponent searchPanel = searchTab.getAsPanel();
        tabbedPane.addTab("Search", null, searchPanel, "Search for Recipes!");
        // tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent recipePanel = recipeTab.getAsPanel();
        tabbedPane.addTab("Recipe", null, recipePanel, "Does twice as much nothing");
        // tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent notesPanel = noteTab.getAsPanel();
        // tabbedPane.addTab("Tab 3", icon, panel3, "Still does nothing");
        tabbedPane.addTab("Notes", null, notesPanel, "Still does nothing");
        // tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        // no more settings tab
        // JComponent settingsTab = gui_test.makeTextPanel("Panel #4 (has a preferred size of 410 x 50).");
        // // settingsTab.setPreferredSize(new Dimension(410, 50));
        // // tabbedPane.addTab("Tab 4", icon, panel4, "Does nothing at all");
        // tabbedPane.addTab("Settings", null, settingsTab, "Does nothing at all");
        // // tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        //Add the tabbed panel
        // this.mainFrame.getContentPane().add(tabbedPane);
        this.mainFrame.add(tabbedPane);
        
        // enable scrolling tabs.
        // tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        this.mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CookItGUI::new);
    }
}
