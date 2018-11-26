package university.UI;
import university.ScreenManager;
import java.awt.*;
import javax.swing.*;
import java.awt.color.*;

import university.*;

public class LoginScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private ScreenManager screen;
    private JPanel loginScreen;
    private ProfileScreen profileScreen;

    public LoginScreen(ScreenManager scr) {
        initComponents();
        this.screen = scr;
    }

    public void draw() {
        this.loginScreen = new JPanel();

        this.loginScreen.setVisible(true);
        this.loginScreen.add(welcomeText);
        this.loginScreen.add(promptText);
        this.loginScreen.add(emailInput);
        this.loginScreen.add(passwordInput);
        this.loginScreen.add(emailText);
        this.loginScreen.add(passwordText);
        this.loginScreen.add(submitButton);

        submitButton.addActionListener(e -> {
            this.loginScreen.setVisible(false);
            this.login();
        });
        
        this.loginScreen.setLayout(null);
        this.loginScreen.setBackground(new Color(70, 70,70));
        
        this.screen.frame.add(this.loginScreen);
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
            String username = "rweasel1";
            String password = "password";
            Clearance clearance = Clearance.STUDENT;

            Account account = new Account(title, forename, surname, username, password, clearance);
            //screen.navToProfile(account);
            profileScreen = new ProfileScreen(this.screen, account);
            profileScreen.draw();
        }

        // Query rest
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        welcomeText = new JLabel();
        promptText = new JLabel();
        emailInput = new JTextField();
        passwordInput = new JTextField();
        emailText = new JLabel();
        passwordText = new JLabel();
        submitButton = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- welcomeText ----
        welcomeText.setText("Welcome to University Software");
        welcomeText.setFont(welcomeText.getFont().deriveFont(welcomeText.getFont().getSize() + 10f));
        welcomeText.setHorizontalTextPosition(SwingConstants.CENTER);
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setForeground(Color.white);
        add(welcomeText);
        welcomeText.setBounds(310, 30, 385, 105);

        //---- promptText ----
        promptText.setHorizontalAlignment(SwingConstants.CENTER);
        promptText.setFont(promptText.getFont().deriveFont(promptText.getFont().getSize() + 5f));
        promptText.setText("Please log-in below:");
        promptText.setAlignmentX(-4.0F);
        promptText.setForeground(Color.white);
        add(promptText);
        promptText.setBounds(305, 155, 385, 45);
        add(emailInput);
        emailInput.setBounds(340, 240, 315, emailInput.getPreferredSize().height);
        add(passwordInput);
        passwordInput.setBounds(340, 305, 315, 30);

        //---- emailText ----
        emailText.setText("Email");
        emailText.setFont(emailText.getFont().deriveFont(emailText.getFont().getSize() + 4f));
        emailText.setHorizontalAlignment(SwingConstants.CENTER);
        emailText.setForeground(Color.white);
        add(emailText);
        emailText.setBounds(470, 215, 55, emailText.getPreferredSize().height);

        //---- passwordText ----
        passwordText.setText("Password");
        passwordText.setFont(passwordText.getFont().deriveFont(passwordText.getFont().getSize() + 4f));
        passwordText.setHorizontalAlignment(SwingConstants.CENTER);
        passwordText.setForeground(Color.white);
        add(passwordText);
        passwordText.setBounds(455, 280, 80, 21);

        //---- submitButton ----
        submitButton.setText("Submit");
        add(submitButton);
        submitButton.setBounds(414, 500, 170, 50);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    private JLabel welcomeText;
    private JLabel promptText;
    private JTextField emailInput;
    private JTextField passwordInput;
    private JLabel emailText;
    private JLabel passwordText;
    private JButton submitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
