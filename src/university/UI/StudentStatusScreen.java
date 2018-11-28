package university.UI;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import university.DegreeResult;
import university.PeriodResult;
import university.ScreenManager;
import university.Student;
import university.StudentStatus;
import university.TableModel;

public class StudentStatusScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel studentStatusScreen;
    private ProfileScreen profileScreen;
    private ScreenManager screen;
    private Student student;
    private StudentStatus status;
    private JTable resultTable;

    StudentStatusScreen(ScreenManager screen, ProfileScreen prof, Student stu) {
        initComponents();
        this.profileScreen = prof;
        this.screen = screen;
        this.student = stu;
    }

    public void initListeners() {
        backToProfileBtn.addActionListener(e -> {
            this.profileScreen.draw();
            this.studentStatusScreen.setVisible(false);
        });
    }

    public void draw() {
        this.studentStatusScreen = new JPanel();

        this.studentStatusScreen.setBackground(new Color(70, 70, 70));

        this.studentStatusScreen.add(nameTxt);
        this.studentStatusScreen.add(gradeTxt);
        this.studentStatusScreen.add(titleTxt);
        this.studentStatusScreen.add(startDateTxt);
        this.studentStatusScreen.add(endDateTxt);
        this.studentStatusScreen.add(levelStudiesTxt);
        this.studentStatusScreen.add(periodStudiesTxt);
        this.studentStatusScreen.add(backToProfileBtn);

        titleTxt.setForeground(new Color(255, 255, 255));

        try {
            status = this.student.getStudentStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.addDetails();
        this.studentStatusScreen.setLayout(null);
        screen.frame.add(this.studentStatusScreen);
    }

    public void addDetails() {

        ArrayList<PeriodResult> results;
        try {
            results = this.student.getPrevResults();
            DefaultTableModel model = new DefaultTableModel();
            resultTable = new JTable(model);

            model.addColumn("Period");
            model.addColumn("Level");
            model.addColumn("Grade");

            for (PeriodResult result : results) {
                model.addRow(new Object[] { result.getPeriod(), result.getLevel(), result.getGrade() });
            }

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(resultTable);

            gradePanel.setLayout(new BorderLayout());

            gradePanel.add(scrollPane);
            this.studentStatusScreen.add(gradePanel);

            if (this.status.isGraduated()) {
                gradeTxt.setText("You have graduated with: "
                        + DegreeResult.getDegreeResult(this.student.getRegistrationNumber()).getResult());
            } else {
                gradeTxt.setText("Current grade: ");
            }

            nameTxt.setText("Student ID: " + this.student.getRegistrationNumber());
            startDateTxt.setText("Start Date: " + this.status.getStartDate());
            endDateTxt.setText("End Date: " + this.status.getEndDate());
            levelStudiesTxt.setText("Level Studies: " + this.status.getLevel());
            periodStudiesTxt.setText("Period Studies: " + this.status.getPeriod());

            nameTxt.setForeground(new Color(255, 255, 255));
            gradeTxt.setForeground(new Color(255, 255, 255));
            startDateTxt.setForeground(new Color(255, 255, 255));
            endDateTxt.setForeground(new Color(255, 255, 255));
            levelStudiesTxt.setForeground(new Color(255, 255, 255));
            periodStudiesTxt.setForeground(new Color(255, 255, 255));

            this.studentStatusScreen.add(gradeTxt);
            this.studentStatusScreen.add(startDateTxt);
            this.studentStatusScreen.add(endDateTxt);
            this.studentStatusScreen.add(levelStudiesTxt);
            this.studentStatusScreen.add(periodStudiesTxt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        nameTxt = new JLabel();
        startDateTxt = new JLabel();
        levelStudiesTxt = new JLabel();
        titleTxt = new JLabel();
        backToProfileBtn = new JButton();
        periodStudiesTxt = new JLabel();
        endDateTxt = new JLabel();
        gradePanel = new JPanel();
        gradeTxt = new JLabel();

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

        nameTxt.setText("Name:");
        add(nameTxt);
        nameTxt.setBounds(180, 145, 360, 45);

        // ---- startDateTxt ----
        startDateTxt.setText("End Date: ");
        add(startDateTxt);
        startDateTxt.setBounds(180, 190, 360, 45);

        // ---- levelStudiesTxt ----
        levelStudiesTxt.setText("Level Of Studies:");
        add(levelStudiesTxt);
        levelStudiesTxt.setBounds(180, 280, 360, 45);

        // ---- titleTxt ----
        titleTxt.setText("Welcome To Student Status");
        titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 12f));
        add(titleTxt);
        titleTxt.setBounds(233, 45, 530, 45);

        // ---- backToProfileBtn ----
        backToProfileBtn.setText("Back To Profile");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        // ---- periodStudiesTxt ----
        periodStudiesTxt.setText("Period Of Studies:");
        add(periodStudiesTxt);
        periodStudiesTxt.setBounds(180, 330, 360, 45);

        // ---- endDateTxt ----
        endDateTxt.setText("Start Date: ");
        add(endDateTxt);
        endDateTxt.setBounds(180, 235, 360, 45);

        // ======== gradePanel ========
        {
            gradePanel.setLayout(null);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < gradePanel.getComponentCount(); i++) {
                    Rectangle bounds = gradePanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = gradePanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                gradePanel.setMinimumSize(preferredSize);
                gradePanel.setPreferredSize(preferredSize);
            }
        }
        add(gradePanel);
        gradePanel.setBounds(550, 145, 365, 230);

        // ---- gradeTxt ----
        gradeTxt.setText("Grade:");
        add(gradeTxt);
        gradeTxt.setBounds(180, 380, 360, 45);

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

    private JLabel nameTxt;
    private JLabel startDateTxt;
    private JLabel levelStudiesTxt;
    private JLabel titleTxt;
    private JButton backToProfileBtn;
    private JLabel periodStudiesTxt;
    private JLabel endDateTxt;
    private JPanel gradePanel;
    private JLabel gradeTxt;
}
