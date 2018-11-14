import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class AccountManagementScreen implements ActionListener {

    public JPanel accountManagement;
    private ScreenManager screen;
    private Account account;

    AccountManagementScreen(ScreenManager scr, Account acc) {
        this.screen = scr;
        this.account = acc;
    }

    public JPanel draw() {
        this.accountManagement = new JPanel();
        JLabel titleTxt = new JLabel("Welcome to account management");
        JButton backToProfileBtn = new JButton("Back");

        this.accountManagement.add(titleTxt);
        this.accountManagement.add(backToProfileBtn);
        this.accountManagement.setLayout(null);

        backToProfileBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            screen.navToProfile(this.account);
        });

        this.create();

        return this.accountManagement;
    }

    private void create() {
        JLabel welcomeTxt = new JLabel("");
        JLabel promptTxt = new JLabel("");
        JLabel titleTxt = new JLabel("Title: ");
        JTextField titleInput = new JTextField();
        JLabel forenameTxt = new JLabel("Forename: ");
        JTextField forenameInput = new JTextField();
        JLabel surnameTxt = new JLabel("Surname: ");
        JTextField surnameInput = new JTextField();
        JButton submitBtn = new JButton("Submit");

        String[] clearanceList = { "Student", "Teacher", "Registrar", "Admin" };

        // change to drop down
        JLabel clearanceTxt = new JLabel("Clearance: ");
        JComboBox clearanceInput = new JComboBox(clearanceList);
        clearanceInput.setSelectedIndex(0);
        clearanceInput.addActionListener(this);

        this.accountManagement.setBackground(new Color(120, 30, 250));
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
                ac.createAccount();
            } catch (SQLException ex) {
                System.out.println("SQL error");
            }

            screen.navToProfile(this.account);
        });
    }

    private void edit() {

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}