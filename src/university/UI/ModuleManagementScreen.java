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

class ModuleManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel moduleScreen;
    private ScreenManager screen;
    private TeachingManagementScreen teachingScreen;
    private Account account;

    ModuleManagementScreen(ScreenManager scr,TeachingManagementScreen teach) {
        this.initComponents();
        this.screen = scr;
        this.teachingScreen = teach;
    }

    public void draw() {
        this.moduleScreen = new JPanel();
        this.moduleScreen.setBackground(new Color(70, 70, 70));

        this.moduleScreen.add(promptTxt);
        this.moduleScreen.add(backToTeachingBtn);
        this.moduleScreen.add(ModuleManagementTxt);
        this.moduleScreen.add(createBtn);
        this.moduleScreen.add(deleteBtn);

        this.moduleScreen.setLayout(null);

        backToTeachingBtn.addActionListener(e -> {
            this.moduleScreen.setVisible(false);
            this.teachingScreen.draw();
        });
        createBtn.addActionListener(e -> {
            this.moduleScreen.setVisible(false);
            ModuleCreationScreen moduleCreate = new ModuleCreationScreen(this.screen, this);
            moduleCreate.draw();
        });

        screen.frame.add(this.moduleScreen);
    }

    // public void returnFromManagement(String status) {
    //     JLabel statusTxt = new JLabel(status);
    //     this.moduleScreen.add(statusTxt);
    //     this.draw();
    // }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    // JFormDesigner - End of variables declaration  //GEN-END:variables

public class ModuleManagementScreen extends JPanel {
    public ModuleManagementScreen() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        promptTxt = new JLabel();
        backToTeachingBtn = new JButton();
        moduleManagementTxt = new JLabel();
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
        promptTxt.setText("output for module table here");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(390, 260, 225, promptTxt.getPreferredSize().height);

        //---- backToTeachingBtn ----
        backToTeachingBtn.setText("Back");
        add(backToTeachingBtn);
        backToTeachingBtn.setBounds(414, 500, 170, 50);

        //---- moduleManagementTxt ----
        moduleManagementTxt.setText("Module Management");
        moduleManagementTxt.setFont(moduleManagementTxt.getFont().deriveFont(moduleManagementTxt.getFont().getSize() + 10f));
        moduleManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        moduleManagementTxt.setForeground(Color.white);
        add(moduleManagementTxt);
        moduleManagementTxt.setBounds(347, 35, 305, 31);

        //---- deleteBtn ----
        deleteBtn.setText("Delete Module");
        add(deleteBtn);
        deleteBtn.setBounds(415, 465, 170, 30);

        //---- createBtn ----
        createBtn.setText("Create Module");
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
    private JLabel moduleManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
}
