package university.UI;

import java.awt.*;
import javax.swing.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.SwingConstants;

import university.ScreenManager;
import university.TableModel;
import university.Account;
import university.Module;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class ModuleManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel moduleScreen;
    private ScreenManager screen;
    private TeachingManagementScreen teachingScreen;
    private Account account;
    private JTable moduleTable;
    private Connection con = null;
    private Statement stmt = null;

    ModuleManagementScreen(ScreenManager scr, TeachingManagementScreen teach) {
        this.initComponents();
        this.initListeners();
        this.screen = scr;
        this.teachingScreen = teach;
    }

    public TeachingManagementScreen getTeachingScreen() {
    	return teachingScreen;
    }

    private void initListeners() {
        backToTeachingBtn.addActionListener(e -> {
            this.moduleScreen.setVisible(false);
            try {
                this.teachingScreen.draw();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        createBtn.addActionListener(e -> {
            this.moduleScreen.setVisible(false);
            ModuleCreationScreen moduleCreate = new ModuleCreationScreen(this.screen, this);
            try {
                moduleCreate.draw();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        deleteBtn.addActionListener(e -> {
            if (moduleTable.getSelectedRow() > -1) {
                String code = (String) moduleTable.getValueAt(moduleTable.getSelectedRow(), 1);
                String name = (String) moduleTable.getValueAt(moduleTable.getSelectedRow(), 0);
                Module modToDelete = new Module(code);

                JLabel label_login = new JLabel("If you wish to continue please insert your account details.");
                JLabel labem_email = new JLabel("Email:");
                JTextField email = new JTextField();

                JLabel label_password = new JLabel("Password:");
                JPasswordField password = new JPasswordField();

                Object[] array = { label_login, labem_email, email, label_password, password };

                int res = JOptionPane.showConfirmDialog(null, array, "Login", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (res == JOptionPane.OK_OPTION) {
                    try {
                        if (Account.delVerification(email.getText(), password.getText(), 3)) {
                            modToDelete.deleteModule();
                            this.moduleScreen.setVisible(false);
                            ModuleManagementScreen newModScree = new ModuleManagementScreen(this.screen,
                                    this.teachingScreen);
                            newModScree.draw();
                            JOptionPane.showMessageDialog(null, "Successfully deleted module: " + name);
                        } else
                            JOptionPane.showMessageDialog(null, "Please insert the correct account details");

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a module to delete");
            }
        });
    }

    public void draw() throws SQLException {
        this.moduleScreen = new JPanel();
        this.moduleScreen.setBackground(new Color(70, 70, 70));

        this.moduleScreen.add(backToTeachingBtn);
        this.moduleScreen.add(moduleManagementTxt);
        this.moduleScreen.add(createBtn);
        this.moduleScreen.add(deleteBtn);
        this.moduleScreen.add(tablePanel);

        this.tablePanel.setLayout(new BorderLayout());

        this.moduleScreen.setLayout(null);

        moduleTable = new JTable(TableModel.buildTableModel(Module.getModList()));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(moduleTable);

        tablePanel.add(scrollPane);

        screen.frame.add(this.moduleScreen);
    }

    private void initComponents() {
        backToTeachingBtn = new JButton();
        moduleManagementTxt = new JLabel();
        deleteBtn = new JButton();
        createBtn = new JButton();
        tablePanel = new JPanel();

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
        tablePanel.setBounds(177, 100, 645, 290);

        // ---- backToTeachingBtn ----
        backToTeachingBtn.setText("Back");
        add(backToTeachingBtn);
        backToTeachingBtn.setBounds(414, 500, 170, 50);

        // ---- moduleManagementTxt ----
        moduleManagementTxt.setText("Module Management");
        moduleManagementTxt
                .setFont(moduleManagementTxt.getFont().deriveFont(moduleManagementTxt.getFont().getSize() + 10f));
        moduleManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        moduleManagementTxt.setForeground(Color.white);
        add(moduleManagementTxt);
        moduleManagementTxt.setBounds(347, 35, 305, 31);

        // ---- deleteBtn ----
        deleteBtn.setText("Delete Module");
        add(deleteBtn);
        deleteBtn.setBounds(415, 465, 170, 30);

        // ---- createBtn ----
        createBtn.setText("Create Module");
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
    }

    private JButton backToTeachingBtn;
    private JLabel moduleManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    private JPanel tablePanel;

    
    public void actionPerformed(ActionEvent e) {
        
    }

}
