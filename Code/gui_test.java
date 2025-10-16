import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
// import javax.swing.;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import java.awt.Image;
// import java.awt.Image.SCALE_SMOOTH;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
https://www.guru99.com/java-swing-gui.html#what-is-swing-in-java
https://docs.oracle.com/javase/8/docs/api/javax/swing/ImageIcon.html
https://zetcode.com/java/imageicon/
*/

class gui {
    public static void BasicTest() {
        // creating the frame
        JFrame frame = new JFrame("My First GUI"); // window name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what function to run when the window is closed
        frame.setSize(300, 300); // window size

        // adding buttons
        JButton button1 = new JButton("button 1"); // button; setting its text
        frame.getContentPane().add(button1); // Adds Button to content pane of frame
        button1.addActionListener(new ActionListener() {
            @Override // overide might not be neccessary here
            public void actionPerformed(ActionEvent e) {
                System.out.println("This is a test");
            }
        });

        // JButton button2 = new JButton("Button 2");
        // frame.getContentPane().add(button2);

        // frame.pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. overwritten by the setMinimumSize function
        frame.setVisible(true);
    }

    public static void ChatExample() {
        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        // frame.

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar(); // menubar
        JMenu m1 = new JMenu("FILE"); // menu tabs
        JMenu m2 = new JMenu("Help");
        mb.add(m1); // add menu tab to the menu bar
        mb.add(m2);

        JMenuItem m11 = new JMenuItem("Open"); // options under the menu tabs
        JMenuItem m12 = new JMenuItem("Save as");
        m1.add(m11); // add menu item to the menu(tab)
        m1.add(m12);

        JMenuItem m21 = new JMenuItem("Search on the Internet");
        m2.add(m21);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(10); // accepts upto 10 characters
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // Text Area at the Center
        JTextArea ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        
        frame.setVisible(true);
    }

