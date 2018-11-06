import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ProfileScreen implements ActionListener {

    // protected string accountType;
    private JPanel profileScreen;
    private ScreenManager screen;
    private String profileType;

    public ProfileScreen(String profType, ScreenManager screenManager) {
        this.screen = screenManager;
        this.profileType = profType;
    }

    public JPanel draw() {
        this.profileScreen = new JPanel();
        this.profileScreen.setBackground(new Color(250, 100, 250));
        
        this.addComponents();

        return this.profileScreen;
    }

    public void addComponents() {
        JLabel profileText = new JLabel("Profile");
        JButton logoutButton = new JButton("Log-out");

        logoutButton.addActionListener(e -> this.logout());

        this.profileScreen.add(logoutButton);
        this.profileScreen.add(profileText);
    } 

    private void logout() {
        this.profileScreen.setVisible(false);
        screen.navToLogin();
    }
}