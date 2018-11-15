package university.UI;
import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 14 16:33:57 GMT 2018
 */



/**
 * @author Katie
 */
public class AccountCreation extends JPanel {
    public AccountCreation() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        welcomeTxt = new JLabel();
        promptTxt = new JLabel();
        titleTxt = new JLabel();
        forenameTxt = new JLabel();
        surnameTxt = new JLabel();
        clearanceTxt = new JLabel();
        titleInput = new JTextField();
        forenameInput = new JTextField();
        surnameInput = new JTextField();
        clearanceInput = new JComboBox();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        welcomeTxt2 = new JLabel();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- welcomeTxt ----
        welcomeTxt.setText("Create Account");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 12f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeTxt);
        welcomeTxt.setBounds(390, 90, 220, welcomeTxt.getPreferredSize().height);

        //---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(promptTxt);
        promptTxt.setBounds(385, 130, 225, promptTxt.getPreferredSize().height);

        //---- titleTxt ----
        titleTxt.setText("Title");
        titleTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 3f));
        add(titleTxt);
        titleTxt.setBounds(325, 185, 45, titleTxt.getPreferredSize().height);

        //---- forenameTxt ----
        forenameTxt.setText("Forename");
        forenameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        forenameTxt.setFont(forenameTxt.getFont().deriveFont(forenameTxt.getFont().getSize() + 3f));
        add(forenameTxt);
        forenameTxt.setBounds(295, 225, 75, 16);

        //---- surnameTxt ----
        surnameTxt.setText("Surname");
        surnameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        surnameTxt.setFont(surnameTxt.getFont().deriveFont(surnameTxt.getFont().getSize() + 3f));
        add(surnameTxt);
        surnameTxt.setBounds(295, 265, 75, 16);

        //---- clearanceTxt ----
        clearanceTxt.setText("Clearance");
        clearanceTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        clearanceTxt.setFont(clearanceTxt.getFont().deriveFont(clearanceTxt.getFont().getSize() + 3f));
        add(clearanceTxt);
        clearanceTxt.setBounds(295, 305, 75, 16);
        add(titleInput);
        titleInput.setBounds(385, 180, 235, titleInput.getPreferredSize().height);
        add(forenameInput);
        forenameInput.setBounds(385, 220, 235, 30);
        add(surnameInput);
        surnameInput.setBounds(385, 260, 235, 30);
        add(clearanceInput);
        clearanceInput.setBounds(385, 300, 235, clearanceInput.getPreferredSize().height);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(435, 345, 135, submitBtn.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(435, 550, 135, 30);

        //---- welcomeTxt2 ----
        welcomeTxt2.setText("Account Management");
        welcomeTxt2.setFont(welcomeTxt2.getFont().deriveFont(welcomeTxt2.getFont().getSize() + 10f));
        welcomeTxt2.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeTxt2);
        welcomeTxt2.setBounds(345, 35, 305, 31);

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
    private JLabel welcomeTxt;
    private JLabel promptTxt;
    private JLabel titleTxt;
    private JLabel forenameTxt;
    private JLabel surnameTxt;
    private JLabel clearanceTxt;
    private JTextField titleInput;
    private JTextField forenameInput;
    private JTextField surnameInput;
    private JComboBox clearanceInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel welcomeTxt2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
