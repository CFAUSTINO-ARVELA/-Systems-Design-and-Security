import java.awt.*;
import javax.swing.*;

public class LoginDesign extends JPanel {

    private ScreenManager screen;
    private JPanel loginScreen;

    private JLabel welcomeText;
    private JLabel promptText;
    private JTextField emailInput;
    private JTextField passwordInput;
    private JLabel emailText;
    private JLabel passwordText;
    private JButton submitButton;

    public LoginDesign(ScreenManager scr) {
        initComponents();
        this.screen = scr;
    }

    public void draw() {
        JPanel login = new JPanel();

        login.setVisible(true);
        login.add(welcomeText);
        login.add(promptText);
        login.add(emailInput);
        login.add(passwordInput);
        login.add(emailText);
        login.add(passwordText);
        login.add(submitButton);
        

        this.screen.frame.add(login);
    }

    /*
     * Created by JFormDesigner on Wed Nov 14 15:16:42 GMT 2018
     */

    private void initComponents() {
        welcomeText = new JLabel();
        promptText = new JLabel();
        emailInput = new JTextField();
        passwordInput = new JTextField();
        emailText = new JLabel();
        passwordText = new JLabel();
        submitButton = new JButton();

        // ======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
                new javax.swing.border.EmptyBorder(0, 0, 0, 0), "JFormDesigner Evaluation",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM,
                new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName()))
                    throw new RuntimeException();
            }
        });

        setLayout(null);

        // ---- welcomeText ----
        welcomeText.setText("Welcome to University Software");
        welcomeText.setFont(welcomeText.getFont().deriveFont(welcomeText.getFont().getSize() + 10f));
        welcomeText.setHorizontalTextPosition(SwingConstants.CENTER);
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeText);
        welcomeText.setBounds(310, 30, 385, 105);

        // ---- promptText ----
        promptText.setHorizontalAlignment(SwingConstants.CENTER);
        promptText.setFont(promptText.getFont().deriveFont(promptText.getFont().getSize() + 5f));
        promptText.setText("Please log-in below:");
        promptText.setAlignmentX(-4.0F);
        add(promptText);
        promptText.setBounds(305, 155, 385, 45);
        add(emailInput);
        emailInput.setBounds(340, 240, 315, emailInput.getPreferredSize().height);
        add(passwordInput);
        passwordInput.setBounds(340, 305, 315, 30);

        // ---- emailText ----
        emailText.setText("Email");
        emailText.setFont(emailText.getFont().deriveFont(emailText.getFont().getSize() + 4f));
        add(emailText);
        emailText.setBounds(470, 215, 55, emailText.getPreferredSize().height);

        // ---- passwordText ----
        passwordText.setText("Password");
        passwordText.setFont(passwordText.getFont().deriveFont(passwordText.getFont().getSize() + 4f));
        add(passwordText);
        passwordText.setBounds(455, 280, 80, 21);

        // ---- submitButton ----
        submitButton.setText("Submit");
        add(submitButton);
        submitButton.setBounds(new Rectangle(new Point(455, 350), submitButton.getPreferredSize()));

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < getComponentCount(); i++) {
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
    }
}
