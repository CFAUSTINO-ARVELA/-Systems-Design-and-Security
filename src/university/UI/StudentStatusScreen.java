package university.UI;

import university.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.*;

public class StudentStatusScreen extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
    private Account loggedInAccount;
    private JPanel studentStatusScreen;
    private ProfileScreen profileScreen;
    private ScreenManager screen;
    private Student student;
    private StudentStatus status;

    StudentStatusScreen(Account loggedInAcc, ScreenManager screen, ProfileScreen prof, Student stu) {
        initComponents();
        this.loggedInAccount = loggedInAcc;
        this.profileScreen = prof;
        this.screen = screen;
        this.student = stu;
    }

    public void draw() {
        this.studentStatusScreen = new JPanel();

        this.studentStatusScreen.setBackground(new Color(70, 70,70));

        this.studentStatusScreen.add(titleTxt);
        this.studentStatusScreen.add(backToProfileBtn);
        
        titleTxt.setForeground(new Color(255,255,255));

        try {
            status = this.student.getStudentStatus();
        } catch (SQLException e) {
            e.printStackTrace();
		}

        backToProfileBtn.addActionListener(e -> {
            this.profileScreen.draw();
            this.studentStatusScreen.setVisible(false);
        });

        this.studentStatusScreen.setLayout(null);
        screen.frame.add(this.studentStatusScreen);
    }

    public void drawForm() {
        
        nameTxt.setText("Name: ");
        startDateTxt.setText("Start Date: " + this.status.getStartDate());
        endDateTxt.setText("End Date: " + this.status.getEndDate());
        levelStudiesTxt.setText("Level Studies: " + this.status.getLevel());
        periodStudiesTxt.setText("Period Studies: " + this.status.getPeriod());

        nameTxt.setForeground(new Color(255,255,255));
        startDateTxt.setForeground(new Color(255,255,255));
        endDateTxt.setForeground(new Color(255,255,255));
        levelStudiesTxt.setForeground(new Color(255,255,255));
        periodStudiesTxt.setForeground(new Color(255,255,255));

        this.studentStatusScreen.add(nameTxt);
        this.studentStatusScreen.add(startDateTxt);
        this.studentStatusScreen.add(endDateTxt);
        this.studentStatusScreen.add(levelStudiesTxt);
        this.studentStatusScreen.add(periodStudiesTxt);
    }

    private void initComponents() {
        nameTxt = new JLabel();
        startDateTxt = new JLabel();
        levelStudiesTxt = new JLabel();
        titleTxt = new JLabel();
        backToProfileBtn = new JButton();
        periodStudiesTxt = new JLabel();
        endDateTxt = new JLabel();

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- nameTxt ----
        nameTxt.setText("Name:");
        add(nameTxt);
        nameTxt.setBounds(365, 180, 280, 45);

        //---- startDateTxt ----
        startDateTxt.setText("End Date: ");
        add(startDateTxt);
        startDateTxt.setBounds(365, 240, 280, 45);

        //---- levelStudiesTxt ----
        levelStudiesTxt.setText("Level Of Studies:");
        add(levelStudiesTxt);
        levelStudiesTxt.setBounds(365, 355, 280, 45);

        //---- titleTxt ----
        titleTxt.setText("Welcome To Student Status");
        titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 12f));
        add(titleTxt);
        titleTxt.setBounds(233, 45, 530, 45);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back To Profile");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- periodStudiesTxt ----
        periodStudiesTxt.setText("Period Of Studies:");
        add(periodStudiesTxt);
        periodStudiesTxt.setBounds(365, 415, 280, 45);

        //---- endDateTxt ----
        endDateTxt.setText("Start Date: ");
        add(endDateTxt);
        endDateTxt.setBounds(365, 295, 280, 45);

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
    }

    private JLabel nameTxt;
    private JLabel startDateTxt;
    private JLabel levelStudiesTxt;
    private JLabel titleTxt;
    private JButton backToProfileBtn;
    private JLabel periodStudiesTxt;
    private JLabel endDateTxt;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
