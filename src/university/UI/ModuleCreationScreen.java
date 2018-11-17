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

class ModuleCreationScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel moduleCreationScreen;
    private ScreenManager screen;
    private ModuleManagementScreen moduleManagement;

    ModuleCreationScreen(ScreenManager scr, ModuleManagementScreen moduleManage) {
        this.initComponents();
        this.screen = scr;
        this.moduleManagement = moduleManage;
    }

    public void draw() {
        this.moduleCreationScreen = new JPanel();
        this.moduleCreationScreen.setBackground(new Color(70, 70, 70));
        this.moduleCreationScreen.setLayout(null);
     
        this.moduleCreationScreen.add(backToProfileBtn);
        this.moduleCreationScreen.add(createTxt);
        this.moduleCreationScreen.add(promptTxt);
        this.moduleCreationScreen.add(nameTxt);
        this.moduleCreationScreen.add(codeTxt);
        this.moduleCreationScreen.add(nameInput);
        this.moduleCreationScreen.add(codeInput);
        this.moduleCreationScreen.add(submitBtn);
        this.moduleCreationScreen.add(moduleTxt);
        this.moduleCreationScreen.add(creditsInput);
        this.moduleCreationScreen.add(creditsTxt);
        this.moduleCreationScreen.add(durationInput);
        this.moduleCreationScreen.add(durationTxt);

        backToProfileBtn.addActionListener(e -> {
            this.moduleCreationScreen.setVisible(false);
            this.moduleManagement.draw();
        });

        screen.frame.add(this.moduleCreationScreen);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        createTxt = new JLabel();
        promptTxt = new JLabel();
        nameTxt = new JLabel();
        codeTxt = new JLabel();
        nameInput = new JTextField();
        codeInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        moduleTxt = new JLabel();
        creditsTxt = new JLabel();
        creditsInput = new JTextField();
        durationTxt = new JLabel();
        durationInput = new JComboBox();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- createTxt ----
        createTxt.setText("Create Module");
        createTxt.setFont(createTxt.getFont().deriveFont(createTxt.getFont().getSize() + 12f));
        createTxt.setHorizontalAlignment(SwingConstants.CENTER);
        createTxt.setForeground(Color.white);
        add(createTxt);
        createTxt.setBounds(350, 90, 299, createTxt.getPreferredSize().height);

        //---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        //---- nameTxt ----
        nameTxt.setText("Name");
        nameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        nameTxt.setFont(nameTxt.getFont().deriveFont(nameTxt.getFont().getSize() + 3f));
        nameTxt.setForeground(Color.white);
        add(nameTxt);
        nameTxt.setBounds(185, 185, 185, nameTxt.getPreferredSize().height);

        //---- codeTxt ----
        codeTxt.setText("Code");
        codeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        codeTxt.setFont(codeTxt.getFont().deriveFont(codeTxt.getFont().getSize() + 3f));
        codeTxt.setForeground(Color.white);
        add(codeTxt);
        codeTxt.setBounds(230, 230, 140, 16);
        add(nameInput);
        nameInput.setBounds(385, 180, 235, nameInput.getPreferredSize().height);
        add(codeInput);
        codeInput.setBounds(385, 225, 235, 30);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 445, 135, submitBtn.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- moduleTxt ----
        moduleTxt.setText("Module Management");
        moduleTxt.setFont(moduleTxt.getFont().deriveFont(moduleTxt.getFont().getSize() + 10f));
        moduleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        moduleTxt.setForeground(Color.white);
        add(moduleTxt);
        moduleTxt.setBounds(347, 35, 305, 31);

        //---- creditsTxt ----
        creditsTxt.setText("Default Credits");
        creditsTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        creditsTxt.setFont(creditsTxt.getFont().deriveFont(creditsTxt.getFont().getSize() + 3f));
        creditsTxt.setForeground(Color.white);
        add(creditsTxt);
        creditsTxt.setBounds(230, 275, 140, 16);
        add(creditsInput);
        creditsInput.setBounds(385, 270, 235, 30);

        //---- durationTxt ----
        durationTxt.setText("Duration");
        durationTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        durationTxt.setFont(durationTxt.getFont().deriveFont(durationTxt.getFont().getSize() + 3f));
        durationTxt.setForeground(Color.white);
        add(durationTxt);
        durationTxt.setBounds(230, 320, 140, 16);
        add(durationInput);
        durationInput.setBounds(377, 315, 250, durationInput.getPreferredSize().height);

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
    private JLabel createTxt;
    private JLabel promptTxt;
    private JLabel nameTxt;
    private JLabel codeTxt;
    private JTextField nameInput;
    private JTextField codeInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel moduleTxt;
    private JLabel creditsTxt;
    private JTextField creditsInput;
    private JLabel durationTxt;
    private JComboBox durationInput;
    // JFormDesigner - End of variables declaration //GEN-END:variables

public class ModuleCreationScreen extends JPanel {
    public ModuleCreationScreen() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        createTxt = new JLabel();
        promptTxt = new JLabel();
        nameTxt = new JLabel();
        codeTxt = new JLabel();
        nameInput = new JTextField();
        codeInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        departmentTxt = new JLabel();
        creditsTxt = new JLabel();
        creditsInput = new JTextField();
        durationTxt = new JLabel();
        durationInput = new JComboBox();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- createTxt ----
        createTxt.setText("Create Module");
        createTxt.setFont(createTxt.getFont().deriveFont(createTxt.getFont().getSize() + 12f));
        createTxt.setHorizontalAlignment(SwingConstants.CENTER);
        createTxt.setForeground(Color.white);
        add(createTxt);
        createTxt.setBounds(350, 90, 299, createTxt.getPreferredSize().height);

        //---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        //---- nameTxt ----
        nameTxt.setText("Name");
        nameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        nameTxt.setFont(nameTxt.getFont().deriveFont(nameTxt.getFont().getSize() + 3f));
        nameTxt.setForeground(Color.white);
        add(nameTxt);
        nameTxt.setBounds(185, 185, 185, nameTxt.getPreferredSize().height);

        //---- codeTxt ----
        codeTxt.setText("Code");
        codeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        codeTxt.setFont(codeTxt.getFont().deriveFont(codeTxt.getFont().getSize() + 3f));
        codeTxt.setForeground(Color.white);
        add(codeTxt);
        codeTxt.setBounds(230, 230, 140, 16);
        add(nameInput);
        nameInput.setBounds(385, 180, 235, nameInput.getPreferredSize().height);
        add(codeInput);
        codeInput.setBounds(385, 225, 235, 30);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 445, 135, submitBtn.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- departmentTxt ----
        departmentTxt.setText("Module Management");
        departmentTxt.setFont(departmentTxt.getFont().deriveFont(departmentTxt.getFont().getSize() + 10f));
        departmentTxt.setHorizontalAlignment(SwingConstants.CENTER);
        departmentTxt.setForeground(Color.white);
        add(departmentTxt);
        departmentTxt.setBounds(347, 35, 305, 31);

        //---- creditsTxt ----
        creditsTxt.setText("Default Credits");
        creditsTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        creditsTxt.setFont(creditsTxt.getFont().deriveFont(creditsTxt.getFont().getSize() + 3f));
        creditsTxt.setForeground(Color.white);
        add(creditsTxt);
        creditsTxt.setBounds(230, 275, 140, 16);
        add(creditsInput);
        creditsInput.setBounds(385, 270, 235, 30);

        //---- durationTxt ----
        durationTxt.setText("Duration");
        durationTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        durationTxt.setFont(durationTxt.getFont().deriveFont(durationTxt.getFont().getSize() + 3f));
        durationTxt.setForeground(Color.white);
        add(durationTxt);
        durationTxt.setBounds(230, 320, 140, 16);
        add(durationInput);
        durationInput.setBounds(377, 315, 250, durationInput.getPreferredSize().height);

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
    private JLabel createTxt;
    private JLabel promptTxt;
    private JLabel nameTxt;
    private JLabel codeTxt;
    private JTextField nameInput;
    private JTextField codeInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel departmentTxt;
    private JLabel creditsTxt;
    private JTextField creditsInput;
    private JLabel durationTxt;
    private JComboBox durationInput;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
}
