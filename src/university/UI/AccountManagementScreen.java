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

class AccountManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel accountManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private Account account;

    AccountManagementScreen(ScreenManager scr, Account acc, ProfileScreen prof) {
        this.initComponents();
        this.profileScreen = prof;
        this.screen = scr;
        this.account = acc;
    }

    public void draw() {
        this.accountManagement = new JPanel();

        accountManagementTxt.setForeground(new Color(255,255,255));
        this.accountManagement.add(accountManagementTxt);
        this.accountManagement.add(backToProfileBtn);
        this.accountManagement.setBackground(new Color(70, 70, 70));
        this.accountManagement.setLayout(null);

        backToProfileBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            this.profileScreen.draw();
        });

        this.create();

        screen.frame.add(this.accountManagement);
    }

    private void create() {

        String[] clearanceList = { "Student", "Teacher", "Registrar", "Admin" };

        // change to drop down
        JLabel clearanceTxt = new JLabel("Clearance: ");
        JComboBox clearanceInput = new JComboBox(clearanceList);
        clearanceInput.setSelectedIndex(0);
        clearanceInput.addActionListener(this);

        welcomeTxt.setForeground(new Color(255,255,255));
        promptTxt.setForeground(new Color(255,255,255));
        titleTxt.setForeground(new Color(255,255,255));
        forenameTxt.setForeground(new Color(255,255,255));
        surnameTxt.setForeground(new Color(255,255,255));
        clearanceTxt.setForeground(new Color(255,255,255));

        this.accountManagement.add(welcomeTxt);
        this.accountManagement.add(promptTxt);
        this.accountManagement.add(titleTxt);
        this.accountManagement.add(titleInput);
        this.accountManagement.add(forenameTxt);
        this.accountManagement.add(forenameInput);
        this.accountManagement.add(surnameTxt);
        this.accountManagement.add(surnameInput);
        this.accountManagement.add(clearanceTxt);
        this.accountManagement.add(clearanceInput);
        this.accountManagement.add(submitBtn);

        // Component Arrangement

        submitBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            String cle = clearanceInput.getSelectedItem().toString();
            Account ac = new Account(titleInput.getText(), forenameInput.getText(), surnameInput.getText(), "password",
                    cle);

            try {
                Account newAccount = ac.createAccount();
            } catch (SQLException ex) {
                System.out.println("SQL error");
            }
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
        clearanceTxt = new JLabel();
        titleInput = new JTextField();
        forenameInput = new JTextField();
        surnameInput = new JTextField();
        clearanceInput = new JComboBox();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        accountManagementTxt = new JLabel();

        //======== this ========



        //---- welcomeTxt ----
        welcomeTxt.setText("Create Account");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 12f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeTxt);
        welcomeTxt.setBounds(389, 90, 220, welcomeTxt.getPreferredSize().height);

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

        //---- clearanceTxt ----
        clearanceTxt.setText("Clearance");
        clearanceTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        clearanceTxt.setFont(clearanceTxt.getFont().deriveFont(clearanceTxt.getFont().getSize() + 3f));
        add(clearanceTxt);
        clearanceTxt.setBounds(155, 305, 215, 16);
        add(titleInput);
        titleInput.setBounds(382, 180, 235, titleInput.getPreferredSize().height);
        add(forenameInput);
        forenameInput.setBounds(382, 220, 235, 30);
        add(surnameInput);
        surnameInput.setBounds(382, 260, 235, 30);
        add(clearanceInput);
        clearanceInput.setBounds(382, 300, 235, clearanceInput.getPreferredSize().height);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 345, 135, submitBtn.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(432, 550, 135, 30);

        //---- accountManagementTxt ----
        accountManagementTxt.setText("Account Management");
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
    private JLabel clearanceTxt;
    private JTextField titleInput;
    private JTextField forenameInput;
    private JTextField surnameInput;
    private JComboBox clearanceInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel accountManagementTxt;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
