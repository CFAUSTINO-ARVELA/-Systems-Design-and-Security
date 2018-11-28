package university.UI;

import university.ScreenManager;
import university.TableModel;
import university.Account;
import university.Department;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public TeachingManagementScreen getTecMaangScree() {
    	return teachingScreen;
    }
    public void draw() throws SQLException{
        this.departmentScreen = new JPanel();
        this.departmentScreen.setBackground(new Color(70, 70, 70));

        this.departmentScreen.add(backToTeachingBtn);
        this.departmentScreen.add(departmentManagementTxt);
        this.departmentScreen.add(createBtn);
        this.departmentScreen.add(deleteBtn);
        this.departmentScreen.add(tablePanel);

        this.tablePanel.setLayout(new BorderLayout());


        this.departmentScreen.setLayout(null);

        backToTeachingBtn.addActionListener(e -> {
            this.departmentScreen.setVisible(false);
            try {
				this.teachingScreen.draw();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        createBtn.addActionListener(e -> {
            this.departmentScreen.setVisible(false);
            DepartmentCreationScreen departmentCreate = new DepartmentCreationScreen(this.screen, this);
            try {
				departmentCreate.draw();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        deleteBtn.addActionListener(e -> {
            if (departmentTable.getSelectedRow() > -1) {
                String code = (String) departmentTable.getValueAt(departmentTable.getSelectedRow(), 0);
                String name = (String) departmentTable.getValueAt(departmentTable.getSelectedRow(), 1);
                Department depToDelete = new Department(code);
               
                
                JLabel label_warning = new JLabel("Warning: Deleting this department will delete all its degrees.");
                JLabel label_login = new JLabel("If you wish to continue please insert your account details.");
                JLabel labem_email = new JLabel("Email:");
                JTextField email = new JTextField();

                JLabel label_password = new JLabel("Password:");
                JPasswordField password = new JPasswordField();

                Object[] array = {label_warning, label_login, labem_email, email, label_password, password };

                int res = JOptionPane.showConfirmDialog(null, array, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (res == JOptionPane.OK_OPTION) {
	                try {
	                	if(Account.delVerification(email.getText(), password.getText(), 3)) {
	                		depToDelete.deleteDep();
	                		JOptionPane.showMessageDialog(null, "Successfully deleted department: " + name);
	                	}else
	                		JOptionPane.showMessageDialog(null, "Please insert the correct account details");
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }
	                this.departmentScreen.setVisible(false);
	                DepartmentManagementScreen newDepScreen = new DepartmentManagementScreen(this.screen,this.teachingScreen);
	                try {
						newDepScreen.draw();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a Department to delete");
            }
        });

        Department dep = new Department();
        departmentTable = new JTable(TableModel.buildTableModel(dep.getDeptList()));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(departmentTable);

        tablePanel.add(scrollPane);


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
