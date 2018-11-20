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
import university.Degree;
import university.Department;
import university.ScreenManager;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class DegreeCreationScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel degreeCreation;
    private ScreenManager screen;
    private DegreeManagementScreen degreeManagement;
    private String[] departments;
    private String[] degreeTypes = { "Undergraduate", "Postgraduate" };
    private String[] placementYear = { "No", "Yes" };

    DegreeCreationScreen(ScreenManager scr, DegreeManagementScreen degreeManage) {
        this.initComponents();
        this.screen = scr;
        this.degreeManagement = degreeManage;
    }

    public void draw() {
        this.degreeCreation = new JPanel();
        this.degreeCreation.setBackground(new Color(70, 70, 70));
        this.degreeCreation.setLayout(null);

        this.degreeCreation.add(backToProfileBtn);
        this.degreeCreation.add(createTxt);
        this.degreeCreation.add(promptTxt);
        this.degreeCreation.add(nameTxt);
        this.degreeCreation.add(mainDepartmentTxt);
        this.degreeCreation.add(secondaryTxt);
        this.degreeCreation.add(nameInput);
        this.degreeCreation.add(secondInput);
        this.degreeCreation.add(mainInput);
        this.degreeCreation.add(submitBtn);
        this.degreeCreation.add(degreeTxt);
        this.degreeCreation.add(typeTxt);
        this.degreeCreation.add(typeInput);
        this.degreeCreation.add(placementInput);
        this.degreeCreation.add(placementTxt);

        backToProfileBtn.addActionListener(e -> {
            this.degreeCreation.setVisible(false);
            this.degreeManagement.draw();
        });
        submitBtn.addActionListener((e -> {
            this.degreeCreation.setVisible(false);

            String type = Character.toString((typeInput.getSelectedItem().toString().charAt(0)));
            boolean placement;

            String placementText = placementInput.getSelectedItem().toString();

            if (placementText.equals("Yes")) {
                placement = true;
            } else {
                placement = false;
            }

            try {
                Department dep = Department.getDept(mainInput.getSelectedItem().toString());
                Degree deg = new Degree(nameInput.getText(), dep, type, placement);
                deg.setCode();
                Degree newDeg = deg.createDegree();
                this.degreeManagement.draw();
                JOptionPane.showMessageDialog(null, "Successfully created Degree: " + newDeg.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
                this.degreeManagement.draw();
                JOptionPane.showMessageDialog(null, "SQL error, please try again");
            }
        }));

        screen.frame.add(this.degreeCreation);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        createTxt = new JLabel();
        promptTxt = new JLabel();
        nameTxt = new JLabel();
        codeTxt = new JLabel();
        mainDepartmentTxt = new JLabel();
        secondaryTxt = new JLabel();
        nameInput = new JTextField();
        codeInput = new JTextField();
        secondInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        degreeTxt = new JLabel();
        typeTxt = new JLabel();
        typeInput = new JComboBox(degreeTypes);
        placementTxt = new JLabel();
        placementInput = new JComboBox(placementYear);

        try {
            mainInput = new JComboBox((Department.getAllDepNames().toArray()));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // ======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
                new javax.swing.border.EmptyBorder(0, 0, 0, 0), "JFormDesigner Evaluation",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM,
                new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName()))
                    throw new RuntimeException();
            }
        });

        setLayout(null);

        // ---- createTxt ----
        createTxt.setText("Create Degree");
        createTxt.setFont(createTxt.getFont().deriveFont(createTxt.getFont().getSize() + 12f));
        createTxt.setHorizontalAlignment(SwingConstants.CENTER);
        createTxt.setForeground(Color.white);
        add(createTxt);
        createTxt.setBounds(389, 90, 220, createTxt.getPreferredSize().height);

        // ---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        // ---- nameTxt ----
        nameTxt.setText("Name");
        nameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        nameTxt.setFont(nameTxt.getFont().deriveFont(nameTxt.getFont().getSize() + 3f));
        nameTxt.setForeground(Color.white);
        add(nameTxt);
        nameTxt.setBounds(185, 185, 185, nameTxt.getPreferredSize().height);

        // ---- codeTxt ----
        codeTxt.setText("Code");
        codeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        codeTxt.setFont(codeTxt.getFont().deriveFont(codeTxt.getFont().getSize() + 3f));
        codeTxt.setForeground(Color.white);
        add(codeTxt);
        codeTxt.setBounds(230, 227, 140, 16);

        // ---- mainDepartmentTxt ----
        mainDepartmentTxt.setText("Main Department");
        mainDepartmentTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        mainDepartmentTxt.setFont(mainDepartmentTxt.getFont().deriveFont(mainDepartmentTxt.getFont().getSize() + 3f));
        mainDepartmentTxt.setForeground(Color.white);
        add(mainDepartmentTxt);
        mainDepartmentTxt.setBounds(155, 265, 215, 16);

        // ---- secondaryTxt ----
        secondaryTxt.setText("Secondary Departments");
        secondaryTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        secondaryTxt.setFont(secondaryTxt.getFont().deriveFont(secondaryTxt.getFont().getSize() + 3f));
        secondaryTxt.setForeground(Color.white);
        add(secondaryTxt);
        secondaryTxt.setBounds(155, 305, 215, 16);
        add(nameInput);
        nameInput.setBounds(385, 180, 235, nameInput.getPreferredSize().height);
        add(codeInput);
        codeInput.setBounds(385, 220, 235, 30);
        add(secondInput);
        secondInput.setBounds(385, 295, 235, 30);
        add(mainInput);
        mainInput.setBounds(385, 260, 235, mainInput.getPreferredSize().height);

        // ---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 445, 135, submitBtn.getPreferredSize().height);

        // ---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        // ---- degreeTxt ----
        degreeTxt.setText("Degree Management");
        degreeTxt.setFont(degreeTxt.getFont().deriveFont(degreeTxt.getFont().getSize() + 10f));
        degreeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        degreeTxt.setForeground(Color.white);
        add(degreeTxt);
        degreeTxt.setBounds(347, 35, 305, 31);

        // ---- typeTxt ----
        typeTxt.setText("Type");
        typeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        typeTxt.setFont(typeTxt.getFont().deriveFont(typeTxt.getFont().getSize() + 3f));
        typeTxt.setForeground(Color.white);
        add(typeTxt);
        typeTxt.setBounds(130, 343, 240, 16);
        add(typeInput);
        typeInput.setBounds(385, 335, 235, 30);

        // ---- placementTxt ----
        placementTxt.setText("Placement year?");
        placementTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        placementTxt.setFont(placementTxt.getFont().deriveFont(placementTxt.getFont().getSize() + 3f));
        placementTxt.setForeground(Color.white);
        add(placementTxt);
        placementTxt.setBounds(130, 380, 240, 16);
        add(placementInput);
        placementInput.setBounds(385, 375, 235, 30);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < getComponentCount(); i++) {
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
    private JLabel mainDepartmentTxt;
    private JLabel secondaryTxt;
    private JTextField nameInput;
    private JTextField codeInput;
    private JTextField secondInput;
    private JComboBox mainInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel degreeTxt;
    private JLabel typeTxt;
    private JComboBox typeInput;
    private JLabel placementTxt;
    private JComboBox placementInput;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
