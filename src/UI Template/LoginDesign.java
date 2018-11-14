import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 14 15:16:42 GMT 2018
 */



/**
 * @author unknown
 */
public class LoginDesign extends JPanel {
    public LoginDesign() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        welcomeText = new JLabel();
        label2 = new JLabel();
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
        add(welcomeText);
        welcomeText.setBounds(310, 30, 385, 105);

        //---- label2 ----
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 5f));
        label2.setText("Please log-in below:");
        label2.setAlignmentX(-4.0F);
        add(label2);
        label2.setBounds(305, 155, 385, 45);
        add(emailInput);
        emailInput.setBounds(340, 240, 315, emailInput.getPreferredSize().height);
        add(passwordInput);
        passwordInput.setBounds(340, 305, 315, 30);

        //---- emailText ----
        emailText.setText("Email");
        emailText.setFont(emailText.getFont().deriveFont(emailText.getFont().getSize() + 4f));
        add(emailText);
        emailText.setBounds(470, 215, 55, emailText.getPreferredSize().height);

        //---- passwordText ----
        passwordText.setText("Password");
        passwordText.setFont(passwordText.getFont().deriveFont(passwordText.getFont().getSize() + 4f));
        add(passwordText);
        passwordText.setBounds(455, 280, 80, 21);

        //---- submitButton ----
        submitButton.setText("Submit");
        add(submitButton);
        submitButton.setBounds(new Rectangle(new Point(455, 350), submitButton.getPreferredSize()));

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
    private JLabel label2;
    private JTextField emailInput;
    private JTextField passwordInput;
    private JLabel emailText;
    private JLabel passwordText;
    private JButton submitButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
