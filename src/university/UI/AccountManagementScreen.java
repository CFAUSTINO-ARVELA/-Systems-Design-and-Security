package university.UI;

import university.UI.ProfileScreen;
import university.ScreenManager;
import university.Account;
import university.TableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


class AccountManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel accountManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private Account account;
    private JTable accountTable;

    private Connection con = null;
    private Statement stmt = null;

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

        this.accountManagement.add(tablePanel);

        backToProfileBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            this.profileScreen.draw();
        });
        createBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            AccountCreationScreen accountCreate = new AccountCreationScreen(this.screen, this, this.profileScreen);
            accountCreate.draw();
        });
        deleteBtn.addActionListener(e -> {
            System.out.println(accountTable.getSelectedRow());
            if (accountTable.getSelectedRow() > -1) {
                String index = (String) accountTable.getValueAt(accountTable.getSelectedRow(), 3);
                Account accountToDelete = new Account(index);
                try {
                    accountToDelete.deleteAccount();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                this.accountManagement.setVisible(false);
                this.profileScreen.draw();
                JOptionPane.showMessageDialog(null, "Successfully deleted account: " + index);
            } else {
                JOptionPane.showMessageDialog(null, "Please select an Account to delete");
            }
        });

        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");

            stmt = con.createStatement();
            Account ac = new Account();
            ResultSet res = stmt
                    .executeQuery("SELECT title, forename, surname, username, email, clearance FROM account;");
            accountTable = new JTable(TableModel.buildTableModel(ac.getAcctList()));
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(accountTable);

            tablePanel.add(scrollPane);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stmt != null) {
            }
            stmt.close();
        }

        this.accountManagement.setVisible(true);
        screen.frame.add(this.accountManagement);
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
        tablePanel = new JPanel();

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

        // ======== tablePanel ========
        {
            tablePanel.setLayout(new BorderLayout());
        }
        add(tablePanel);
        tablePanel.setBounds(177, 125, 645, 290);

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
    private JPanel tablePanel;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    }
}
