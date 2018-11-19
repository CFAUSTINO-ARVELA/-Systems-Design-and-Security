package university.UI;

import university.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.*;
/*
 * Created by JFormDesigner on Thu Nov 15 14:23:19 GMT 2018
 */

/**
 * @author Katie
 */
public class StudentStatus extends JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Account loggedInAccount;
    private JPanel studentStatusScreen;
    private ProfileScreen profileScreen;
    private ScreenManager screen;

    private String username;
    private Date startDate;
    private Date endDate;
    private int levelStudies;
    private Semester periodStudies;
    private int grade;

    StudentStatus(Account loggedInAcc, ScreenManager screen, ProfileScreen prof) {
        initComponents();
        this.loggedInAccount = loggedInAcc;
        this.profileScreen = prof;
        this.screen = screen;
    }

    public void draw() {
        this.studentStatusScreen = new JPanel();

        this.studentStatusScreen.setBackground(new Color(70, 70,70));

        this.studentStatusScreen.add(titleTxt);
        this.studentStatusScreen.add(backToProfileBtn);

        backToProfileBtn.addActionListener(e -> {
            this.profileScreen.draw();
            this.studentStatusScreen.setVisible(false);

        });

        this.studentStatusScreen.setLayout(null);

        switch (loggedInAccount.getClearance()) {
        case STUDENT:
            this.setStudentStatus(loggedInAccount.getUsername());
            this.drawForm();
            break;
        case TEACHER:
            this.drawSearch();
            break;
        }

        screen.frame.add(this.studentStatusScreen);
    }

    public void drawForm() {
        nameTxt.setText("Name: " + this.username);
        startDateTxt.setText("Start Date: " + this.startDate);
        endDateTxt.setText("End Date: " + this.endDate);
        levelStudiesTxt.setText("Level Studies: " + this.levelStudies);
        periodStudiesTxt.setText("Period Studies: " + this.periodStudies);

        titleTxt.setForeground(new Color(255,255,255));
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

    public void drawSearch() {
        JTextField searchbox = new JTextField();
        JButton searchBtn = new JButton("Search");

        searchBtn.addActionListener(e -> {
            this.setStudentStatus(searchbox.getText());
        });
    }

    public void setStudentStatus(String user) {
        // query the student status table/cols here and set all the data

        this.username = "aca16klw";
        // ...

    }

    private void updateLevelStudies() {

    }

    private void updatePeriodStudies() {

    }

    private int getGradeMean() {
        return 0;
    }

    // @Override
    // public void actionPerformed(ActionEvent arg0) {
    //     // TODO Auto-generated method stub

    // }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        nameTxt = new JLabel();
        startDateTxt = new JLabel();
        levelStudiesTxt = new JLabel();
        titleTxt = new JLabel();
        backToProfileBtn = new JButton();
        periodStudiesTxt = new JLabel();
        endDateTxt = new JLabel();

        //======== this ========

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
        backToProfileBtn.setBounds(435, 515, backToProfileBtn.getPreferredSize().width, 45);

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
        // JFormDesigner - End of component initialization //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    private JLabel nameTxt;
    private JLabel startDateTxt;
    private JLabel levelStudiesTxt;
    private JLabel titleTxt;
    private JButton backToProfileBtn;
    private JLabel periodStudiesTxt;
    private JLabel endDateTxt;
    // JFormDesigner - End of variables declaration //GEN-END:variables

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
