package university.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import university.UI.ProfileScreen;
import university.ScreenManager;
import university.Account;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class TeachingManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel teachingManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private Account account;

    TeachingManagementScreen(ScreenManager scr, Account acc, ProfileScreen prof) {
        this.initComponents();
        this.account = acc;
        this.profileScreen = prof;
        this.screen = scr;
    }

    public void draw() {
        this.teachingManagement = new JPanel();

        accountManagementTxt.setForeground(new Color(255,255,255));
        this.teachingManagement.add(accountManagementTxt);
        this.teachingManagement.add(backToProfileBtn);
        this.teachingManagement.setBackground(new Color(70, 70, 70));
        this.teachingManagement.setLayout(null);

        backToProfileBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            this.profileScreen.draw();
        });

        this.create();

        screen.frame.add(this.teachingManagement);
    }

    private void create() {
        welcomeTxt.setForeground(new Color(255,255,255));
        promptTxt.setForeground(new Color(255,255,255));
        titleTxt.setForeground(new Color(255,255,255));
        forenameTxt.setForeground(new Color(255,255,255));
        surnameTxt.setForeground(new Color(255,255,255));

        this.teachingManagement.add(welcomeTxt);
        this.teachingManagement.add(promptTxt);
        this.teachingManagement.add(titleTxt);
        this.teachingManagement.add(titleInput);
        this.teachingManagement.add(forenameTxt);
        this.teachingManagement.add(forenameInput);
        this.teachingManagement.add(surnameTxt);
        this.teachingManagement.add(surnameInput);
        this.teachingManagement.add(submitBtn);

        // Component Arrangement

        submitBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            // String cle = clearanceInput.getSelectedItem().toString();
            // Account ac = new Account(titleInput.getText(), forenameInput.getText(), surnameInput.getText(), "password",
            //         cle);

            //         String status;
            // try {
            //     Account newAccount = ac.createAccount();
            //     status = "Successfully created account";
            // } catch (SQLException ex) {
            //     status = "SQL error";
            //     System.out.println("SQL error, please try again");
            // }
            this.profileScreen.draw();
        });
    }

    private void edit() {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        welcomeTxt = new JLabel();
        promptTxt = new JLabel();
        titleTxt = new JLabel();
        forenameTxt = new JLabel();
        surnameTxt = new JLabel();
        titleInput = new JTextField();
        forenameInput = new JTextField();
        surnameInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        accountManagementTxt = new JLabel();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- welcomeTxt ----
        welcomeTxt.setText("Placeholder screen");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 12f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeTxt);
        welcomeTxt.setBounds(331, 90, 336, welcomeTxt.getPreferredSize().height);

        //---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        //---- titleTxt ----
        titleTxt.setText("Title");
        titleTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 3f));
        add(titleTxt);
        titleTxt.setBounds(185, 185, 185, titleTxt.getPreferredSize().height);

        //---- forenameTxt ----
        forenameTxt.setText("Forename");
        forenameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        forenameTxt.setFont(forenameTxt.getFont().deriveFont(forenameTxt.getFont().getSize() + 3f));
        add(forenameTxt);
        forenameTxt.setBounds(230, 227, 140, 16);

        //---- surnameTxt ----
        surnameTxt.setText("Surname");
        surnameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        surnameTxt.setFont(surnameTxt.getFont().deriveFont(surnameTxt.getFont().getSize() + 3f));
        add(surnameTxt);
        surnameTxt.setBounds(155, 265, 215, 16);
        add(titleInput);
        titleInput.setBounds(382, 180, 235, titleInput.getPreferredSize().height);
        add(forenameInput);
        forenameInput.setBounds(382, 220, 235, 30);
        add(surnameInput);
        surnameInput.setBounds(382, 260, 235, 30);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 345, 135, submitBtn.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- accountManagementTxt ----
        accountManagementTxt.setText("Teaching Management");
        accountManagementTxt.setFont(accountManagementTxt.getFont().deriveFont(accountManagementTxt.getFont().getSize() + 10f));
        accountManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(accountManagementTxt);
        accountManagementTxt.setBounds(347, 35, 305, 31);

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
        // JFormDesigner - End of component initialization //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    private JLabel welcomeTxt;
    private JLabel promptTxt;
    private JLabel titleTxt;
    private JLabel forenameTxt;
    private JLabel surnameTxt;
    private JTextField titleInput;
    private JTextField forenameInput;
    private JTextField surnameInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel accountManagementTxt;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
