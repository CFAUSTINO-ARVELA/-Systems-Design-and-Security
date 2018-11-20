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
import university.TableModel;
import university.Account;
import university.Department;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class DepartmentManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel departmentScreen;
    private ScreenManager screen;
    private TeachingManagementScreen teachingScreen;
    private Account account;
    private JTable departmentTable;
    private Connection con = null;
    private Statement stmt = null;

    DepartmentManagementScreen(ScreenManager scr,TeachingManagementScreen teach) {
        this.initComponents();
        this.screen = scr;
        this.teachingScreen = teach;
    }

    public void draw() {
        this.departmentScreen = new JPanel();
        this.departmentScreen.setBackground(new Color(70, 70, 70));

        this.departmentScreen.add(promptTxt);
        this.departmentScreen.add(backToTeachingBtn);
        this.departmentScreen.add(departmentManagementTxt);
        this.departmentScreen.add(createBtn);
        this.departmentScreen.add(deleteBtn);
        this.departmentScreen.add(tablePanel);

        this.tablePanel.setLayout(new BorderLayout());


        this.departmentScreen.setLayout(null);

        backToTeachingBtn.addActionListener(e -> {
            this.departmentScreen.setVisible(false);
            this.teachingScreen.draw();
        });
        createBtn.addActionListener(e -> {
            this.departmentScreen.setVisible(false);
            DepartmentCreationScreen departmentCreate = new DepartmentCreationScreen(this.screen, this);
            departmentCreate.draw();
        });
        deleteBtn.addActionListener(e -> {
            if (departmentTable.getSelectedRow() > -1) {
                String code = (String) departmentTable.getValueAt(departmentTable.getSelectedRow(), 0);
                String name = (String) departmentTable.getValueAt(departmentTable.getSelectedRow(), 1);
                Department depToDelete = new Department(code);
                try {
                    depToDelete.deleteDep();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.departmentScreen.setVisible(false);
                this.teachingScreen.draw();
                JOptionPane.showMessageDialog(null, "Successfully deleted department: " + name);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a Department to delete");
            }
        });

        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
            stmt = con.createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM department;");
            departmentTable = new JTable(TableModel.buildTableModel(res));
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(departmentTable);

            tablePanel.add(scrollPane);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
            }
            try {
                stmt.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        screen.frame.add(this.departmentScreen);
    }

    // public void returnFromManagement(String status) {
    //     JLabel statusTxt = new JLabel(status);
    //     this.departmentScreen.add(statusTxt);
    //     this.draw();
    // }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        promptTxt = new JLabel();
        backToTeachingBtn = new JButton();
        departmentManagementTxt = new JLabel();
        deleteBtn = new JButton();
        createBtn = new JButton();
        tablePanel = new JPanel();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- promptTxt ----
        promptTxt.setText("output for department table here");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(390, 260, 225, promptTxt.getPreferredSize().height);

        //---- backToTeachingBtn ----
        backToTeachingBtn.setText("Back");
        add(backToTeachingBtn);
        backToTeachingBtn.setBounds(414, 500, 170, 50);

        //---- departmentManagementTxt ----
        departmentManagementTxt.setText("Department Management");
        departmentManagementTxt.setFont(departmentManagementTxt.getFont().deriveFont(departmentManagementTxt.getFont().getSize() + 10f));
        departmentManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        departmentManagementTxt.setForeground(Color.white);
        add(departmentManagementTxt);
        departmentManagementTxt.setBounds(347, 35, 305, 31);

        //---- deleteBtn ----
        deleteBtn.setText("Delete Department");
        add(deleteBtn);
        deleteBtn.setBounds(415, 465, 170, 30);

        //---- createBtn ----
        createBtn.setText("Create Department");
        add(createBtn);
        createBtn.setBounds(415, 430, 170, 30);

        tablePanel.setBounds(177, 100, 645, 290);

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
    private JLabel departmentManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    private JPanel tablePanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
