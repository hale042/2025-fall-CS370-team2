import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Font;
// import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class gui_test2 {
    private JFrame mainFrame = new JFrame("My First GUI"); // main window

    // frame size
    int frameWidth = 300;
    int frameHeight = 300;
    private Dimension defaultWindowSize = new Dimension(frameWidth, frameHeight);

    private void initializeMainWindow () {
        this.mainFrame.setMinimumSize(defaultWindowSize);
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); // start fullscreen
        // this.mainFrame.setUndecorated(true);
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what function to run when the window is closed

        // tabbed panes?
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent mainTab = makeTextPanel("Panel #1");
        // tabbedPane.addTab("Tab 1", icon, panel1, "Does nothing");
        tabbedPane.addTab("Welcome!", null, mainTab, "Does nothing");
        // tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JComponent searchTab = searchPanelTest();
        // tabbedPane.addTab("Tab 2", icon, panel2, "Does twice as much nothing");
        tabbedPane.addTab("Search", null, searchTab, "Does twice as much nothing");
        // tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent recipeTab = recipePanelTest();
        // tabbedPane.addTab("Tab 2", icon, panel2, "Does twice as much nothing");
        tabbedPane.addTab("Recipe", null, recipeTab, "Does twice as much nothing");
        // tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent noteTab = makeTextPanel("Panel #3");
        // tabbedPane.addTab("Tab 3", icon, panel3, "Still does nothing");
        tabbedPane.addTab("Notes", null, noteTab, "Still does nothing");
        // tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
        JComponent settingsTab = makeTextPanel("Panel #4 (has a preferred size of 410 x 50).");
        // settingsTab.setPreferredSize(new Dimension(410, 50));
        // tabbedPane.addTab("Tab 4", icon, panel4, "Does nothing at all");
        tabbedPane.addTab("Settings", null, settingsTab, "Does nothing at all");
        // tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        
        //Add the tabbed pane to this panel.
        this.mainFrame.getContentPane().add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // 
        this.mainFrame.setVisible(true);
    }

    public static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);

        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    public JComponent recipePanelTest() {
        Dimension windowDimension = this.mainFrame.getSize();
        int paddingX = windowDimension.width / 15;

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        String sample_text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        
        // header
        JPanel header = new JPanel();
        // header.setLayout(new BorderLayout());
        header.setLayout(new GridLayout(1, 1));
        header.setBorder(BorderFactory.createEmptyBorder(5, paddingX, 5, paddingX));
        // header.setBackground(Color.BLUE);
        
        // System.out.println(windowDimension.width + "x" + windowDimension.height);
        
        JLabel headerText = new JLabel(sample_text.substring(0,26), JLabel.LEFT);
        // Font headerFont = new Font("Verdana", Font.PLAIN, 56);
        Font headerFont = new Font("Verdana", Font.BOLD, 56);
        headerText.setFont(headerFont);
        header.add(headerText);
        // header.add(BorderLayout.CENTER, headerText);
        
        
        // body for recipe details
        JPanel body = new JPanel();
        body.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        body.add(new JLabel(sample_text, JLabel.CENTER));

        panel.add(BorderLayout.NORTH, header);
        panel.add(BorderLayout.NORTH, body);

        JScrollPane scrollable = new JScrollPane(panel);
        // return panel;
        return scrollable;
    }
    
    public static JComponent searchPanelTest() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints bagConstraints = new GridBagConstraints();
        
        // ingredients/filters side panel?
        // panel.setMinimumSize(new Dimension(300, 300));
        JPanel sidePanel = new JPanel();
        // sidePanel.setLayout(new GridLayout(5, 1));
        BoxLayout boxlayout = new BoxLayout(sidePanel, BoxLayout.Y_AXIS);
        sidePanel.setLayout(boxlayout);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); // Set border for the panel
                
        sidePanel.add(new JLabel("Filters:"));
        sidePanel.add(new JCheckBox("Test 1"));
        sidePanel.add(new JCheckBox("Test 2"));
        sidePanel.add(new JCheckBox("Test 3"));
        // String ingredients[] = {"test1", "test2", "test3", "test4", "etst5", "test6"};
        // JList<String> ingredList = new JList<String>(ingredients);
        // sidePanel.add(ingredList);
        
        bagConstraints.fill = GridBagConstraints.VERTICAL;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.gridheight = 2;
        // bagConstraints.gridwidth = 2;
        panel.add(sidePanel, bagConstraints);
        
        // search bar
        JPanel searchComponents = new JPanel();
        searchComponents.setLayout(new BorderLayout());
        searchComponents.setBorder(BorderFactory.createEmptyBorder(5,5,5,5)); // Set border for the panel
        
        JTextField searchField = new JTextField(100);
        JButton searchButton = new JButton("Send");
        searchComponents.add(searchField, BorderLayout.WEST);
        searchComponents.add(searchButton, BorderLayout.EAST);
        
        bagConstraints.fill = GridBagConstraints.HORIZONTAL;
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 0;
        // bagConstraints.gridwidth = 2;
        panel.add(searchComponents, bagConstraints);
        
        // searchResults
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        // listPanel.add(new JLabel(" "), BorderLayout.NORTH);

        // ArrayList<String> testList = new ArrayList<String>(Arrays.asList("test1", "test2", "test3"));
        String testList[] = {"test1", "test2", "test3", "test4", "etst5", "test6"};
        JList<String> recipeList = new JList<String>(testList);
        listPanel.add(recipeList, BorderLayout.SOUTH);
        // first element of the list keeps getting cut off for some reason? haven't figured out why yet
        
        // bagConstraints.fill = GridBagConstraints.HORIZONTAL;
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.gridx = 1;
        bagConstraints.gridy = 1;
        // bagConstraints.gridwidth = 2;
        panel.add(listPanel, bagConstraints);

        return panel;
    }

    public void start() {
        initializeMainWindow();

        // 
    }

    public static void main(String args[]) {
        gui_test2 gui = new gui_test2();

        //Schedule a job for the event-dispatching thread("Swing data structures aren't thread-safe")
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.start();
            }
        });
    }
}