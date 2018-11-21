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
import university.Student;
import university.TableModel;
import university.Account;
import university.ModuleChoice;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class StudentManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel studentManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private Account account;
    private JTable studentTable;

    private Connection con = null;
    private Statement stmt = null;

    StudentManagementScreen(ScreenManager scr, ProfileScreen prof) {
        this.initComponents();
        this.screen = scr;
        this.profileScreen = prof;
    }

    public void draw() throws SQLException {
        this.studentManagement = new JPanel();
        this.studentManagement.setBackground(new Color(70, 70, 70));

        this.studentManagement.add(backToProfileBtn);
        this.studentManagement.add(studentManagementTxt);
        this.studentManagement.add(createBtn);
        this.studentManagement.add(deleteBtn);
        this.studentManagement.add(tablePanel);
        this.studentManagement.add(registrationBtn);
        this.studentManagement.add(moduleBtn);

        this.tablePanel.setLayout(new BorderLayout());

        this.studentManagement.setLayout(null);

        backToProfileBtn.addActionListener(e -> {
            this.studentManagement.setVisible(false);
            this.profileScreen.draw();
        });
        createBtn.addActionListener(e -> {
            this.studentManagement.setVisible(false);
            StudentCreationScreen studentCreate = new StudentCreationScreen(this.screen, this, this.profileScreen);
            studentCreate.draw();
        });
        deleteBtn.addActionListener(e -> {
            if (studentTable.getSelectedRow() > -1) {
                int index = (int) studentTable.getValueAt(studentTable.getSelectedRow(), 0);
                String username = (String) studentTable.getValueAt(studentTable.getSelectedRow(), 3);
                Student stuToDelete = new Student(index);
                try {
                    stuToDelete.deleteStudent();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                this.studentManagement.setVisible(false);
                this.profileScreen.draw();
                JOptionPane.showMessageDialog(null, "Successfully deleted student: " + username);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a Student to delete");
            }
        });
        moduleBtn.addActionListener(e -> {
            ModuleChoiceScreen choiceScreen = new ModuleChoiceScreen(this.screen, this);
            choiceScreen.draw();
            this.studentManagement.setVisible(false);
        });

        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
            stmt = con.createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM student;");
            studentTable = new JTable(TableModel.buildTableModel(res));
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(studentTable);

            tablePanel.add(scrollPane);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
            }
            stmt.close();
        }

        this.studentManagement.setVisible(true);
        screen.frame.add(this.studentManagement);
    }

    public void returnFromManagement(String status) {
        JLabel statusTxt = new JLabel(status);
        this.studentManagement.add(statusTxt);
        try {
            this.draw();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        backToProfileBtn = new JButton();
        studentManagementTxt = new JLabel();
        deleteBtn = new JButton();
        createBtn = new JButton();
        tablePanel = new JPanel();
        moduleBtn = new JButton();
        registrationBtn = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- studentManagementTxt ----
        studentManagementTxt.setText("Student Management");
        studentManagementTxt.setFont(studentManagementTxt.getFont().deriveFont(studentManagementTxt.getFont().getSize() + 10f));
        studentManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        studentManagementTxt.setForeground(Color.white);
        add(studentManagementTxt);
        studentManagementTxt.setBounds(347, 35, 305, 31);

        //---- deleteBtn ----
        deleteBtn.setText("Delete Student");
        add(deleteBtn);
        deleteBtn.setBounds(415, 470, 170, 30);

        //---- createBtn ----
        createBtn.setText("Create Student");
        add(createBtn);
        createBtn.setBounds(415, 440, 170, 30);

        //======== tablePanel ========
        {
            tablePanel.setLayout(new BorderLayout());
        }
        add(tablePanel);
        tablePanel.setBounds(177, 100, 645, 290);

        //---- moduleBtn ----
        moduleBtn.setText("Add/drop modules");
        add(moduleBtn);
        moduleBtn.setBounds(325, 410, 170, 30);

        //---- registrationBtn ----
        registrationBtn.setText("Student registration");
        add(registrationBtn);
        registrationBtn.setBounds(505, 410, 170, 30);

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
    private JButton backToProfileBtn;
    private JLabel studentManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    private JPanel tablePanel;
    private JButton moduleBtn;
    private JButton registrationBtn;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
