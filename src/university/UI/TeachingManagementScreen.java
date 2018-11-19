package university.UI;
import java.awt.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import university.Account;
import university.ScreenManager;

import java.awt.GridLayout;
import java.awt.event.*;

import java.sql.*;

class TeachingManagementScreen implements ActionListener {

    public JPanel teachingManagement;
    private ScreenManager screen;
    private Account account;
    private ProfileScreen profileScreen;

    TeachingManagementScreen(ScreenManager scr, Account acc, ProfileScreen prof) {
        this.screen = scr;
        this.account = acc;
        this.profileScreen = prof;
    }

    public void draw() {
        this.teachingManagement = new JPanel();

        this.teachingManagement.add(welcomeTxt);
        this.teachingManagement.add(titleTxt);
        this.teachingManagement.add(degreeBtn);
        this.teachingManagement.add(departmentBtn);
        this.teachingManagement.add(moduleBtn);
        this.teachingManagement.add(backToProfileBtn);
        this.teachingManagement.setLayout(new GridLayout());

        backToProfileBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            //screen.navToProfile(this.account);
        });

        screen.frame.add(this.teachingManagement);
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        welcomeTxt = new JLabel();
        backToProfileBtn = new JButton();
        titleTxt = new JLabel();
        degreeBtn = new JButton();
        departmentBtn = new JButton();
        moduleBtn = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark

        //---- welcomeTxt ----
        welcomeTxt.setText("Welcome");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 12f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeTxt.setForeground(Color.white);
        welcomeTxt.setBounds(331, 90, 336, 31);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- titleTxt ----
        titleTxt.setText("Teaching Management");
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 10f));
        titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        titleTxt.setForeground(Color.white);
        titleTxt.setBounds(347, 35, 305, 31);

        //---- degreeBtn ----
        degreeBtn.setText("Degree Management");

        degreeBtn.setBounds(190, 180, 195, 50);

        //---- departmentBtn ----
        departmentBtn.setText("Department Management");

        departmentBtn.setBounds(402, 180, 195, 50);

        //---- moduleBtn ----
        moduleBtn.setText("Module Management");

        moduleBtn.setBounds(615, 180, 195, 50);

        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    private JLabel welcomeTxt;
    private JButton backToProfileBtn;
    private JLabel titleTxt;
    private JButton degreeBtn;
    private JButton departmentBtn;
    private JButton moduleBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}