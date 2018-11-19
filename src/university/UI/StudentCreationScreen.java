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

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class StudentCreationScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel studentCreationJPanel;
    private ScreenManager screen;
    private StudentManagementScreen studentManagement;

    StudentCreationScreen(ScreenManager scr, StudentManagementScreen studentManage) {
        this.initComponents();
        this.screen = scr;
        this.studentManagement = studentManage;
    }

    public void draw() {
        this.studentCreationJPanel = new JPanel();
        this.studentCreationJPanel.setBackground(new Color(70, 70, 70));
        this.studentCreationJPanel.setLayout(null);

        this.studentCreationJPanel.add(submitBtn);
        this.studentCreationJPanel.add(promptTxt);
        this.studentCreationJPanel.add(welcomeTxt);
        this.studentCreationJPanel.add(titleTxt);
        this.studentCreationJPanel.add(forenameTxt);
        this.studentCreationJPanel.add(surnameTxt);
        this.studentCreationJPanel.add(titleInput);
        this.studentCreationJPanel.add(forenameInput);
        this.studentCreationJPanel.add(surnameInput);
        this.studentCreationJPanel.add(backToProfileBtn);
        this.studentCreationJPanel.add(studentManagementTxt);
        this.studentCreationJPanel.add(degreeTxt);
        this.studentCreationJPanel.add(degreeInput);
        this.studentCreationJPanel.add(submitBtn);
        this.studentCreationJPanel.add(degreeTxt);
        this.studentCreationJPanel.add(tutorTxt);
        this.studentCreationJPanel.add(tutorInput);
        this.studentCreationJPanel.add(degreeInput);

        backToProfileBtn.addActionListener(e -> {
            this.studentCreationJPanel.setVisible(false);
            this.studentManagement.draw();
        });

        screen.frame.add(this.studentCreationJPanel);
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
        titleInput = new JTextField();
        forenameInput = new JTextField();
        surnameInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        studentManagementTxt = new JLabel();
        degreeTxt = new JLabel();
        tutorTxt = new JLabel();
        degreeInput = new JTextField();
        tutorInput = new JTextField();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- welcomeTxt ----
        welcomeTxt.setText("Student Account");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 12f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeTxt.setForeground(Color.white);
        add(welcomeTxt);
        welcomeTxt.setBounds(389, 90, 220, welcomeTxt.getPreferredSize().height);

        //---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        //---- titleTxt ----
        titleTxt.setText("Title");
        titleTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 3f));
        titleTxt.setForeground(Color.white);
        add(titleTxt);
        titleTxt.setBounds(185, 185, 185, titleTxt.getPreferredSize().height);

        //---- forenameTxt ----
        forenameTxt.setText("Forename");
        forenameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        forenameTxt.setFont(forenameTxt.getFont().deriveFont(forenameTxt.getFont().getSize() + 3f));
        forenameTxt.setForeground(Color.white);
        add(forenameTxt);
        forenameTxt.setBounds(230, 227, 140, 16);

        //---- surnameTxt ----
        surnameTxt.setText("Surname");
        surnameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        surnameTxt.setFont(surnameTxt.getFont().deriveFont(surnameTxt.getFont().getSize() + 3f));
        surnameTxt.setForeground(Color.white);
        add(surnameTxt);
        surnameTxt.setBounds(155, 265, 215, 16);
        add(titleInput);
        titleInput.setBounds(382, 180, 235, titleInput.getPreferredSize().height);
        add(forenameInput);
        forenameInput.setBounds(382, 220, 235, 30);
        add(surnameInput);
        surnameInput.setBounds(382, 260, 235, 30);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 465, 135, submitBtn.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- studentManagementTxt ----
        studentManagementTxt.setText("Account Management");
        studentManagementTxt.setFont(studentManagementTxt.getFont().deriveFont(studentManagementTxt.getFont().getSize() + 10f));
        studentManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        studentManagementTxt.setForeground(Color.white);
        add(studentManagementTxt);
        studentManagementTxt.setBounds(347, 35, 305, 31);

        //---- degreeTxt ----
        degreeTxt.setText("Degree");
        degreeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        degreeTxt.setFont(degreeTxt.getFont().deriveFont(degreeTxt.getFont().getSize() + 3f));
        degreeTxt.setForeground(Color.white);
        add(degreeTxt);
        degreeTxt.setBounds(155, 305, 215, 16);

        //---- tutorTxt ----
        tutorTxt.setText("Tutor");
        tutorTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        tutorTxt.setFont(tutorTxt.getFont().deriveFont(tutorTxt.getFont().getSize() + 3f));
        tutorTxt.setForeground(Color.white);
        add(tutorTxt);
        tutorTxt.setBounds(155, 345, 215, 16);
        add(degreeInput);
        degreeInput.setBounds(382, 300, 235, 30);
        add(tutorInput);
        tutorInput.setBounds(382, 340, 235, 30);

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
    private JTextField titleInput;
    private JTextField forenameInput;
    private JTextField surnameInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel studentManagementTxt;
    private JLabel degreeTxt;
    private JLabel tutorTxt;
    private JTextField degreeInput;
    private JTextField tutorInput;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
