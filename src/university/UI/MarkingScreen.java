package university.UI;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import university.Module;
import university.ModuleChoice;
import university.ModuleGrades;
import university.ScreenManager;
import university.Student;
import university.StudentStatus;

public class MarkingScreen extends JPanel {

	private JPanel markingScreen;
	private ScreenManager screen;
	private Student student;
	private StudentManagementScreen studentManagement;
	private int rows;
	private JTable table;

	public MarkingScreen(ScreenManager scr, StudentManagementScreen stuScreen, Student stu) {
		this.screen = scr;
		this.studentManagement = stuScreen;
		this.student = stu;
		initComponents();
	}

	public void draw() {

		this.markingScreen = new JPanel();
		this.markingScreen.setLayout(null);
		this.markingScreen.setBackground(new Color(70, 70, 70));

		this.markingScreen.add(titleTxt);
		this.markingScreen.add(markingPanel);
		this.markingScreen.add(backToProfileBtn);
		this.markingScreen.add(submitBtn);

		this.markingPanel.setLayout(new BorderLayout());

		rows = 0;
		this.addModules();

		screen.frame.add(this.markingScreen);

		backToProfileBtn.addActionListener(e -> {
			try {
				this.markingScreen.setVisible(false);
				this.studentManagement.draw();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		submitBtn.addActionListener(e -> {
			table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
			ArrayList<ModuleGrades> allGrades = new ArrayList<>();
			if (this.checkEntered()) {
				System.out.println("None empty");
				for (int i = 0; i < rows; i++) {
					try {
						String code = (String)table.getValueAt(i, 1);
						ModuleGrades grade = new ModuleGrades(Module.getModule(code), Integer.parseInt((String)table.getValueAt(i, 2)));
						allGrades.add(grade);
						System.out.println(this.student.progress(student, allGrades));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("Missed a grade");
			}
		});
	}

	private boolean checkEntered() {
		for (int i = 0; i < rows; i++) { 
			System.out.println(i);
			if (table.getValueAt(i, 2) == null) {
				System.out.println(table.getValueAt(i, 2));
				return false;
			}
		}
		return true;
		
	}

	private void addModules() {

		try {
			StudentStatus stuStatus = this.student.getStudentStatus();

			DefaultTableModel model = new DefaultTableModel();
			table = new JTable(model);

			// Create a couple of columns
			model.addColumn("Name");
			model.addColumn("Code");
			model.addColumn("Grade");
			model.addColumn("Resit Grade");
			for (ModuleChoice module : stuStatus.getCurrentModules()) {
				System.out.println(module.getModuleCode());
				String name = Module.getModule(module.getModuleCode()).getName();
				model.addRow(new Object[] { name, module.getModuleCode(), null, null });
				rows++;
			}

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportView(table);

			markingPanel.add(scrollPane);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {
		titleTxt = new JLabel();
		markingPanel = new JPanel();
		backToProfileBtn = new JButton();
		submitBtn = new JButton();

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

		// ---- titleTxt ----
		titleTxt.setText("Marking");
		titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
		titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 10f));
		titleTxt.setForeground(Color.white);
		add(titleTxt);
		titleTxt.setBounds(430, 25, 140, 55);

		// ======== markingPanel ========
		{
			markingPanel.setLayout(null);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for (int i = 0; i < markingPanel.getComponentCount(); i++) {
					Rectangle bounds = markingPanel.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = markingPanel.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				markingPanel.setMinimumSize(preferredSize);
				markingPanel.setPreferredSize(preferredSize);
			}
		}
		add(markingPanel);
		markingPanel.setBounds(195, 95, 610, 340);

		// ---- backToProfileBtn ----
		backToProfileBtn.setText("Back To Students");
		add(backToProfileBtn);
		backToProfileBtn.setBounds(414, 500, 170, 50);

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

		submitBtn.setText("Submit grades");
        add(submitBtn);
        submitBtn.setBounds(415, 465, 170, 30);

		// JFormDesigner - End of component initialization //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Katie
	private JLabel titleTxt;
	private JPanel markingPanel;
	private JButton backToProfileBtn;
	private JButton submitBtn;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
