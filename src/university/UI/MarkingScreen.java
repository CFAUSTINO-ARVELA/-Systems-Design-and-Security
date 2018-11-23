package university.UI;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import university.Module;
import university.ModuleChoice;
import university.ScreenManager;
import university.Student;
import university.StudentStatus;
import university.TableModel;

public class MarkingScreen extends JPanel {

	private JPanel markingScreen;
	private ScreenManager screen;
	private Student student;
	private StudentManagementScreen studentManagement;

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
		
		this.markingPanel.setLayout(new BorderLayout());

		this.addModules();

		screen.frame.add(this.markingScreen);
	}

	private void addModules() {

		try {
			StudentStatus stuStatus = this.student.getStudentStatus();
			
			DefaultTableModel model = new DefaultTableModel(); 
			JTable table = new JTable(model); 

			// Create a couple of columns 
			model.addColumn("Name"); 
			model.addColumn("Code"); 
			model.addColumn("Grade"); 
			model.addColumn("Resit Grade"); 
			
			for (ModuleChoice module : stuStatus.getCurrentModules()) {
				String name = Module.getModule(module.getModuleCode()).getName();
				model.addRow(new Object[]{name, module.getModuleCode(), null, null});
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
		// JFormDesigner - End of component initialization //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Katie
	private JLabel titleTxt;
	private JPanel markingPanel;
	private JButton backToProfileBtn;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
