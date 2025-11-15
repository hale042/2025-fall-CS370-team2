import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;

public class WelcomeTab extends TabFrameTemplate {
    private final Font titleFont = new Font("Segoe UI", Font.BOLD, 35);

    @Override
    public void initializeTabContents() {
        mainPanel.setLayout(new BorderLayout(10, 10));

        JLabel title = new JLabel("Welcome!");
        title.setFont(titleFont);
        title.setHorizontalAlignment(JLabel.CENTER);

        mainPanel.add(title, BorderLayout.NORTH);
    }
}
