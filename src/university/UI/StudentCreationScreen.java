package university.UI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import university.UI.ProfileScreen;
import university.Account;
import university.Student;
import university.ValidCheck;
import university.Degree;
import university.ScreenManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

class StudentCreationScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel studentCreation;
    private ScreenManager screen;
    private ProfileScreen profileScreen;
    private StudentManagementScreen studentManagement;
    private ArrayList<String> degrees = new ArrayList<String>();
    private Degree d = null;

    private Connection con = null;
    private Statement stmt = null;

    StudentCreationScreen(ScreenManager scr, StudentManagementScreen studentManage, ProfileScreen prof) {
        this.initComponents();
        this.initListeners();
        this.screen = scr;
        this.studentManagement = studentManage;
        this.profileScreen = prof;
    }

    private void initListeners() {
        backToProfileBtn.addActionListener(e -> {
            this.studentCreation.setVisible(false);
            try {
                this.profileScreen.draw();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        submitBtn.addActionListener(e -> {

            if (ValidCheck.input(titleInput) && ValidCheck.input(forenameInput) && ValidCheck.input(surnameInput)) {

                this.studentCreation.setVisible(false);
                Account ac = new Account(titleInput.getText(), forenameInput.getText(), surnameInput.getText(),
                        "password", "Student");
                try {
                    Student stu;
                    d = Degree.getDegree(degreeInput.getSelectedItem().toString());
                    try {
                        stu = new Student(d, tutorInput.getText(), ac.createAccount());
                        stu.createStudent();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    this.profileScreen.draw();
                    JOptionPane.showMessageDialog(null,
                            "Successfully created Student: " + ac.getEmail() + ". Password: " + ac.getPassword()
                                    + "\nThey've been added to degree " + d.getName()
                                    + " on Level 1 \nCore modules have been added");
                    ac.securePassword();
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "SQL error, please try again");
                }
                // }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please ensure no fields are blank and no symbols have been entered");
            }
        });
    }

    public void draw() {
        this.studentCreation = new JPanel();
        this.studentCreation.setBackground(new Color(70, 70, 70));
        this.studentCreation.setLayout(null);

        this.studentCreation.add(submitBtn);
        this.studentCreation.add(promptTxt);
        this.studentCreation.add(welcomeTxt);
        this.studentCreation.add(titleTxt);
        this.studentCreation.add(forenameTxt);
        this.studentCreation.add(surnameTxt);
        this.studentCreation.add(titleInput);
        this.studentCreation.add(forenameInput);
        this.studentCreation.add(surnameInput);
        this.studentCreation.add(backToProfileBtn);
        this.studentCreation.add(studentManagementTxt);
        this.studentCreation.add(degreeTxt);
        this.studentCreation.add(degreeInput);
        this.studentCreation.add(submitBtn);
        this.studentCreation.add(degreeTxt);
        this.studentCreation.add(tutorTxt);
        this.studentCreation.add(tutorInput);
        this.studentCreation.add(degreeInput);

        screen.frame.add(this.studentCreation);
    }

    private void initComponents() {
        welcomeTxt = new JLabel();
        promptTxt = new JLabel();
        titleTxt = new JLabel();
        forenameTxt = new JLabel();
        surnameTxt = new JLabel();
        titleInput = new JTextField();
        forenameInput = new JTextField();
        surnameInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        studentManagementTxt = new JLabel();
        degreeTxt = new JLabel();
        tutorTxt = new JLabel();
        tutorInput = new JTextField();
        try {
            degrees = Degree.getAllDegreeCodes();
            degreeInput = new JComboBox((degrees.toArray()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

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

        // ---- welcomeTxt ----
        welcomeTxt.setText("Student Account");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 12f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeTxt.setForeground(Color.white);
        add(welcomeTxt);
        welcomeTxt.setBounds(389, 90, 220, welcomeTxt.getPreferredSize().height);

        // ---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        // ---- titleTxt ----
        titleTxt.setText("Title");
        titleTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 3f));
        titleTxt.setForeground(Color.white);
        add(titleTxt);
        titleTxt.setBounds(185, 185, 185, titleTxt.getPreferredSize().height);

        // ---- forenameTxt ----
        forenameTxt.setText("Forename");
        forenameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        forenameTxt.setFont(forenameTxt.getFont().deriveFont(forenameTxt.getFont().getSize() + 3f));
        forenameTxt.setForeground(Color.white);
        add(forenameTxt);
        forenameTxt.setBounds(230, 227, 140, 16);

        // ---- surnameTxt ----
        surnameTxt.setText("Surname");
        surnameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        surnameTxt.setFont(surnameTxt.getFont().deriveFont(surnameTxt.getFont().getSize() + 3f));
        surnameTxt.setForeground(Color.white);
        add(surnameTxt);
        surnameTxt.setBounds(155, 265, 215, 16);
        add(titleInput);
        titleInput.setBounds(382, 180, 235, 30);
        add(forenameInput);
        forenameInput.setBounds(382, 220, 235, 30);
        add(surnameInput);
        surnameInput.setBounds(382, 260, 235, 30);

        // ---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 465, 135, submitBtn.getPreferredSize().height);

        // ---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        // ---- studentManagementTxt ----
        studentManagementTxt.setText("Account Management");
        studentManagementTxt
                .setFont(studentManagementTxt.getFont().deriveFont(studentManagementTxt.getFont().getSize() + 10f));
        studentManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        studentManagementTxt.setForeground(Color.white);
        add(studentManagementTxt);
        studentManagementTxt.setBounds(347, 35, 305, 31);

        // ---- degreeTxt ----
        degreeTxt.setText("Degree");
        degreeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        degreeTxt.setFont(degreeTxt.getFont().deriveFont(degreeTxt.getFont().getSize() + 3f));
        degreeTxt.setForeground(Color.white);
        add(degreeTxt);
        degreeTxt.setBounds(155, 305, 215, 16);

        // ---- tutorTxt ----
        tutorTxt.setText("Tutor");
        tutorTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        tutorTxt.setFont(tutorTxt.getFont().deriveFont(tutorTxt.getFont().getSize() + 3f));
        tutorTxt.setForeground(Color.white);
        add(tutorTxt);
        tutorTxt.setBounds(155, 345, 215, 16);
        add(tutorInput);
        tutorInput.setBounds(382, 340, 235, 30);
        add(degreeInput);
        degreeInput.setBounds(382, 300, 235, degreeInput.getPreferredSize().height);

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

    private JLabel welcomeTxt;
    private JLabel promptTxt;
    private JLabel titleTxt;
    private JLabel forenameTxt;
    private JLabel surnameTxt;
    private JTextField titleInput;
    private JTextField forenameInput;
    private JTextField surnameInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel studentManagementTxt;
    private JLabel degreeTxt;
    private JLabel tutorTxt;
    private JTextField tutorInput;
    private JComboBox degreeInput;

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
