package university.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import university.UI.ProfileScreen;
import university.ScreenManager;
import university.Account;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class AccountManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel accountManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private Account account;
    private Connection connect = null;
    private Statement statement = null;

    AccountManagementScreen(ScreenManager scr, Account acc, ProfileScreen prof) {
        this.initComponents();
        this.profileScreen = prof;
        this.screen = scr;
        this.account = acc;
    }

    public void draw() {
        this.accountManagement = new JPanel();
        this.accountManagement.setBackground(new Color(70, 70, 70));
        JTable accountTable = new JTable();

        this.accountManagement.add(promptTxt);
        this.accountManagement.add(backToProfileBtn);
        this.accountManagement.add(accountManagementTxt);
        this.accountManagement.add(createBtn);
        this.accountManagement.add(deleteBtn);
        this.accountManagement.add(accountTable);

        this.accountManagement.setLayout(null);

        backToProfileBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            this.profileScreen.draw();
        });
        createBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            AccountCreationScreen accountCreate = new AccountCreationScreen(this.screen, this);
            accountCreate.draw();
        });

		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");

			stmt = con.createStatement();
			
		ResultSet res = stmt.executeQuery("SELECT * FROM account;");
		
		ResultSetMetaData rsmd = res.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		while (res.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
		        if (i > 1) System.out.print(",  ");
		        String columnValue = res.getString(i);
		        System.out.print(columnValue + " " + rsmd.getColumnName(i));
		    }
		    System.out.println("");
		}
		
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (stmt != null) {}
				//stmt.close();
		}
        screen.frame.add(this.accountManagement);
    }

    public void returnFromManagement(String status) {
        JLabel statusTxt = new JLabel(status);
        this.accountManagement.add(statusTxt);
        this.draw();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        promptTxt = new JLabel();
        backToProfileBtn = new JButton();
        accountManagementTxt = new JLabel();
        deleteBtn = new JButton();
        createBtn = new JButton();

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

        // ---- promptTxt ----
        promptTxt.setText("output account database here");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(390, 260, 225, promptTxt.getPreferredSize().height);

        // ---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        // ---- accountManagementTxt ----
        accountManagementTxt.setText("Account Management");
        accountManagementTxt
                .setFont(accountManagementTxt.getFont().deriveFont(accountManagementTxt.getFont().getSize() + 10f));
        accountManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        accountManagementTxt.setForeground(Color.white);
        add(accountManagementTxt);
        accountManagementTxt.setBounds(347, 35, 305, 31);

        // ---- deleteBtn ----
        deleteBtn.setText("Delete Account");
        add(deleteBtn);
        deleteBtn.setBounds(415, 465, 170, 30);

        // ---- createBtn ----
        createBtn.setText("Create Account");
        add(createBtn);
        createBtn.setBounds(415, 430, 170, 30);

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
    private JLabel promptTxt;
    private JButton backToProfileBtn;
    private JLabel accountManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    // JFormDesigner - End of variables declaration //GEN-END:variables
}
