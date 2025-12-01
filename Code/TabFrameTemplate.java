import java.awt.GridLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

// package CookItGUI;
public class TabFrameTemplate {
    protected JPanel mainPanel = new JPanel(); // main window
    private String sample_text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    // public TabFrameTemplate() {}

    public void initializeTabContents() {
        mainPanel.setLayout(new GridLayout(1, 1));
        
        JLabel filler = new JLabel(this.sample_text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        
        mainPanel.add(filler);
    }

    public JComponent getAsPanel() {
        initializeTabContents();
        
        return mainPanel;
    }

    public static void main(String args[]) {
        TabFrameTemplate gui = new TabFrameTemplate();

        //Schedule a job for the event-dispatching thread("Swing data structures aren't thread-safe")
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println(gui.getAsPanel());
            }
        });
    }
}