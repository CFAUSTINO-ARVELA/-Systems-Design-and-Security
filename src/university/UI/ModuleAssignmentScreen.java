package university.UI;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 21 13:18:41 GMT 2018
 */

import university.Degree;
import university.Module;
import university.ScreenManager;
import university.TableModel;

public class ModuleAssignmentScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel moduleAssScreen;
    private ScreenManager screen;
    private TeachingManagementScreen teachScreen;
    private String[] coreList = { "Yes", "No" };
    private String[] levelList = { "1", "2", "3", "4" };

    private JTable degreeTable;
    private JTable moduleTable;

    public ModuleAssignmentScreen(ScreenManager scr, TeachingManagementScreen teach) {
        this.screen = scr;
        this.teachScreen = teach;
        initComponents();
        this.initListeners();
    }

    private void initListeners() {
        backToProfileBtn.addActionListener(e -> {
            this.moduleAssScreen.setVisible(false);
            try {
                this.teachScreen.draw();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        submitBtn.addActionListener((e -> {

            if (degreeTable.getSelectedRow() > -1 && moduleTable.getSelectedRow() > -1) {
                String degreeCode = (String) degreeTable.getValueAt(degreeTable.getSelectedRow(), 0);
                String moduleCode = (String) moduleTable.getValueAt(moduleTable.getSelectedRow(), 1);
                int level = Integer.parseInt((String) levelInput.getSelectedItem());
                boolean core;

                if (coreInput.getSelectedItem().equals("Yes")) {
                    core = true;
                } else {
                    core = false;
                }

                try {
                    if (Degree.getDegree(degreeCode).getType().equals("MSc")) {

                        if (level != 4) {
                            JOptionPane.showMessageDialog(null, "The degree selected does not offer level " + level
                                    + ". \n Please select the correct level.");
                        } else if (Degree.getCredits(degreeCode, level)
                                + Module.getModule(moduleCode).getCredits() > 180 && core) {
                            JOptionPane.showMessageDialog(null,
                                    "Adding this module will exceed 180 core credits. \n Please insert the correct information.");
                        } else {
                            Module.assignModule(degreeCode, moduleCode, level, core);
                           

                            this.moduleAssScreen.setVisible(false);
                            ModuleAssignmentScreen newScreen = new ModuleAssignmentScreen(this.screen,
                                    this.teachScreen);

                            newScreen.draw();

                            JOptionPane.showMessageDialog(null, "Successfully linked module and degree");
                        }

                    } else {

                        if (Degree.getDegree(degreeCode).getType().charAt(0) == 'B' && level == 4) {
                            JOptionPane.showMessageDialog(null,
                                    "The degree selected does not offer level 4. \n Please select the correct level.");
                        } else if (Degree.getCredits(degreeCode, level)
                                + Module.getModule(moduleCode).getCredits() > 120 && core) {
                            JOptionPane.showMessageDialog(null,
                                    "Adding this module will exceed 120 core credits. \n Please insert the correct information.");
                        } else if (Degree.getNoMod(degreeCode, moduleCode, level) != 0) {
                            JOptionPane.showMessageDialog(null,
                                    "This module is already assigned to this degree's level " + level
                                            + ". \n Please insert the correct information.");
                        } else {
                            Module.assignModule(degreeCode, moduleCode, level, core);
                            

                            this.moduleAssScreen.setVisible(false);
                            ModuleAssignmentScreen newScreen = new ModuleAssignmentScreen(this.screen,
                                    this.teachScreen);

                            newScreen.draw();

                            JOptionPane.showMessageDialog(null, "Successfully linked module and degree");
                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select both a degree and module to link");

            }
        }));

    }

    public void draw() throws Exception {
        this.moduleAssScreen = new JPanel();
        this.moduleAssScreen.setBackground(new Color(70, 70, 70));

        this.moduleAssScreen.add(degreeTxt);
        this.moduleAssScreen.add(degreePanel);
        this.moduleAssScreen.add(modulePanel);
        this.moduleAssScreen.add(promptTxt);
        this.moduleAssScreen.add(levelTxt);
        this.moduleAssScreen.add(levelInput);
        this.moduleAssScreen.add(coreTxt);
        this.moduleAssScreen.add(coreInput);
        this.moduleAssScreen.add(submitBtn);
        this.moduleAssScreen.add(backToProfileBtn);

        this.moduleAssScreen.setLayout(null);
        this.degreePanel.setLayout(new BorderLayout());
        this.modulePanel.setLayout(new BorderLayout());

        degreeTable = new JTable(TableModel.buildTableModel(Degree.getDegList()));
        moduleTable = new JTable(TableModel.buildTableModel(Module.getModList()));
        JScrollPane degreeScroll = new JScrollPane();
        JScrollPane moduleScroll = new JScrollPane();
        degreeScroll.setViewportView(degreeTable);
        moduleScroll.setViewportView(moduleTable);

        modulePanel.add(moduleScroll);
        degreePanel.add(degreeScroll);

        this.screen.frame.add(this.moduleAssScreen);

    }

    private void initComponents() {
        degreeTxt = new JLabel();
        levelTxt = new JLabel();
        coreTxt = new JLabel();
        coreInput = new JComboBox(coreList);
        levelInput = new JComboBox(levelList);
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        modulePanel = new JPanel();
        degreePanel = new JPanel();
        promptTxt = new JLabel();

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

        // ---- degreeTxt ----
        degreeTxt.setText("Module Assignment");
        degreeTxt.setFont(degreeTxt.getFont().deriveFont(degreeTxt.getFont().getSize() + 10f));
        degreeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        degreeTxt.setForeground(Color.white);
        add(degreeTxt);
        degreeTxt.setBounds(347, 35, 305, 31);

        // ---- levelTxt ----
        levelTxt.setText("Level:");
        levelTxt.setFont(levelTxt.getFont().deriveFont(levelTxt.getFont().getSize() + 2f));
        levelTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        levelTxt.setForeground(Color.white);
        add(levelTxt);
        levelTxt.setBounds(315, 370, 100, 35);

        // ---- coreTxt ----
        coreTxt.setText("Core?");
        coreTxt.setFont(coreTxt.getFont().deriveFont(coreTxt.getFont().getSize() + 2f));
        coreTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        coreTxt.setForeground(Color.white);
        add(coreTxt);
        coreTxt.setBounds(315, 420, 100, 35);
        add(coreInput);
        coreInput.setBounds(435, 420, 130, coreInput.getPreferredSize().height);
        add(levelInput);
        levelInput.setBounds(435, 375, 130, levelInput.getPreferredSize().height);

        // ---- submitBtn ----
        submitBtn.setText("Link");
        add(submitBtn);
        submitBtn.setBounds(415, 465, 170, 30);

        // ---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(415, 500, 170, 50);

        // ======== modulePanel ========
        {
            modulePanel.setLayout(null);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < modulePanel.getComponentCount(); i++) {
                    Rectangle bounds = modulePanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = modulePanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                modulePanel.setMinimumSize(preferredSize);
                modulePanel.setPreferredSize(preferredSize);
            }
        }
        add(modulePanel);
        modulePanel.setBounds(85, 115, 391, 226);

        // ======== degreePanel ========
        {
            degreePanel.setLayout(null);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < degreePanel.getComponentCount(); i++) {
                    Rectangle bounds = degreePanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = degreePanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                degreePanel.setMinimumSize(preferredSize);
                degreePanel.setPreferredSize(preferredSize);
            }
        }
        add(degreePanel);
        degreePanel.setBounds(520, 115, 391, 226);

        // ---- promptTxt ----
        promptTxt.setText("Please select a degree and a module from the lists");
        promptTxt.setFont(promptTxt.getFont().deriveFont(promptTxt.getFont().getSize() + 2f));
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(249, 75, 500, 31);

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
    }

    private JLabel degreeTxt;
    private JLabel levelTxt;
    private JLabel coreTxt;
    private JComboBox coreInput;
    private JComboBox levelInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JPanel modulePanel;
    private JPanel degreePanel;
    private JLabel promptTxt;
}
