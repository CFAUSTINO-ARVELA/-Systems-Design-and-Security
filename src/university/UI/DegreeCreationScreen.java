package university.UI;

import java.awt.*;
import javax.swing.*;

import university.UI.ProfileScreen;
import university.Degree;
import university.Department;
import university.ScreenManager;

import java.awt.event.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class DegreeCreationScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel degreeCreation;
    private ScreenManager screen;
    private DegreeManagementScreen degreeManagement;
    private String[] degreeTypes = { "BSc", "BEng", "MComp", "MEng", "MSc" };
    private String[] placementYear = { "No", "Yes" };
    private ArrayList<String> departments = new ArrayList<String>();
    private List<JCheckBox> checkboxes = new ArrayList<>();

    DegreeCreationScreen(ScreenManager scr, DegreeManagementScreen degreeManage) {
        this.initComponents();
        this.screen = scr;
        this.degreeManagement = degreeManage;
    }

    public void draw() {
        this.degreeCreation = new JPanel();
        this.degreeCreation.setBackground(new Color(70, 70, 70));
        this.degreeCreation.setLayout(null);

        this.degreeCreation.add(backToProfileBtn);
        this.degreeCreation.add(createTxt);
        this.degreeCreation.add(promptTxt);
        this.degreeCreation.add(nameTxt);
        this.degreeCreation.add(mainDepartmentTxt);
        this.degreeCreation.add(secondaryTxt);
        this.degreeCreation.add(nameInput);
        this.degreeCreation.add(mainInput);
        this.degreeCreation.add(submitBtn);
        this.degreeCreation.add(degreeTxt);
        this.degreeCreation.add(typeTxt);
        this.degreeCreation.add(typeInput);
        this.degreeCreation.add(placementInput);
        this.degreeCreation.add(placementTxt);
        this.degreeCreation.add(scrollPane);
        
        JPanel newPan = new JPanel();

        scrollPane.add(checkboxPanel);

        for (String element : departments) {
            
            JCheckBox box = new JCheckBox(element);
            checkboxes.add(box);
            newPan.add(box);
        }
        scrollPane.setViewportView(newPan);
        newPan.setLayout(new BoxLayout(newPan, BoxLayout.Y_AXIS));

        backToProfileBtn.addActionListener(e -> {
            this.degreeCreation.setVisible(false);
            try {
				this.degreeManagement.draw();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        submitBtn.addActionListener((e -> {
        	if (nameInput.getText().isEmpty())
        		JOptionPane.showMessageDialog(null, "Please insert the name of the degree");
        	else {
	            ArrayList<Department> secondaryDepts = new ArrayList<Department>();
	            for (JCheckBox box : checkboxes) {
	            	//System.out.println(box.getText());
	                if (box.isSelected()) {
	                    try {
	                        secondaryDepts.add(Department.getDept(box.getText()));
	                        //System.out.println(secondaryDepts.size());
	                    } catch (Exception e1) {
	                        // TODO Auto-generated catch block
	                        e1.printStackTrace();
	                    }
	                }
	            }
	
	            String type = typeInput.getSelectedItem().toString();
	            boolean placement;
	            String placementText = placementInput.getSelectedItem().toString();
	            if (placementText.equals("Yes")) {
	                placement = true;
	            } else {
	                placement = false;
	            }
	            
	            if (type == "MSc" && placement == true)
                	JOptionPane.showMessageDialog(null, "This degree cannot offer a year in industry. /n Please select a different type of degree.");
	            else {
	            
		            try {
		            	int count = 0;
		            	Degree deg;
		                if (placement == true) {
		                	//System.out.println("Yes");
		                	//System.out.println(secondaryDepts.size() + "Aqui");
		                	Department dep = Department.getDept(mainInput.getSelectedItem().toString());
			                deg = new Degree(nameInput.getText(), dep, secondaryDepts, type, false);
			                deg.setCode();
			                count += deg.createDegree();
			            	Department dep2 = Department.getDept(mainInput.getSelectedItem().toString());
			                Degree deg2 = new Degree(nameInput.getText() + " with a Year in Industry", dep, secondaryDepts, type, placement);
			                deg2.setCode();
			                count += deg2.createDegree();
			                //System.out.println(count);
		            	}else {
		            		//System.out.println(secondaryDepts.size() + "Aqui");
		            		//System.out.println("Yes");
		            		Department dep = Department.getDept(mainInput.getSelectedItem().toString());
			                deg = new Degree(nameInput.getText(), dep, secondaryDepts, type, false);
			                deg.setCode();
			                //System.out.println(deg.getSeconDepts().size());
			                count += deg.createDegree();
			                //System.out.println(count);
		            	}
		            	
		                if (count == 0)
		                	JOptionPane.showMessageDialog(null, "There already is a degree with this name.  Please insert a different name.");
		                else {
		                	JOptionPane.showMessageDialog(null, "Successfully created Degree: " + deg.getName());	
		                	this.degreeCreation.setVisible(false);
		                	DegreeManagementScreen degMan = new DegreeManagementScreen(this.screen,degreeManagement.getTecMaangScree());
		                	degMan.draw();
		                }
		            } catch (Exception ex) {
		                ex.printStackTrace();
		                try {
							this.degreeManagement.draw();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		                JOptionPane.showMessageDialog(null, "SQL error, please try again");
		            }
	            }
	        }
/**
=======

            ArrayList<Department> secondaryDepts = new ArrayList<Department>();

            for (JCheckBox box : checkboxes) {
                if (box.isSelected()) {
                    try {
                        secondaryDepts.add(Department.getDept(box.getText()));
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }

            this.degreeCreation.setVisible(false);
            
            String typebox = typeInput.getSelectedItem().toString();

            String type = Character.toString((typebox.charAt(0)));
            boolean placement;
            boolean fouryears;
            String placementText = placementInput.getSelectedItem().toString();

            if (placementText.equals("Yes")) {
                placement = true;
            } else {
                placement = false;
            }
            
            if (typebox.equals("Undergraduate 4 years")) {
            	fouryears = true;
            } else {
            	fouryears = false;
            }

            try {
                Department dep = Department.getDept(mainInput.getSelectedItem().toString());
                Degree deg = new Degree(nameInput.getText(), dep, secondaryDepts, type, placement, fouryears);
                deg.setCode();
                Degree newDeg = deg.createDegree();
                this.degreeManagement.draw();
                JOptionPane.showMessageDialog(null, "Successfully created Degree: " + newDeg.getName());
            } catch (Exception ex) {
                ex.printStackTrace();
                this.degreeManagement.draw();
                JOptionPane.showMessageDialog(null, "SQL error, please try again");
            }
>>>>>>> be6d3e369968de5b9330658105e05417da2e3195 */
        }));

        screen.frame.add(this.degreeCreation);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        createTxt = new JLabel();
        promptTxt = new JLabel();
        nameTxt = new JLabel();
        mainDepartmentTxt = new JLabel();
        secondaryTxt = new JLabel();
        nameInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        degreeTxt = new JLabel();
        typeTxt = new JLabel();
        typeInput = new JComboBox(degreeTypes);
        placementTxt = new JLabel();
        placementInput = new JComboBox(placementYear);
        scrollPane = new JScrollPane();
        checkboxPanel = new JPanel();
        try {
            departments = Department.getAllDepNames();
            mainInput = new JComboBox((departments.toArray()));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- createTxt ----
        createTxt.setText("Create Degree");
        createTxt.setFont(createTxt.getFont().deriveFont(createTxt.getFont().getSize() + 12f));
        createTxt.setHorizontalAlignment(SwingConstants.CENTER);
        createTxt.setForeground(Color.white);
        add(createTxt);
        createTxt.setBounds(389, 90, 220, createTxt.getPreferredSize().height);

        //---- promptTxt ----
        promptTxt.setText("Please enter details below");
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        //---- nameTxt ----
        nameTxt.setText("Name");
        nameTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        nameTxt.setFont(nameTxt.getFont().deriveFont(nameTxt.getFont().getSize() + 3f));
        nameTxt.setForeground(Color.white);
        add(nameTxt);
        nameTxt.setBounds(185, 185, 185, nameTxt.getPreferredSize().height);

        //---- mainDepartmentTxt ----
        mainDepartmentTxt.setText("Main Department");
        mainDepartmentTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        mainDepartmentTxt.setFont(mainDepartmentTxt.getFont().deriveFont(mainDepartmentTxt.getFont().getSize() + 3f));
        mainDepartmentTxt.setForeground(Color.white);
        add(mainDepartmentTxt);
        mainDepartmentTxt.setBounds(155, 220, 215, 16);

        //---- secondaryTxt ----
        secondaryTxt.setText("Secondary Departments");
        secondaryTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        secondaryTxt.setFont(secondaryTxt.getFont().deriveFont(secondaryTxt.getFont().getSize() + 3f));
        secondaryTxt.setForeground(Color.white);
        add(secondaryTxt);
        secondaryTxt.setBounds(155, 255, 215, 16);
        add(nameInput);
        nameInput.setBounds(385, 180, 235, nameInput.getPreferredSize().height);
        add(mainInput);
        mainInput.setBounds(385, 215, 235, mainInput.getPreferredSize().height);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(414, 465, 170, submitBtn.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- degreeTxt ----
        degreeTxt.setText("Degree Management");
        degreeTxt.setFont(degreeTxt.getFont().deriveFont(degreeTxt.getFont().getSize() + 10f));
        degreeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        degreeTxt.setForeground(Color.white);
        add(degreeTxt);
        degreeTxt.setBounds(347, 35, 305, 31);

        //---- typeTxt ----
        typeTxt.setText("Type");
        typeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        typeTxt.setFont(typeTxt.getFont().deriveFont(typeTxt.getFont().getSize() + 3f));
        typeTxt.setForeground(Color.white);
        add(typeTxt);
        typeTxt.setBounds(130, 385, 240, 16);
        add(typeInput);
        typeInput.setBounds(385, 375, 235, 30);

        //---- placementTxt ----
        placementTxt.setText("Placement year?");
        placementTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        placementTxt.setFont(placementTxt.getFont().deriveFont(placementTxt.getFont().getSize() + 3f));
        placementTxt.setForeground(Color.white);
        add(placementTxt);
        placementTxt.setBounds(130, 420, 240, 16);
        add(placementInput);
        placementInput.setBounds(385, 415, 235, 30);
        add(scrollPane);
        scrollPane.setBounds(387, 255, 235, 115);
        checkboxPanel.setBounds(387, 255, 235, 115);

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
    private JLabel createTxt;
    private JLabel promptTxt;
    private JLabel nameTxt;
    private JLabel mainDepartmentTxt;
    private JLabel secondaryTxt;
    private JTextField nameInput;
    private JComboBox mainInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel degreeTxt;
    private JLabel typeTxt;
    private JComboBox typeInput;
    private JLabel placementTxt;
    private JComboBox placementInput;
    private JScrollPane scrollPane;
    private JPanel checkboxPanel;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
