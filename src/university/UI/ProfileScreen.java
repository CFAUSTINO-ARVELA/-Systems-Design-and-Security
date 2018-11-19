package university.UI;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;

import university.UI.LoginScreen;
import university.UI.StudentStatus;
import university.UI.AccountManagementScreen;
import university.UI.StudentManagementScreen;
import university.UI.AccountCreationScreen;
import university.*;

public class ProfileScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private ScreenManager screen;
    private Account account;
    private JPanel profileScreen;

    public ProfileScreen(ScreenManager scr, Account acc) {
        this.screen = scr;
        this.account = acc;
        initComponents();
    }

    public void draw() {
        this.profileScreen = new JPanel();
        this.profileScreen.setLayout(null);
        this.profileScreen.setBackground(new Color(70, 70, 70));

        welcomeTxt.setText("Welcome " + this.account.getForename());
        welcomeTxt.setForeground(new Color(255, 255, 255));
        profileTxt.setText(this.account.getClearance() + " Profile");
        profileTxt.setForeground(new Color(255, 255, 255));
        this.addSharedComponents();

        switch (this.account.getClearance()) {
        // Implement cases for teacher and registrar
        case STUDENT:
            this.studentComponents();
            break;
        case ADMIN:
            this.adminComponents();
            break;
        case REGISTRAR:
            this.registrarComponents();
            break;
        case TEACHER:
            this.teacherComponents();
            break;
        }

        screen.frame.add(this.profileScreen);
    }

    public void addSharedComponents() {
        this.profileScreen.add(welcomeTxt);
        this.profileScreen.add(profileTxt);
        this.profileScreen.add(this.logoutButton);

        this.logoutButton.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            screen.navToLogin();
        });
    }

    public void studentComponents() {
        titleTxt.setText("Title: " + this.account.getTitle());
        titleTxt.setForeground(new Color(255, 255, 255));
        nameTxt.setText("Name: " + this.account.getForename() + " " + this.account.getSurname());
        nameTxt.setForeground(new Color(255, 255, 255));

        this.profileScreen.add(profileTxt);
        this.profileScreen.add(titleTxt);
        this.profileScreen.add(nameTxt);
        this.profileScreen.add(studentManageBtn);

        studentManageBtn.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            StudentStatus status = new StudentStatus(account, screen, this);
            status.draw();
        });
    }

    public void adminComponents() {
        accountManagementBtn.addActionListener(e -> {
            AccountManagementScreen accountScreen = new AccountManagementScreen(this.screen, this.account, this);
            
            try {
            	accountScreen.draw();
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
            this.profileScreen.setVisible(false);
        });
        teachingManagementBtn.addActionListener(e -> {
            TeachingManagementScreen teachingScreen = new TeachingManagementScreen(this.screen, this.account, this);
            teachingScreen.draw();
            this.profileScreen.setVisible(false);
        });
        this.profileScreen.add(teachingManagementBtn);
        this.profileScreen.add(accountManagementBtn);
    }

    private void registrarComponents() {
        this.profileScreen.add(studentManageBtn);

        studentManageBtn.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            StudentManagementScreen studentScreen = new StudentManagementScreen(screen, this);
            try {
            	studentScreen.draw();
            } catch (SQLException ex) {
            	ex.printStackTrace();
            }
        });
    }

    private void teacherComponents() {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        profileTxt = new JLabel();
        welcomeTxt = new JLabel();
        logoutButton = new JButton();
        accountManagementBtn = new JButton();
        teachingManagementBtn = new JButton();
        studentManageBtn = new JButton();
        titleTxt = new JLabel();
        nameTxt = new JLabel();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- profileTxt ----
        profileTxt.setText("Profile");
        profileTxt.setHorizontalAlignment(SwingConstants.CENTER);
        profileTxt.setFont(profileTxt.getFont().deriveFont(profileTxt.getFont().getSize() + 12f));
        add(profileTxt);
        profileTxt.setBounds(362, 20, 275, 40);

        //---- welcomeTxt ----
        welcomeTxt.setText("Welcome");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 6f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeTxt);
        welcomeTxt.setBounds(347, 95, 305, 50);

        //---- logoutButton ----
        logoutButton.setText("Logout");
        add(logoutButton);
        logoutButton.setBounds(414, 500, 170, 50);

        //---- accountManagementBtn ----
        accountManagementBtn.setText("Account Management");
        add(accountManagementBtn);
        accountManagementBtn.setBounds(315, 175, 170, 50);

        //---- teachingManagementBtn ----
        teachingManagementBtn.setText("Teaching Management");
        add(teachingManagementBtn);
        teachingManagementBtn.setBounds(510, 175, 170, 50);

        //---- studentManageBtn ----
        studentManageBtn.setText("Student Management");
        add(studentManageBtn);
        studentManageBtn.setBounds(414, 430, 170, 50);

        //---- titleTxt ----
        titleTxt.setText("Title:");
        titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 4f));
        add(titleTxt);
        titleTxt.setBounds(363, 260, 273, titleTxt.getPreferredSize().height);

        //---- nameTxt ----
        nameTxt.setText("Name:");
        nameTxt.setHorizontalAlignment(SwingConstants.CENTER);
        nameTxt.setFont(nameTxt.getFont().deriveFont(nameTxt.getFont().getSize() + 4f));
        add(nameTxt);
        nameTxt.setBounds(363, 290, 273, 16);

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

    // @Override
    // public void actionPerformed(ActionEvent arg0) {
    // // TODO Auto-generated method stub

    // }

    // JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    private JLabel profileTxt;
    private JLabel welcomeTxt;
    private JButton logoutButton;
    private JButton accountManagementBtn;
    private JButton teachingManagementBtn;
    private JButton studentManageBtn;
    private JLabel titleTxt;
    private JLabel nameTxt;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
