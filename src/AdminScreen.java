import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AdminScreen implements ActionListener {

    private ScreenManager screenManager;
    private JPanel adminScreen;

    AdminScreen(ScreenManager scr) {
        this.screenManager = scr;
    }

    public JPanel draw() {
        System.out.print("Drawing admin");
        this.adminScreen = new JPanel();
        JLabel welcomeText = new JLabel("Welcome to edit accounts");
        JButton createAccountButton = new JButton("Create Account");
        JButton editAccountButton = new JButton("Edit account");
        JButton returnButton = new JButton("Return");

        createAccountButton.addActionListener(e -> this.createAccount());
        editAccountButton.addActionListener(e -> this.editAccount());
        returnButton.addActionListener(e -> this.back());

        // welcomeText.setBounds(100, 30, 400, 30);
        // emailText.setBounds(80, 70, 200, 30);
        // emailInput.setBounds(300, 70, 200, 30);
        // passwordText.setBounds(80, 200, 200, 30);
        // passwordInput.setBounds(300, 200, 200, 30);
        // submitButton.setBounds(300, 300, 200, 30);

        this.adminScreen.setLayout(null);
        this.adminScreen.add(welcomeText);
        this.adminScreen.add(createAccountButton);
        this.adminScreen.add(editAccountButton);
        this.adminScreen.add(returnButton);
        return this.adminScreen;
    }

    private void back() {
        screenManager.navToAdminScreen();
    }

    private void createAccount() {

    }

    private void editAccount() {

    }

}