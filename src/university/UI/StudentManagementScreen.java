package university.UI;

import java.awt.*;
import javax.swing.*;

import university.UI.ProfileScreen;
import university.Account;
import university.Module;
import university.ScreenManager;
import university.Student;
import university.StudentStatus;
import university.TableModel;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class StudentManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel studentManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private boolean canEdit;
    private boolean isTeacher;
    private JTable studentTable;

    StudentManagementScreen(ScreenManager scr, ProfileScreen prof, boolean edit, boolean teacher) {
        this.initComponents();
        this.initListeners();
        this.canEdit = edit;
        this.screen = scr;
        this.profileScreen = prof;
        this.isTeacher = teacher;
    }

    public void initListeners() {
        backToProfileBtn.addActionListener(e -> {
            studentTable.clearSelection();
            this.studentManagement.setVisible(false);
            this.profileScreen.draw();
        });
        createBtn.addActionListener(e -> {
            studentTable.clearSelection();
            this.studentManagement.setVisible(false);
            StudentCreationScreen studentCreate = new StudentCreationScreen(this.screen, this, this.profileScreen);
            studentCreate.draw();
        });
        deleteBtn.addActionListener(e -> {
            if (studentTable.getSelectedRow() > -1) {
                int index = Integer.parseInt((String) studentTable.getValueAt(studentTable.getSelectedRow(), 0));
                String username = (String) studentTable.getValueAt(studentTable.getSelectedRow(), 3);
                Student stuToDelete = new Student(index);
                
                
                
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
	                		stuToDelete.deleteStudent();
	                		
	                	}else
	                		JOptionPane.showMessageDialog(null, "Please insert the correct account details");
                    
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	                this.studentManagement.setVisible(false);
	                this.profileScreen.draw();
	                JOptionPane.showMessageDialog(null, "Successfully deleted student: " + username);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a Student to delete");
            }
            studentTable.clearSelection();
        });
        statusBtn.addActionListener(e -> {
            if (studentTable.getSelectedRow() > -1) {
                String username = (String) studentTable.getValueAt(studentTable.getSelectedRow(), 3);
                try {
                    Student student = Student.getStudent(username);
                    StudentStatusScreen statusScreen = new StudentStatusScreen(this.screen, this.profileScreen,
                            student);
                    statusScreen.draw();
                    this.studentManagement.setVisible(false);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a Student to view");
            }
        });
        moduleBtn.addActionListener(e -> {
            if (studentTable.getSelectedRow() > -1) {
                int index = Integer.parseInt((String) studentTable.getValueAt(studentTable.getSelectedRow(), 0));
                Student student;
                try {
                    student = Student.getStudentReg(index);
                    if (student.getStudentStatus().isGraduated()) {
                        JOptionPane.showMessageDialog(null, "This student has graduated or failed");
                    } else {
                        ModuleChoiceScreen choiceScreen = new ModuleChoiceScreen(this.screen, this, student);
                        choiceScreen.draw();
                        this.studentManagement.setVisible(false);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                this.studentManagement.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a Student to complete add/drop");
            }
            studentTable.clearSelection();
        });
        markingBtn.addActionListener(e -> {
            if (studentTable.getSelectedRow() > -1) {
                String username = (String) studentTable.getValueAt(studentTable.getSelectedRow(), 3);
                MarkingScreen markingScr;
                try {
                    Student student = Student.getStudent(username);
                    if (student.getStudentStatus().isGraduated()) {
                        JOptionPane.showMessageDialog(null, "This student has graduated");
                    } else {
                        markingScr = new MarkingScreen(this.screen, this, student);
                        markingScr.draw();
                        this.studentManagement.setVisible(false);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select a Student to mark");
            }
            studentTable.clearSelection();
        });
    }

    public void draw() throws Exception {
        this.studentManagement = new JPanel();
        this.studentManagement.setBackground(new Color(70, 70, 70));

        this.studentManagement.add(backToProfileBtn);
        this.studentManagement.add(studentManagementTxt);

        if (this.canEdit) {
            this.studentManagement.add(createBtn);
            this.studentManagement.add(deleteBtn);
            this.studentManagement.add(moduleBtn);
        }

        if (this.isTeacher) {
            this.studentManagement.add(statusBtn);
        }

        this.studentManagement.add(tablePanel);
        this.studentManagement.add(markingBtn);
        this.tablePanel.setLayout(new BorderLayout());

        this.studentManagement.setLayout(null);

        studentTable = new JTable(TableModel.buildTableModel(Student.getStutList()));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(studentTable);

        tablePanel.add(scrollPane);

        this.studentManagement.setVisible(true);
        screen.frame.add(this.studentManagement);
    }

    public void returnFromManagement(String status) throws Exception {
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
        markingBtn = new JButton();
        statusBtn = new JButton();

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

        // ---- studentManagementTxt ----
        studentManagementTxt.setText("Student Management");
        studentManagementTxt
                .setFont(studentManagementTxt.getFont().deriveFont(studentManagementTxt.getFont().getSize() + 10f));
        studentManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        studentManagementTxt.setForeground(Color.white);
        add(studentManagementTxt);
        studentManagementTxt.setBounds(347, 35, 305, 31);

        // ---- deleteBtn ----
        deleteBtn.setText("Delete Student");
        add(deleteBtn);
        deleteBtn.setBounds(415, 470, 170, 30);

        // ---- createBtn ----
        createBtn.setText("Create Student");
        add(createBtn);
        createBtn.setBounds(415, 440, 170, 30);

        // ======== tablePanel ========
        {
            tablePanel.setLayout(new BorderLayout());
        }
        add(tablePanel);
        tablePanel.setBounds(177, 100, 645, 270);

        // ---- moduleBtn ----
        moduleBtn.setText("Registration/Add drop");
        add(moduleBtn);
        moduleBtn.setBounds(415, 410, 170, 30);

        statusBtn.setText("Student Status");
        statusBtn.setBounds(415, 410, 170, 30);

        // ---- markingBtn ----
        markingBtn.setText("Student grades");
        add(markingBtn);
        markingBtn.setBounds(415, 375, 170, 30);

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
    private JLabel studentManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    private JPanel tablePanel;
    private JButton moduleBtn;
    private JButton markingBtn;
    private JButton statusBtn;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
