import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ProfileScreen implements ActionListener {

    // protected string accountType;
    private JPanel profileScreen;
    private ScreenManager screen;
    private Account account;

    public ProfileScreen(Account acc, ScreenManager screenManager) {
        this.screen = screenManager;
        this.account = acc;
    }

    public JPanel draw() {
        this.profileScreen = new JPanel();
        this.profileScreen.setBackground(new Color(250, 100, 250));

        this.addSharedComponents();

        return this.profileScreen;
    }

    public void addSharedComponents() {
        JLabel profileText = new JLabel("Profile: " + this.account.getForename());
        JButton logoutButton = new JButton("Log-out");

        logoutButton.addActionListener(e -> this.logout());

        this.profileScreen.add(logoutButton);
        this.profileScreen.add(profileText);

        switch (this.account.getClearance()) {
            case "Student": 
                this.studentComponents();
            case "Admin":  
                this.adminComponents();
        }
    }

    public void studentComponents() {
        JLabel profileText = new JLabel("Student Profile");
        this.profileScreen.add(profileText);
    }

    public void adminComponents() {
        System.out.print("Drawing admin");
        JLabel welcomeText = new JLabel("Welcome to edit accounts");
        JButton createAccountButton = new JButton("Create Account");
        JButton editAccountButton = new JButton("Edit account");

        createAccountButton.addActionListener(e -> this.createAccount());
        editAccountButton.addActionListener(e -> this.editAccount());

        this.profileScreen.add(welcomeText);
        this.profileScreen.add(createAccountButton);
        this.profileScreen.add(editAccountButton);
    }

    private void logout() {
        this.destroy();
        screen.navToLogin();
    }

    public void destroy() {
        this.profileScreen.setVisible(false);
    }

    public void createAccount() {

    }

    public void editAccount() {
        
    }
}