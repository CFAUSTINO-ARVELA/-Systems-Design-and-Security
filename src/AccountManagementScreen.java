import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.*;

import java.sql.*;

class AccountManagementScreen implements ActionListener {

    public JPanel accountManagement;
    private ScreenManager screen;
    private Account account;
    private String task;

    AccountManagementScreen(ScreenManager scr, Account acc, String tsk) {
        this.screen = scr;
        this.account = acc;
        this.task = "Create";
    }

    public JPanel draw() {
        this.accountManagement = new JPanel();
        JLabel titleTxt = new JLabel("Welcome to account management");
        JButton backToProfileBtn = new JButton("Back");

        this.accountManagement.add(titleTxt);
        this.accountManagement.add(backToProfileBtn);
        this.accountManagement.setLayout(new GridLayout());

        backToProfileBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            screen.navToProfile(this.account);
        });

        this.create();

        return this.accountManagement;
    }

    private void create() {
        JLabel titleTxt = new JLabel("Title: ");
        JTextField titleInput = new JTextField();
        JLabel forenameTxt = new JLabel("Forename: ");
        JTextField forenameInput = new JTextField();
        JLabel surnameTxt = new JLabel("Surname: ");
        JTextField surnameInput = new JTextField();
        JLabel usernameTxt = new JLabel("Username: ");

        // change to drop down
        JLabel clearanceTxt = new JLabel("Clearance: ");
        JTextField clearanceInput = new JTextField();
        JTextField usernameInput = new JTextField();
        JButton submitBtn = new JButton("Submit");

        // this.accountManagement.setLayout(GridLayout);
        this.accountManagement.add(titleTxt);
        this.accountManagement.add(titleInput);
        this.accountManagement.add(forenameTxt);
        this.accountManagement.add(forenameInput);
        this.accountManagement.add(surnameTxt);
        this.accountManagement.add(surnameInput);
        this.accountManagement.add(usernameTxt);
        this.accountManagement.add(usernameInput);
        this.accountManagement.add(submitBtn);

        submitBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            Account ac = new Account(titleInput.getText(), forenameInput.getText(), surnameInput.getText(), usernameInput.getText(), "password", Clearance.STUDENT);

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