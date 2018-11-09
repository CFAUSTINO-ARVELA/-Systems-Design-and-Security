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

        logoutButton.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            screen.navToLogin();
        });

        this.profileScreen.add(logoutButton);
        this.profileScreen.add(profileText);

        switch (this.account.getClearance()) {
            case "Student": 
                this.studentComponents();
            case "Admin":  
                this.adminComponents();
            // others 
        }
    }

    public void studentComponents() {
        JLabel profileText = new JLabel("Student Profile");
        JLabel titleText = new JLabel("Title: " + this.account.getTitle());
        JLabel nameText = new JLabel("Name: " + this.account.getForename() + " " + this.account.getSurname());
        
        this.profileScreen.add(profileText);
        this.profileScreen.add(titleText);
        this.profileScreen.add(nameText);
    }

    public void adminComponents() {
        System.out.print("Drawing admin");
        JLabel welcomeText = new JLabel("Welcome to edit accounts");
        JButton createAccountButton = new JButton("Create Account");
        JButton editAccountButton = new JButton("Edit account");

        createAccountButton.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            screen.navToAccountManagementScreen(account);;
        });
        editAccountButton.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            screen.navToAccountManagementScreen(account);
        });

        this.profileScreen.add(welcomeText);
        this.profileScreen.add(createAccountButton);
        this.profileScreen.add(editAccountButton);
    }

    // private void logout() {
    //     screen.navToLogin();
    // }
}