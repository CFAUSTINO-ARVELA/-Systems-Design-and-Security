import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen extends ScreenManager implements ActionListener {

    // private JFrame frame;
    private JPanel loginScreen;
    private ScreenManager screen;

    public LoginScreen(int w, int h, ScreenManager screen) {
        super(w, h);
        this.screen = screen;
    }

    public JPanel draw() {
        this.loginScreen = new JPanel();
        JLabel welcomeText = new JLabel("Welcome! Please login below");
        JLabel emailText = new JLabel("Email:");
        JLabel passwordText = new JLabel("Password:");
        JTextField emailInput = new JTextField();
        JPasswordField passwordInput = new JPasswordField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> this.login());

        welcomeText.setBounds(100, 30, 400, 30);
        emailText.setBounds(80, 70, 200, 30);
        emailInput.setBounds(300, 70, 200, 30);
        passwordText.setBounds(80, 200, 200, 30);
        passwordInput.setBounds(300, 200, 200, 30);
        submitButton.setBounds(300, 300, 200, 30);

        this.loginScreen.add(welcomeText);
        this.loginScreen.add(emailText);
        this.loginScreen.add(emailInput);
        this.loginScreen.add(passwordText);
        this.loginScreen.add(passwordInput);
        this.loginScreen.add(submitButton);

        return this.loginScreen;
    }

    public void login() {

        // //authenticate here
        screen.navToProfile();
    }

    public void destroy() {
        this.loginScreen.setVisible(false);
    }
}