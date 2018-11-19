package university.UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import university.UI.ProfileScreen;
import university.ScreenManager;
import university.Account;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;
import java.util.ArrayList;

class AccountManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel accountManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private Account account;

    AccountManagementScreen(ScreenManager scr, Account acc, ProfileScreen prof) {
        this.initComponents();
        this.profileScreen = prof;
        this.screen = scr;
        this.account = acc;
    }

    public void draw() throws SQLException {
        this.accountManagement = new JPanel();
        this.accountManagement.setBackground(new Color(70, 70, 70));

        this.accountManagement.add(backToProfileBtn);
        this.accountManagement.add(accountManagementTxt);
        this.accountManagement.add(createBtn);
        this.accountManagement.add(deleteBtn);
        this.accountManagement.setLayout(null);
        this.accountManagement.add(scrollPane1);

        backToProfileBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            this.profileScreen.draw();
        });
        createBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            AccountCreationScreen accountCreate = new AccountCreationScreen(this.screen, this);
            accountCreate.draw();
        });

        DefaultTableModel model = new DefaultTableModel();
        JTable accountTable = new JTable(model);

        Connection con = null;
        Statement stmt = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");

            stmt = con.createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM account;");

            ResultSetMetaData rsmd = res.getMetaData();
            int numberCols = rsmd.getColumnCount();
            while (res.next()) {
                ArrayList<String> row = new ArrayList<String>();

                for (int i = 1; i <= numberCols; i++) {
                    String value = res.getString(i);
                    row.add(value);
                }
                System.out.print(row);
                model.addRow(row.toArray(new String[row.size()]));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
            }
            stmt.close();
        }

       // scrollPane1.getViewport().add(accountTable);
        this.accountManagement.setVisible(true);
    }

    public void returnFromManagement(String status) {
        JLabel statusTxt = new JLabel(status);
        this.accountManagement.add(statusTxt);
        
        try {
        	this.draw();
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        backToProfileBtn = new JButton();
        accountManagementTxt = new JLabel();
        deleteBtn = new JButton();
        createBtn = new JButton();
        scrollPane1 = new JScrollPane();

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
        add(scrollPane1);
        scrollPane1.setBounds(245, 120, 525, 250);

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
    private JButton backToProfileBtn;
    private JLabel accountManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    private JScrollPane scrollPane1;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}
