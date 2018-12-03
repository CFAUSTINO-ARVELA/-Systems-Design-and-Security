package university.UI;
import java.awt.*;
import javax.swing.*;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import university.*;
import university.Module;

public class DegreeDetailsScreen extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;
    private ScreenManager screen;
    private JPanel degreDetScreen;
    private Degree deg;
    private DegreeManagementScreen degreeManagement;
    private JTable moduleTable;
    


    public DegreeDetailsScreen(ScreenManager scr,DegreeManagementScreen degreeManagement, Degree deg) {
        this.screen = scr;
        this.deg = deg;
        this.degreeManagement = degreeManagement;
        this.initComponents();
        this.initListeners();
    }

    public void initListeners() {
        backToProfileBtn.addActionListener(e -> {
            this.degreDetScreen.setVisible(false);
            DegreeManagementScreen newDegMangSecreen = new DegreeManagementScreen(this.screen,degreeManagement.getTecMaangScree());
            try {
				newDegMangSecreen.draw();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        
        deleteBtn.addActionListener(e -> {
           if (moduleTable.getSelectedRow() > -1) {
                String code = (String) moduleTable.getValueAt(moduleTable.getSelectedRow(), 0);
                String year = (String) moduleTable.getValueAt(moduleTable.getSelectedRow(), 3);
                
                JLabel label_login = new JLabel("If you wish to continue please insert your account details.");
                JLabel labem_email = new JLabel("Email:");
                JTextField email = new JTextField();

                JLabel label_password = new JLabel("Password:");
                JPasswordField password = new JPasswordField();

                Object[] array = {label_login, labem_email, email, label_password, password };

                int res = JOptionPane.showConfirmDialog(null, array, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (res == JOptionPane.OK_OPTION) {
	                try {
	                	if(Account.delVerification(email.getText(), Account.md5hash(password.getText()), 3)) {
                            Module.remAssoModDeg(code, deg.getCode(), Integer.parseInt(year));
                            JOptionPane.showMessageDialog(null, "Successfully remove Module.");
                            this.degreDetScreen.setVisible(false);
                            DegreeDetailsScreen newDegDet = new DegreeDetailsScreen(this.screen,this.degreeManagement,this.deg);
                            newDegDet.draw();
                            
	                		
	                	}else
	                		JOptionPane.showMessageDialog(null, "Please insert the correct account details");    
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a Module to remove");
            }
        });
    }

    public void draw() throws Exception {
        this.degreDetScreen = new JPanel();
        this.degreDetScreen.setBackground(new Color(70, 70, 70));
        this.degreDetScreen.setLayout(null);

       
        
        this.degreDetScreen.add(backToProfileBtn);
        this.degreDetScreen.add(courseTxt);
        this.degreDetScreen.add(codeTxt);
        this.degreDetScreen.add(durationTxt);
        this.degreDetScreen.add(secondaryTxt);
        this.degreDetScreen.add(titleTxt);
        this.degreDetScreen.add(typeTxt);
        this.degreDetScreen.add(promptTxt);
        this.degreDetScreen.add(degreeCode);
        this.degreDetScreen.add(degreeDuration);
        this.degreDetScreen.add(degreeType);
        this.degreDetScreen.add(degreeSecondDept);
        this.degreDetScreen.add(moduleTxt);
        this.degreDetScreen.add(tablePanel);
        this.degreDetScreen.add(deleteBtn);
        
        this.tablePanel.setLayout(new BorderLayout());

        this.degreDetScreen.setLayout(null);

        moduleTable = new JTable(TableModel.buildTableModel(deg.getDegModules()));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(moduleTable);

        tablePanel.add(scrollPane);
        
        screen.frame.add(this.degreDetScreen);
    }

    private void initComponents(){
        // JFormDesigner - Component initialization - DO NOT MODIFY
        // //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        courseTxt = new JLabel();
        codeTxt = new JLabel();
        durationTxt = new JLabel();
        secondaryTxt = new JLabel();
        backToProfileBtn = new JButton();
        titleTxt = new JLabel();
        typeTxt = new JLabel();
        promptTxt = new JLabel();
        degreeCode = new JLabel();
        degreeDuration = new JLabel();
        degreeType = new JLabel();
        degreeSecondDept = new JLabel();
        moduleTxt = new JLabel();
        tablePanel = new JPanel();
        deleteBtn = new JButton();
        //======== this ========

        
        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);
        
      //---- table ----
        tablePanel.setBounds(177, 330, 645, 100);

      //---- titleTxt ----
        titleTxt.setText("Course Details");
        titleTxt.setFont(titleTxt.getFont().deriveFont(titleTxt.getFont().getSize() + 10f));
        titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
        titleTxt.setForeground(Color.white);
        add(titleTxt);
        titleTxt.setBounds(347, 35, 305, 31);
        
        //---- courseTxt ----
        courseTxt.setText(deg.getName());
        courseTxt.setFont(courseTxt.getFont().deriveFont(courseTxt.getFont().getSize() + 12f));
        courseTxt.setHorizontalAlignment(SwingConstants.CENTER);
        courseTxt.setForeground(Color.white);
        add(courseTxt);
        courseTxt.setBounds(347 - courseTxt.getPreferredSize().width/2, 90,305 + courseTxt.getPreferredSize().width, courseTxt.getPreferredSize().height);

      //---- promptTxt ----
        promptTxt.setText(deg.getMainDept().getName());
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(387, 130, 225, promptTxt.getPreferredSize().height);

        //---- codeTxt ----
        codeTxt.setText("Code: ");
        codeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        codeTxt.setFont(codeTxt.getFont().deriveFont(codeTxt.getFont().getSize() + 3f));
        codeTxt.setForeground(Color.white);
        add(codeTxt);
        codeTxt.setBounds(185, 155, 185, codeTxt.getPreferredSize().height);

        degreeCode.setText(deg.getCode());
        degreeCode.setHorizontalAlignment(SwingConstants.RIGHT);
        degreeCode.setFont(degreeCode.getFont().deriveFont(degreeCode.getFont().getSize() + 3f));
        degreeCode.setForeground(Color.white);
        add(degreeCode);
        degreeCode.setBounds(385, 155, degreeCode.getPreferredSize().width, degreeCode.getPreferredSize().height);
        
        //---- typeTxt ----
        typeTxt.setText("Type: ");
        typeTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        typeTxt.setFont(typeTxt.getFont().deriveFont(typeTxt.getFont().getSize() + 3f));
        typeTxt.setForeground(Color.white);
        add(typeTxt);
        typeTxt.setBounds(155, 190, 215, 16);
        
        
        if (deg.getType().matches("MSc"))
        	degreeType.setText("Postgraduate");
		else
			degreeType.setText("Undergraduate");
        degreeType.setHorizontalAlignment(SwingConstants.RIGHT);
        degreeType.setFont(degreeType.getFont().deriveFont(degreeType.getFont().getSize() + 3f));
        degreeType.setForeground(Color.white);
        add(degreeType);
        degreeType.setBounds(385, 190, degreeType.getPreferredSize().width, degreeType.getPreferredSize().height);
        
        
        //---- durationTxt ----
        durationTxt.setText("Duration: ");
        durationTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        durationTxt.setFont(durationTxt.getFont().deriveFont(durationTxt.getFont().getSize() + 3f));
        durationTxt.setForeground(Color.white);
        add(durationTxt);
        durationTxt.setBounds(155, 225, 215, 16);
        
        if (deg.getType().matches("BSc") || deg.getType().matches("BEng") )
			if (deg.getPlacement() == false)
				degreeDuration.setText("3 Years");
			else 
				degreeDuration.setText("4 Years");
        else if (deg.getType().matches("MSc"))
        	degreeDuration.setText("1 Year");
		else
			if (deg.getPlacement() == false)
				degreeDuration.setText("4 Years");
			else 
				degreeDuration.setText("5 Years");
        degreeDuration.setHorizontalAlignment(SwingConstants.RIGHT);
        degreeDuration.setFont(degreeDuration.getFont().deriveFont(degreeDuration.getFont().getSize() + 3f));
        degreeDuration.setForeground(Color.white);
        add(degreeDuration);
        degreeDuration.setBounds(385, 225, degreeDuration.getPreferredSize().width, degreeDuration.getPreferredSize().height);
        

        //---- secondaryTxt ----
        if(!deg.getSeconDepts().isEmpty()) {
	        secondaryTxt.setText("Secondary Departments: ");
	        secondaryTxt.setHorizontalAlignment(SwingConstants.RIGHT);
	        secondaryTxt.setFont(secondaryTxt.getFont().deriveFont(secondaryTxt.getFont().getSize() + 3f));
	        secondaryTxt.setForeground(Color.white);
	        add(secondaryTxt);
	        secondaryTxt.setBounds(175, 260, secondaryTxt.getPreferredSize().width, secondaryTxt.getPreferredSize().height);
	        
	        
	        String depts = new String( deg.getSeconDepts().get(0).getName());
	        for (int i = 1; i < deg.getSeconDepts().size(); i++)
	        	depts +=", " +  deg.getSeconDepts().get(i).getName();
	        degreeSecondDept.setText(depts);
	        degreeSecondDept.setHorizontalAlignment(SwingConstants.RIGHT);
	        degreeSecondDept.setFont(degreeSecondDept.getFont().deriveFont(degreeSecondDept.getFont().getSize() + 3f));
	        degreeSecondDept.setForeground(Color.white);
	        add(degreeSecondDept);
	        degreeSecondDept.setBounds(385, 260, degreeSecondDept.getPreferredSize().width, degreeSecondDept.getPreferredSize().height);
        }
       
        moduleTxt.setText("Modules: ");
        moduleTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        moduleTxt.setFont(moduleTxt.getFont().deriveFont(moduleTxt.getFont().getSize() + 3f));
        moduleTxt.setForeground(Color.white);
        add(moduleTxt);
        moduleTxt.setBounds(155, 295 , 215, 16);
        
      //---- deleteBtn ----
        deleteBtn.setText("Remove Module");
        add(deleteBtn);
        deleteBtn.setBounds(415, 465, 170, 30);
        
      
      //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(414, 500, 170, 50);


        
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
    private JLabel courseTxt;
    private JLabel codeTxt;
    private JLabel promptTxt;
    private JLabel durationTxt;
    private JLabel secondaryTxt;
    private JButton backToProfileBtn;
    private JLabel titleTxt;
    private JLabel typeTxt;
    private JLabel degreeCode; 
    private JLabel degreeDuration;
    private JLabel degreeType;
    private JLabel degreeSecondDept;
    private JLabel moduleTxt;
    private JPanel tablePanel;
    private JButton deleteBtn;
    // JFormDesigner - End of variables declaration //GEN-END:variables

    
    public void actionPerformed(ActionEvent e) {
       

    }
}
