import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.GridLayout;
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

        String[] clearanceList = { "Student", "Teacher", "Registrar", "Admin" };

        // change to drop down
        JLabel clearanceTxt = new JLabel("Clearance: ");
        JComboBox clearanceInput = new JComboBox(clearanceList);
        clearanceInput.setSelectedIndex(0);
        clearanceInput.addActionListener(this);

        JButton submitBtn = new JButton("Submit");

        // this.accountManagement.setLayout(GridLayout);
        this.accountManagement.add(titleTxt);
        this.accountManagement.add(titleInput);
        this.accountManagement.add(forenameTxt);
        this.accountManagement.add(forenameInput);
        this.accountManagement.add(surnameTxt);
        this.accountManagement.add(surnameInput);
        this.accountManagement.add(clearanceTxt);
        this.accountManagement.add(clearanceInput);
        this.accountManagement.add(submitBtn);

        String cle = (String)clearanceInput.getSelectedItem();

        submitBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            Account ac = new Account(titleInput.getText(), forenameInput.getText(), surnameInput.getText(),"password", cle);

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