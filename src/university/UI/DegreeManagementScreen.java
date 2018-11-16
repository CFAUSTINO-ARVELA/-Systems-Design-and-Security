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

class DegreeManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel degreeManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private Account account;

    DegreeManagementScreen(ScreenManager scr,TeachingManagementScreen teach) {
        this.initComponents();
        this.screen = scr;
    }

    public void draw() {
        this.degreeManagement = new JPanel();
        this.degreeManagement.setBackground(new Color(70, 70, 70));

        this.degreeManagement.add(promptTxt);
        this.degreeManagement.add(backToTeachingBtn);
        this.degreeManagement.add(degreeManagementTxt);
        this.degreeManagement.add(createBtn);
        this.degreeManagement.add(deleteBtn);

        this.degreeManagement.setLayout(null);

        backToTeachingBtn.addActionListener(e -> {
            this.degreeManagement.setVisible(false);
            // this.profileScreen.draw();
        });
        createBtn.addActionListener(e -> {
            this.degreeManagement.setVisible(false);
            // AccountCreationScreen accountCreate = new AccountCreationScreen(this.screen, this);
            // accountCreate.draw();
        });

        screen.frame.add(this.degreeManagement);
    }

    public void returnFromManagement(String status) {
        JLabel statusTxt = new JLabel(status);
        this.degreeManagement.add(statusTxt);
        this.draw();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        promptTxt = new JLabel();
        backToTeachingBtn = new JButton();
        degreeManagementTxt = new JLabel();
        deleteBtn = new JButton();
        createBtn = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- promptTxt ----
        promptTxt.setText("output for degrees table here");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(390, 260, 225, promptTxt.getPreferredSize().height);

        //---- backToTeachingBtn ----
        backToTeachingBtn.setText("Back");
        add(backToTeachingBtn);
        backToTeachingBtn.setBounds(414, 500, 170, 50);

        //---- degreeManagementTxt ----
        degreeManagementTxt.setText("Degree Management");
        degreeManagementTxt.setFont(degreeManagementTxt.getFont().deriveFont(degreeManagementTxt.getFont().getSize() + 10f));
        degreeManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        degreeManagementTxt.setForeground(Color.white);
        add(degreeManagementTxt);
        degreeManagementTxt.setBounds(347, 35, 305, 31);

        //---- deleteBtn ----
        deleteBtn.setText("Delete Delete");
        add(deleteBtn);
        deleteBtn.setBounds(415, 465, 170, 30);

        //---- createBtn ----
        createBtn.setText("Create Degree");
        add(createBtn);
        createBtn.setBounds(415, 430, 170, 30);

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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    private JLabel promptTxt;
    private JButton backToTeachingBtn;
    private JLabel degreeManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
