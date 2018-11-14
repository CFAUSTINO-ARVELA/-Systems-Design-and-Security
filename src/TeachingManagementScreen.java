import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.*;

import java.sql.*;

class TeachingManagementScreen implements ActionListener {

    public JPanel teachingManagement;
    private ScreenManager screen;

    TeachingManagementScreen(ScreenManager scr, Account acc) {
        this.screen = scr;
        this.account = acc;
    }

    public JPanel draw() {
        this.teachingManagement = new JPanel();
        JLabel titleTxt = new JLabel("Welcome to teaching management");
        JButton backToProfileBtn = new JButton("Back");

        this.teachingManagement.add(titleTxt);
        this.teachingManagement.add(backToProfileBtn);
        this.teachingManagement.setLayout(new GridLayout());

        backToProfileBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
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

        this.teachingManagement.add(titleTxt);
        this.teachingManagement.add(titleInput);
        this.teachingManagement.add(forenameTxt);
        this.teachingManagement.add(forenameInput);
        this.teachingManagement.add(surnameTxt);
        this.teachingManagement.add(surnameInput);
        this.teachingManagement.add(usernameTxt);
        this.teachingManagement.add(usernameInput);
        this.teachingManagement.add(submitBtn);

        submitBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
        });
    }

    private void edit() {

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

}