    public static void ImageDisplay() {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating dimension object for the frame window('s size)
        // https://www.geeksforgeeks.org/java/java-awt-dimension-class/
        int frameWidth = 300;
        int frameHeight = 300;
        Dimension frameDimension = new Dimension(frameWidth, frameHeight);
        // frame.setSize(300, 300);
        // frame.setSize(frameDimension);
        frame.setMinimumSize(frameDimension);
        // frame.getSize().height
        
        // JLabel label = new JLabel("Hello World");
        // frame.getContentPane().add(label);
        
        // row = suit, value = column
        // https://www.youtube.com/watch?v=ntirmRhy6Fw
        // try catch for displaying the image
        try {
            ImageIcon image = new ImageIcon("Lab4\\Poker cards\\ace_of_spades2.png"); // create imageicon object
            int imageW = image.getIconWidth();
            int imageH = image.getIconHeight();
            
            // scaling test (https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon)
            Image testImage = image.getImage(); // transform it 
            Image newimg = testImage.getScaledInstance(imageW/2, imageH/2, Image.SCALE_SMOOTH); // scale it the smooth way  
            ImageIcon scaledImage = new ImageIcon(newimg);  // transform it back
            
            // // ImageIcon test = image.getImage();
            // // ImageIcon test = image.getImage().getScaledInstance(frameWidth, frameHeight, SCALE_SMOOTH);
            // ImageIcon test = image.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_DEFAULT);
            
            // JLabel displayField = new JLabel(image); // use jlabel to display image
            JLabel displayField = new JLabel(scaledImage); // use jlabel to display image
            frame.add(displayField);
            // JLabel img2 = new JLabel(image); // use jlabel to display image
            // frame.add(img2);
        } catch (Exception e) {
            System.out.println("Unable to get image");
        }
        
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void DisplayImagesFromDirectory(String dir) {
        dir = (dir != null) ? dir : "Lab4\\Poker cards";
        
        // get the paths of all the files in the image folder
        File directory = new File(dir);
        File[] imagePaths = directory.listFiles();
        
        System.out.println(imagePaths.length);
        
        // might be the hard way...
        ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
        
        // print list of file paths
        if (imagePaths != null) {
            for (File file : imagePaths) {
                System.out.println(file.getName());
                // should probably check file extensions :shrug:
            }
        }
        
    }
    
    public static void listShuffleTest() {
        ArrayList<String> TheList = new ArrayList<String>(Arrays.asList("Apples", "Bananas", "Oranges", "Pears", "Kiwis", "Dragonfruit"));
        
        JFrame frame = new JFrame("My First GUI"); // window name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what function to run when the window is closed
        frame.setSize(300, 300); // window size
        
        // adding buttons
        JButton button1 = new JButton("button 1"); // button; setting its text
        frame.getContentPane().add(button1); // Adds Button to content pane of frame
        button1.addActionListener(new ActionListener() {
            @Override // overide might not be neccessary here
            public void actionPerformed(ActionEvent e) {
                // print out list
                for (String item : TheList) {
                    System.out.println(item);
                }
                System.out.println("-------------");
                
                Collections.shuffle(TheList); // use shuffle function from Collections to shuffle the list
            }
        });
        
        // JButton button2 = new JButton("Button 2");
        // frame.getContentPane().add(button2);
        
        // frame.pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. overwritten by the setMinimumSize function
        frame.setVisible(true);
    }
    
    public static void appGUITest() {
        // creating the frame
        JFrame frame = new JFrame("My First GUI"); // window name

        // frame size
        int frameWidth = 300;
        int frameHeight = 300;
        Dimension frameDimension = new Dimension(frameWidth, frameHeight);
        frame.setMinimumSize(frameDimension);

        // frame.setSize(300, 300); // window size

        // fullscreen?
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // what function to run when the window is closed

        // main panel
        JPanel mainPanel = new JPanel();
        frame.add(BorderLayout.CENTER, mainPanel);

        // adding buttons
        // JButton button1 = new JButton("button 1"); // button; setting its text
        // frame.getContentPane().add(button1); // Adds Button to content pane of frame
        // button1.addActionListener(new ActionListener() {
        //     @Override // overide might not be neccessary here
        //     public void actionPerformed(ActionEvent e) {
        //         System.out.println("This is a test");
        //     }
        // });

        // JButton button2 = new JButton("Button 2");
        // frame.getContentPane().add(button2);

        //Creating the MenuBar and adding components
        JMenuBar menuBar = new JMenuBar(); // menubar
        JMenu menu1 = new JMenu("FILE"); // menu tabs
        JMenu menu2 = new JMenu("Help");
        menuBar.add(menu1); // add menu tab to the menu bar
        menuBar.add(menu2);

        JMenuItem openOption = new JMenuItem("Open"); // options under the menu tabs
        JMenuItem saveOption = new JMenuItem("Save as");
        menu1.add(openOption); // add menu item to the menu(tab)
        menu1.add(saveOption);

        JMenuItem searchOption = new JMenuItem("Search on the Internet");
        menu2.add(searchOption);

        //Creating the panel at bottom and adding components
        // JPanel panel = new JPanel(); // the panel is not visible in output
        // JLabel label = new JLabel("Enter Text");
        // JTextField tf = new JTextField(10); // accepts upto 10 characters
        // JButton send = new JButton("Send");
        // JButton reset = new JButton("Reset");
        // panel.add(label); // Components Added using Flow Layout
        // panel.add(tf);
        // panel.add(send);
        // panel.add(reset);

        //Adding Components to the frame.
        // mainPanel.add(BorderLayout.SOUTH, panel);
        mainPanel.add(BorderLayout.NORTH, menuBar);
        // frame.getContentPane().add(BorderLayout.CENTER, ta);

        // frame.pack(); // Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. overwritten by the setMinimumSize function
        frame.setVisible(true);
    }
    
    public static void appGUITest2() {
        // https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
        // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TabbedPaneDemoProject/src/components/TabbedPaneDemo.java
        // creating the frame
        JFrame frame = new JFrame("My First GUI"); // window name
        
        // frame size
        int frameWidth = 300;
        int frameHeight = 300;
        Dimension frameDimension = new Dimension(frameWidth, frameHeight);
        frame.setMinimumSize(frameDimension);
        
        // frame.setSize(300, 300); // window size
        
        // fullscreen?
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // frame.setUndecorated(true);
        
        // what function to run when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // tabbed panes?
        JTabbedPane tabbedPane = new JTabbedPane();
        // ImageIcon icon = createImageIcon("images/middle.gif");
        
        /*
            set the tab as a premade panel?!
            would mean you can/have to create each tab separately(have a function for each tab/section?)
        */
        JComponent mainTab = makeTextPanel("Panel #1");
        // tabbedPane.addTab("Tab 1", icon, panel1, "Does nothing");
        tabbedPane.addTab("Welcome!", null, mainTab, "Does nothing");
        // tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        JComponent searchTab = searchPanelTest();
        // tabbedPane.addTab("Tab 2", icon, panel2, "Does twice as much nothing");
        tabbedPane.addTab("Search", null, searchTab, "Does twice as much nothing");
        // tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JComponent recipeTab = makeTextPanel("Panel #2");
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
        frame.getContentPane().add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // 
        frame.setVisible(true);
    }
    
    public static JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);

        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
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

    public static void main(String args[]) {
        //Schedule a job for the event-dispatching thread("Swing data structures aren't thread-safe")
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // BasicTest();
                // ChatExample();
                // ImageDisplay();
                // DisplayImagesFromDirectory(null);
                // listShuffleTest();
                // appGUITest();
                appGUITest2();
            }
        });
    }
}