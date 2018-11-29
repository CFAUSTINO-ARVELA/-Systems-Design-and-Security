package university.UI;

import java.awt.*;
import java.sql.*;

import university.UI.ProfileScreen;
import university.ScreenManager;
import university.ValidCheck;
import university.Account;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class AccountCreationScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel accountCreation;
    private ScreenManager screen;
    private AccountManagementScreen accountManagement;
    private ProfileScreen profileScreen;
    private String[] clearanceList = { "Teacher", "Registrar", "Admin" };

    AccountCreationScreen(ScreenManager scr, AccountManagementScreen accManage, ProfileScreen prof) {
        this.initComponents();
        this.initListeners();
        this.screen = scr;
        this.profileScreen = prof;
        this.accountManagement = accManage;
    }

    public void initListeners() {
        backToProfileBtn.addActionListener(e -> {
            this.accountCreation.setVisible(false);
            this.accountManagement.draw();
        });

        submitBtn.addActionListener(e -> {

            if (ValidCheck.input(titleInput) || ValidCheck.input(forenameInput) || ValidCheck.input(surnameInput)) {
                JOptionPane.showMessageDialog(null, "Please enter all fields and ensure there are no symbols");
            } else {
                this.accountCreation.setVisible(false);
                String cle = clearanceInput.getSelectedItem().toString();
                Account ac = new Account(titleInput.getText(), forenameInput.getText(), surnameInput.getText(),
                        "password", cle);

                try {
                    Account newAccount = ac.createAccount();
                    this.profileScreen.draw();
                    JOptionPane.showMessageDialog(null, "Successfully created Account: " + newAccount.getEmail()
                            + ". Password: " + newAccount.getPassword());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "SQL error, please try again");
                }
            }
        });
    }

    public void draw() {
        this.accountCreation = new JPanel();

        accountManagementTxt.setForeground(new Color(255, 255, 255));
        this.accountCreation.add(accountManagementTxt);
        this.accountCreation.add(backToProfileBtn);
        this.accountCreation.setBackground(new Color(70, 70, 70));
        this.accountCreation.setLayout(null);

        this.create();
        screen.frame.add(this.accountCreation);
    }

    private void create() {
        clearanceInput.setSelectedIndex(0);
        clearanceInput.addActionListener(this);

        welcomeTxt.setForeground(new Color(255, 255, 255));
        promptTxt.setForeground(new Color(255, 255, 255));
        titleTxt.setForeground(new Color(255, 255, 255));
        forenameTxt.setForeground(new Color(255, 255, 255));
        surnameTxt.setForeground(new Color(255, 255, 255));
        clearanceTxt.setForeground(new Color(255, 255, 255));

        this.accountCreation.add(welcomeTxt);
        this.accountCreation.add(promptTxt);
        this.accountCreation.add(titleTxt);
        this.accountCreation.add(titleInput);
        this.accountCreation.add(forenameTxt);
        this.accountCreation.add(forenameInput);
        this.accountCreation.add(surnameTxt);
        this.accountCreation.add(surnameInput);
        this.accountCreation.add(clearanceTxt);
        this.accountCreation.add(clearanceInput);
        this.accountCreation.add(submitBtn);
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
        clearanceInput = new JComboBox(clearanceList);
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        accountManagementTxt = new JLabel();

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

        // ---- welcomeTxt ----
        welcomeTxt.setText("Create Account");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 12f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeTxt);
        welcomeTxt.setBounds(389, 90, 220, welcomeTxt.getPreferredSize().height);

        // ---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        // ---- titleTxt ----
        titleTxt.setText("Title");
        titleTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 3f));
        add(titleTxt);
        titleTxt.setBounds(185, 185, 185, titleTxt.getPreferredSize().height);

        // ---- forenameTxt ----
        forenameTxt.setText("Forename");
        forenameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        forenameTxt.setFont(forenameTxt.getFont().deriveFont(forenameTxt.getFont().getSize() + 3f));
        add(forenameTxt);
        forenameTxt.setBounds(230, 227, 140, 16);

        // ---- surnameTxt ----
        surnameTxt.setText("Surname");
        surnameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        surnameTxt.setFont(surnameTxt.getFont().deriveFont(surnameTxt.getFont().getSize() + 3f));
        add(surnameTxt);
        surnameTxt.setBounds(155, 265, 215, 16);

        // ---- clearanceTxt ----
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

        // ---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 345, 135, submitBtn.getPreferredSize().height);

        // ---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        // ---- accountManagementTxt ----
        accountManagementTxt.setText("Account Management");
        accountManagementTxt
                .setFont(accountManagementTxt.getFont().deriveFont(accountManagementTxt.getFont().getSize() + 10f));
        accountManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(accountManagementTxt);
        accountManagementTxt.setBounds(347, 35, 305, 31);

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

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}
