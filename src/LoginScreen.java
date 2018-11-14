import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginScreen implements ActionListener {

    // private JFrame frame;
    private JPanel loginScreen;
    private ScreenManager screen;

    public LoginScreen(ScreenManager screen) {
        this.screen = screen;
    }

    public JPanel draw() {
        this.loginScreen = new JPanel();
        JLabel welcomeText = new JLabel("");
        JLabel promptText = new JLabel();
        JLabel emailText = new JLabel("Email");
        JLabel passwordText = new JLabel("Password");
        JTextField emailInput = new JTextField();
        JPasswordField passwordInput = new JPasswordField();
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {
            this.loginScreen.setVisible(false);
            this.login();
        });

        this.loginScreen.setBackground(new Color(100, 100, 100));

        // ---- welcomeText ----
        welcomeText.setText("Welcome to University Software");
        welcomeText.setFont(welcomeText.getFont().deriveFont(welcomeText.getFont().getSize() + 10f));
        welcomeText.setForeground(new Color(255,255,255));
        welcomeText.setHorizontalTextPosition(SwingConstants.CENTER);
        welcomeText.setBounds(310, 30, 385, 105);

        promptText.setHorizontalAlignment(SwingConstants.CENTER);
        promptText.setFont(promptText.getFont().deriveFont(promptText.getFont().getSize() + 5f));
        promptText.setText("Please log-in below:");
        promptText.setForeground(new Color(255,255,255));
        promptText.setAlignmentX(-4.0F);
        promptText.setBounds(305, 155, 385, 45);
        passwordInput.setBounds(340, 305, 315, 30);
        emailInput.setBounds(340, 240, 315, 30);

         // ---- emailText ----
         emailText.setText("Email");
         emailText.setForeground(new Color(255,255,255));
         emailText.setFont(emailText.getFont().deriveFont(emailText.getFont().getSize() + 4f));
         emailText.setBounds(470, 215, 55, emailText.getPreferredSize().height);

         submitButton.setBounds(new Rectangle(new Point(455, 350), submitButton.getPreferredSize()));

         // ---- passwordText ----
         passwordText.setText("Password");
         passwordText.setForeground(new Color(255,255,255));
         passwordText.setFont(passwordText.getFont().deriveFont(passwordText.getFont().getSize() + 4f));
         passwordText.setBounds(455, 280, 80, 21);

        this.loginScreen.setLayout(null);
        this.loginScreen.add(welcomeText);
        this.loginScreen.add(promptText);
        this.loginScreen.add(emailText);
        this.loginScreen.add(emailInput);
        this.loginScreen.add(passwordText);
        this.loginScreen.add(passwordInput);
        this.loginScreen.add(submitButton);

        return this.loginScreen;
    }

    public void login() {

        // //authenticate here
        // I think the easiest way will be to check each table and if it exists in the
        // table and the passwords match, we know they
        // they are a ___ and set profileType accordingly

        // Query student table
        if (true) {

            // get data
            String title = "Miss";
            String forename = "Katie";
            String surname = "Walker";
            String email = "katie@katie.com";
            String username = "acaklw";
            String password = "password";
            Clearance clearance = Clearance.ADMIN;

            Account account = new Account(title, forename, surname, username, password, clearance);
            screen.navToProfile(account);
        }

        // Query rest
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}