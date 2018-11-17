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

        teachingManagement.setForeground(new Color(255,255,255));
        this.teachingManagement.add(welcomeTxt);
        this.teachingManagement.add(backToProfileBtn);
        this.teachingManagement.add(titleTxt);
        this.teachingManagement.add(degreeBtn);
        this.teachingManagement.add(departmentBtn);
        this.teachingManagement.add(moduleBtn);

        this.teachingManagement.setBackground(new Color(70, 70, 70));
        this.teachingManagement.setLayout(null);

        backToProfileBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            this.profileScreen.draw();
        });
        degreeBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            DegreeManagementScreen degreeScreen = new DegreeManagementScreen(this.screen, this);
            degreeScreen.draw();
        });
        departmentBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            DepartmentManagementScreen departmentScreen = new DepartmentManagementScreen(this.screen, this);
            departmentScreen.draw();
        });

        screen.frame.add(this.teachingManagement);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        welcomeTxt = new JLabel();
        backToProfileBtn = new JButton();
        titleTxt = new JLabel();
        degreeBtn = new JButton();
        departmentBtn = new JButton();
        moduleBtn = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- welcomeTxt ----
        welcomeTxt.setText("Welcome");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 12f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeTxt.setForeground(Color.white);
        add(welcomeTxt);
        welcomeTxt.setBounds(331, 90, 336, welcomeTxt.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- titleTxt ----
        titleTxt.setText("Teaching Management");
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 10f));
        titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        titleTxt.setForeground(Color.white);
        add(titleTxt);
        titleTxt.setBounds(347, 35, 305, 31);

        //---- degreeBtn ----
        degreeBtn.setText("Degree Management");
        add(degreeBtn);
        degreeBtn.setBounds(190, 180, 195, 50);

        //---- departmentBtn ----
        departmentBtn.setText("Department Management");
        add(departmentBtn);
        departmentBtn.setBounds(402, 180, 195, 50);

        //---- moduleBtn ----
        moduleBtn.setText("Module Management");
        add(moduleBtn);
        moduleBtn.setBounds(615, 180, 195, 50);

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
    private JButton backToProfileBtn;
    private JLabel titleTxt;
    private JButton degreeBtn;
    private JButton departmentBtn;
    private JButton moduleBtn;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
