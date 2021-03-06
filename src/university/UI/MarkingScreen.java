package university.UI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;

import university.Module;
import university.ModuleChoice;
import university.ModuleGrades;
import university.ScreenManager;
import university.Student;
import university.StudentStatus;
import university.ValidCheck;

public class MarkingScreen extends JPanel {

	private JPanel markingScreen;
	private ScreenManager screen;
	private Student student;
	private StudentManagementScreen studentManagement;
	private ProfileScreen profile;
	private int rows;
	private JTable table;

	public MarkingScreen(ScreenManager scr, StudentManagementScreen stuScreen, Student stu, ProfileScreen prof) {
		this.screen = scr;
		this.studentManagement = stuScreen;
		this.student = stu;
		this.profile = prof;
		initComponents();
		this.initListeners();
	}

	public void initListeners() {
		backToProfileBtn.addActionListener(e -> {
			try {
				this.markingScreen.setVisible(false);
				this.profile.draw();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		calculateBtn.addActionListener(e -> {
			if (table.getEditingRow() != -1) {
				table.getCellEditor().stopCellEditing();
			}

			try {
				if (this.student.getLevel() != "P") {
					ArrayList<ModuleGrades> allGrades = new ArrayList<>();
					boolean invalid = false;
					if (this.checkEntered()) {
						for (int i = 0; i < rows; i++) {
							try {
								String code = (String) table.getValueAt(i, 1);
								String firstGradeTxt = (String) table.getValueAt(i, 3);
								ModuleGrades grades = null;

								if (ValidCheck.grade(firstGradeTxt)) {
									int firstGrade = Integer.parseInt(firstGradeTxt);

									if (this.isResitGrade(i)) {

										String resitTxt = (String) table.getValueAt(i, 4);

										if (ValidCheck.grade(resitTxt)) {
											int resit = Integer.parseInt(resitTxt);
											grades = new ModuleGrades(Module.getModule(code), firstGrade, resit);
										} else {
											JOptionPane.showMessageDialog(null, "A grade you entered was invalid");
										}

									} else {
										grades = new ModuleGrades(Module.getModule(code), firstGrade);
									}

									allGrades.add(grades);
								} else {
									invalid = true;
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						try {
							if (!invalid) {
								JOptionPane.showMessageDialog(null, this.student.calculate(student, allGrades));
							} else {
								JOptionPane.showMessageDialog(null, "A grade you entered was invalid");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						System.out.println("Missed a grade");
					}
				} else {
					JOptionPane.showMessageDialog(null, this.student.calculate(student, null));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		submitBtn.addActionListener(e -> {
			if (table.getEditingRow() != -1) {
				table.getCellEditor().stopCellEditing();
			}

			try {
				if (this.student.getLevel() != "P") {
					ArrayList<ModuleGrades> allGrades = new ArrayList<>();
					boolean invalid = false;
					if (this.checkEntered()) {
						for (int i = 0; i < rows; i++) {
							try {
								String code = (String) table.getValueAt(i, 1);
								String firstGradeTxt = (String) table.getValueAt(i, 3);
								ModuleGrades grades = null;

								if (ValidCheck.grade(firstGradeTxt)) {
									int firstGrade = Integer.parseInt(firstGradeTxt);

									if (this.isResitGrade(i)) {

										String resitTxt = (String) table.getValueAt(i, 4);

										if (ValidCheck.grade(resitTxt)) {
											int resit = Integer.parseInt(resitTxt);
											grades = new ModuleGrades(Module.getModule(code), firstGrade, resit);
										} else {
											JOptionPane.showMessageDialog(null, "A grade you entered was invalid");
										}

									} else {
										grades = new ModuleGrades(Module.getModule(code), firstGrade);
									}

									allGrades.add(grades);
								} else {
									invalid = true;
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						try {
							if (!invalid) {
								JOptionPane.showMessageDialog(null, this.student.progress(student, allGrades));
							} else {
								JOptionPane.showMessageDialog(null, "A grade you entered was invalid");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} else {
						System.out.println("Missed a grade");
					}
				} else {
					JOptionPane.showMessageDialog(null, this.student.progress(student, null));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
	}

	public void draw() {
		this.markingScreen = new JPanel();
		this.markingScreen.setLayout(null);
		this.markingScreen.setBackground(new Color(70, 70, 70));

		this.markingScreen.add(titleTxt);
		this.markingScreen.add(markingPanel);
		this.markingScreen.add(backToProfileBtn);
		this.markingScreen.add(calculateBtn);
		this.markingScreen.add(submitBtn);

		this.markingPanel.setLayout(new BorderLayout());

		rows = 0;
		this.addModules();

		screen.frame.add(this.markingScreen);
	}

	private boolean isResitGrade(int row) {
		if (table.getValueAt(row, 4) == null) {
			return false;
		}
		return true;
	}

	private boolean checkEntered() {
		for (int i = 0; i < rows; i++) {
			System.out.println(i);
			if (table.getValueAt(i, 3) == null) {
				System.out.println(table.getValueAt(i, 3));
				return false;
			}
		}
		return true;
	}

	private void addModules() {

		try {
			StudentStatus stuStatus = this.student.getStudentStatus();

			if (stuStatus.isRegistered() && stuStatus.getLevel() != 'P') {
				DefaultTableModel model = new DefaultTableModel() {

					@Override
					public boolean isCellEditable(int row, int column) {
						return column == 3 || column == 4;
					}
				};

				table = new JTable(model);

				// Create a couple of columns
				model.addColumn("Name");
				model.addColumn("Code");
				model.addColumn("Credits");
				model.addColumn("Grade");
				model.addColumn("Resit Grade");
				for (ModuleChoice module : stuStatus.getCurrentModules()) {
					Module mod = Module.getModule(module.getModuleCode());
					String name = mod.getName();
					int credits = mod.getCredits();
					model.addRow(new Object[] { name, module.getModuleCode(), credits, null, null });
					rows++;
				}

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setViewportView(table);

				markingPanel.add(scrollPane);

			} else if (stuStatus.getLevel() == 'P') {
				promptTxt.setText("Student on Placement");
				titleTxt.setVisible(false);
				this.markingScreen.add(promptTxt);
				submitBtn.setText("Progress");
			} else {
				promptTxt.setText("Student not yet registered");
				titleTxt.setVisible(false);
				submitBtn.setVisible(false);
				this.markingScreen.add(promptTxt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initComponents() {
		titleTxt = new JLabel();
		markingPanel = new JPanel();
		backToProfileBtn = new JButton();
		submitBtn = new JButton();
		calculateBtn = new JButton();
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

		// ---- titleTxt ----
		titleTxt.setText("Marking");
		titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
		titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 10f));
		titleTxt.setForeground(Color.white);
		add(titleTxt);
		titleTxt.setBounds(430, 25, 140, 55);

		promptTxt.setText("Marking");
		promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
		promptTxt.setFont(promptTxt.getFont().deriveFont(promptTxt.getFont().getSize() + 10f));
		promptTxt.setForeground(Color.white);
		add(promptTxt);
		promptTxt.setBounds(430, 25, 140, 55);
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
		backToProfileBtn.setText("Back To Profile");
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

		calculateBtn.setText("Calculate Grade");
		add(calculateBtn);
		calculateBtn.setBounds(415, 445, 170, 20);

		submitBtn.setText("Progress Student");
		add(submitBtn);
		submitBtn.setBounds(415, 475, 170, 20);

		// JFormDesigner - End of component initialization //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Katie
	private JLabel titleTxt;
	private JPanel markingPanel;
	private JButton backToProfileBtn;
	private JButton submitBtn;
	private JButton calculateBtn;
	private JLabel promptTxt;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
