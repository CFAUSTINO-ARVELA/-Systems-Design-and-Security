package university.UI;

import java.awt.*;
import java.sql.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import university.UI.ProfileScreen;
import university.ScreenManager;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class TeachingManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel teachingManagement;
    private ScreenManager screen;
    private ProfileScreen profileScreen;

    TeachingManagementScreen(ScreenManager scr, ProfileScreen prof) {
        this.initComponents();
        this.initListeners();
        this.profileScreen = prof;
        this.screen = scr;
    }

    private void initListeners() {
        backToProfileBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            this.profileScreen.draw();
        });
        degreeBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            DegreeManagementScreen degreeScreen = new DegreeManagementScreen(this.screen, this);
            try {
				degreeScreen.draw();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        departmentBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            DepartmentManagementScreen departmentScreen = new DepartmentManagementScreen(this.screen, this);
            try {
				departmentScreen.draw();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        moduleBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            ModuleManagementScreen moduleScreen = new ModuleManagementScreen(this.screen, this);
            try {
				moduleScreen.draw();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        moduleAssignmentBtn.addActionListener(e -> {
            this.teachingManagement.setVisible(false);
            ModuleAssignmentScreen moduleAssScreen = new ModuleAssignmentScreen(this.screen, this);
            try {
                moduleAssScreen.draw();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
				e1.printStackTrace();
            }
        });
    }

    public void draw() throws SQLException{
        this.teachingManagement = new JPanel();

        welcomeTxt.setForeground(new Color(255, 255, 255));

        teachingManagement.setForeground(new Color(255,255,255));
        this.teachingManagement.add(welcomeTxt);
        this.teachingManagement.add(backToProfileBtn);
        this.teachingManagement.add(titleTxt);
        this.teachingManagement.add(degreeBtn);
        this.teachingManagement.add(departmentBtn);
        this.teachingManagement.add(moduleBtn);
        this.teachingManagement.add(moduleAssignmentBtn);

        this.teachingManagement.setBackground(new Color(70, 70, 70));
        this.teachingManagement.setLayout(null);

        screen.frame.add(this.teachingManagement);
    }


    private void initComponents() {
        welcomeTxt = new JLabel();
        backToProfileBtn = new JButton();
        titleTxt = new JLabel();
        degreeBtn = new JButton();
        departmentBtn = new JButton();
        moduleBtn = new JButton();
        moduleAssignmentBtn = new JButton();

        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- welcomeTxt ----
        welcomeTxt.setText("Please make a selection below");
        welcomeTxt.setFont(welcomeTxt.getFont().deriveFont(welcomeTxt.getFont().getSize() + 6f));
        welcomeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeTxt);
        welcomeTxt.setBounds(347, 95, 305, 50);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- titleTxt ----
        titleTxt.setText("Teaching Management");
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 10f));
        titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        titleTxt.setForeground(Color.white);
        add(titleTxt);
        titleTxt.setBounds(347, 35, 305, 31);

        //---- degreeBtn ----
        degreeBtn.setText("Degree Management");
        add(degreeBtn);
        degreeBtn.setBounds(190, 180, 195, 50);

        //---- departmentBtn ----
        departmentBtn.setText("Department Management");
        add(departmentBtn);
        departmentBtn.setBounds(402, 180, 195, 50);

        //---- moduleBtn ----
        moduleBtn.setText("Module Management");
        add(moduleBtn);
        moduleBtn.setBounds(615, 180, 195, 50);

        //---- moduleAssignmentBtn ----
        moduleAssignmentBtn.setText("Module Assignment");
        add(moduleAssignmentBtn);
        moduleAssignmentBtn.setBounds(402, 250, 195, 50);

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

    private JLabel welcomeTxt;
    private JButton backToProfileBtn;
    private JLabel titleTxt;
    private JButton degreeBtn;
    private JButton departmentBtn;
    private JButton moduleBtn;
    private JButton moduleAssignmentBtn;

	
	public void actionPerformed(ActionEvent e) {

		
	}
}
