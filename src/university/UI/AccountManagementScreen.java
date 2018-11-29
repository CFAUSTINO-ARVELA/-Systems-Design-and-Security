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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
        this.initListeners();
        this.profileScreen = prof;
        this.screen = scr;
        this.account = acc;
    }

    private void initListeners() {
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
            if (accountTable.getSelectedRow() > -1) {
                String index = (String) accountTable.getValueAt(accountTable.getSelectedRow(), 0);
                Account accountToDelete = new Account(index);
                JLabel label_login = new JLabel("If you wish to continue please insert your account details.");
                JLabel labem_email = new JLabel("Email:");
                JTextField email = new JTextField();

                JLabel label_password = new JLabel("Password:");
                JPasswordField password = new JPasswordField();

                Object[] array = {label_login, labem_email, email, label_password, password };

                int res = JOptionPane.showConfirmDialog(null, array, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (res == JOptionPane.OK_OPTION) {
	                try {
	                	if(Account.delVerification(email.getText(), password.getText(), 3)) {
                            accountToDelete.deleteAccount();
                            this.accountManagement.setVisible(false);
                            this.profileScreen.draw();
                            JOptionPane.showMessageDialog(null, "Successfully deleted account: " + index);
	                		
	                	}else
	                		JOptionPane.showMessageDialog(null, "Please insert the correct account details");
                    
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select an Account to delete");
            }
        });
    }

    public void drawTable() throws SQLException {
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
    }

    public void draw()  {
        this.accountManagement = new JPanel();
        this.accountManagement.setBackground(new Color(70, 70, 70));

        this.accountManagement.add(backToProfileBtn);
        this.accountManagement.add(accountManagementTxt);
        this.accountManagement.add(createBtn);
        this.accountManagement.add(deleteBtn);
        this.accountManagement.add(promptTxt);
        this.accountManagement.setLayout(null);

        this.accountManagement.add(tablePanel);

        this.accountManagement.setVisible(true);
        screen.frame.add(this.accountManagement);

        try {
            this.drawTable();
        } catch (SQLException e) {
            e.printStackTrace();
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
        promptTxt = new JLabel();

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

        promptTxt.setBounds(347, 70, 305, 31);
        promptTxt.setFont(promptTxt.getFont().deriveFont(promptTxt.getFont().getSize() + 0f));
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        promptTxt.setText("Select an account from the list to delete");


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
    private JLabel promptTxt;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    }
}
