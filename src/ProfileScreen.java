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

        switch (this.profileType) {
            case "Student": 
                this.studentComponents();
                break;
            case "Admin":  
                screen.navToAdminScreen();
        }
    }

    public void studentComponents() {
        JLabel profileText = new JLabel("Student Profile");
        this.profileScreen.add(profileText);
    }

    public void adminComponents() {

    }

    // etc

    private void logout() {
        this.destroy();
        screen.navToLogin();
    }

    public void destroy() {
        this.profileScreen.setVisible(false);
    }
}