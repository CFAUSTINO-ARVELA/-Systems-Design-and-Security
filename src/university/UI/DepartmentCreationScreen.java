package university.UI;

import university.ScreenManager;
import university.Department;
import university.ValidCheck;
import university.UI.DepartmentManagementScreen;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DepartmentCreationScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel departmentScreen;
    private ScreenManager screen;
    private DepartmentManagementScreen departmentManagement;
    

    DepartmentCreationScreen(ScreenManager scr, DepartmentManagementScreen departManage) {
        this.initComponents();
        this.screen = scr;
        this.departmentManagement = departManage;
    }

    public void draw() throws Exception {
        this.departmentScreen = new JPanel();
        this.departmentScreen.setBackground(new Color(70, 70, 70));
        this.departmentScreen.setLayout(null);
        
        this.departmentScreen.add(backToProfileBtn);
        this.departmentScreen.add(createTxt);
        this.departmentScreen.add(promptTxt);
        this.departmentScreen.add(nameTxt);
        this.departmentScreen.add(codeTxt);
        this.departmentScreen.add(nameInput);
        this.departmentScreen.add(codeInput);
        this.departmentScreen.add(submitBtn);
        this.departmentScreen.add(departmentTxt);
        
        
        backToProfileBtn.addActionListener(e -> {
            this.departmentScreen.setVisible(false);
            try {
				this.departmentManagement.draw();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        
        submitBtn.addActionListener(e -> {
            if (!ValidCheck.input(nameInput) || !ValidCheck.input(codeInput)) {
                JOptionPane.showMessageDialog(null, "Please do not insert any symbols");
            } else if(nameInput.getText().isEmpty())
        		JOptionPane.showMessageDialog(null, "Please insert the name of the Department");
        	else if (codeInput.getText().isEmpty()) {
        		JOptionPane.showMessageDialog(null, "Please insert the code of the Department");
        	} else if (codeInput.getText().length() != 3)
        		JOptionPane.showMessageDialog(null, "Please insert a valid Department three-letter code");
        	else {
	        	Department dept = new Department(codeInput.getText(), nameInput.getText());
	            try {
	                int count = dept.createDept();
	                if (count != 0) {
	                	JOptionPane.showMessageDialog(null, "Successfully created Department: " + dept.getName());
	                	this.departmentScreen.setVisible(false);
	                	DepartmentManagementScreen newDeptMana= new DepartmentManagementScreen(this.screen,departmentManagement.getTecMangScree());
	                	newDeptMana.draw();
	                } else {
	                	JOptionPane.showMessageDialog(null, "Please choose a differente Department Code.");
	                }
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(null, "SQL error, please try again");
	            }
        	}
        });
        	screen.frame.add(this.departmentScreen);
    }

    private void initComponents() {
        createTxt = new JLabel();
        promptTxt = new JLabel();
        nameTxt = new JLabel();
        codeTxt = new JLabel();
        nameInput = new JTextField();
        codeInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();
        departmentTxt = new JLabel();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- createTxt ----
        createTxt.setText("Create Department");
        createTxt.setFont(createTxt.getFont().deriveFont(createTxt.getFont().getSize() + 12f));
        createTxt.setHorizontalAlignment(SwingConstants.CENTER);
        createTxt.setForeground(Color.white);
        add(createTxt);
        createTxt.setBounds(350, 90, 299, createTxt.getPreferredSize().height);

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

        //---- codeTxt ----
        codeTxt.setText("Code");
        codeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        codeTxt.setFont(codeTxt.getFont().deriveFont(codeTxt.getFont().getSize() + 3f));
        codeTxt.setForeground(Color.white);
        add(codeTxt);
        codeTxt.setBounds(230, 227, 140, 16);
        add(nameInput);
        nameInput.setBounds(385, 180, 235, nameInput.getPreferredSize().height);
        add(codeInput);
        codeInput.setBounds(385, 220, 235, 30);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(432, 445, 135, submitBtn.getPreferredSize().height);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);

        //---- departmentTxt ----
        departmentTxt.setText("Department Management");
        departmentTxt.setFont(departmentTxt.getFont().deriveFont(departmentTxt.getFont().getSize() + 10f));
        departmentTxt.setHorizontalAlignment(SwingConstants.CENTER);
        departmentTxt.setForeground(Color.white);
        add(departmentTxt);
        departmentTxt.setBounds(347, 35, 305, 31);

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

    private JLabel createTxt;
    private JLabel promptTxt;
    private JLabel nameTxt;
    private JLabel codeTxt;
    private JTextField nameInput;
    private JTextField codeInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    private JLabel departmentTxt;

	
	public void actionPerformed(ActionEvent e) {
		
	}
}
