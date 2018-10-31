import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen extends ScreenManager implements ActionListener {

    public LoginScreen(int w, int h) {
        super(w, h);
        // this.panel = ;
    }

    public void init() {
        this.frame = new JFrame("University Software");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(this.width, this.height);

        this.panel = new JPanel();
        this.panel.setBackground(new Color(150, 200, 250));
        this.panel.setLayout(new FlowLayout());

        this.frame.add(panel);
        this.addComponents();
        this.redraw();
    }

    public void redraw() {
        this.frame.setVisible(true);
    }

    public void addComponents() {
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

        this.panel.add(welcomeText);
        this.panel.add(emailText);
        this.panel.add(emailInput);
        this.panel.add(passwordText);
        this.panel.add(passwordInput);
        this.panel.add(submitButton);

        this.panel.setLayout(null);
    }

    public void login() {

    }

    public void destroy() {
        super.destroy();
    }

}