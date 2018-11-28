package university.UI;

import java.awt.*;
import javax.swing.*;

import university.UI.ProfileScreen;
import university.ScreenManager;
import university.TableModel;
import university.Account;
import university.Degree;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;

import java.sql.*;

class DegreeManagementScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    public JPanel degreeManagement;
    private ScreenManager screen;
    private TeachingManagementScreen teachingScreen;
    private JTable degreeTable;
    private Connection con = null;
    private Statement stmt = null;

    DegreeManagementScreen(ScreenManager scr,TeachingManagementScreen teach) {
        this.initComponents();
        this.screen = scr;
        this.teachingScreen = teach;
    }
    
    public TeachingManagementScreen getTecMaangScree() {
    	return teachingScreen;
    }

    private DegreeManagementScreen getdegreeManagement() {
    	return this;
    }
    
    private JPanel getJPanel() {
    	return this.degreeManagement;
    }
    private ScreenManager getScreen() {
    	return this.screen;
    }
    
   
    public void draw() throws Exception {
        this.degreeManagement = new JPanel();
        this.degreeManagement.setBackground(new Color(70, 70, 70));

        this.degreeManagement.add(backToTeachingBtn);
        this.degreeManagement.add(degreeManagementTxt);
        this.degreeManagement.add(createBtn);
        this.degreeManagement.add(deleteBtn);
        this.degreeManagement.add(tablePanel);

        this.tablePanel.setLayout(new BorderLayout());

        this.degreeManagement.setLayout(null);

        backToTeachingBtn.addActionListener(e -> {
            this.degreeManagement.setVisible(false);
            
            try {
				this.teachingScreen.draw();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        createBtn.addActionListener(e -> {
            this.degreeManagement.setVisible(false);
            DegreeCreationScreen degreeCreate = new DegreeCreationScreen(this.screen, this);
            degreeCreate.draw();
        });
        deleteBtn.addActionListener(e -> {
            if (degreeTable.getSelectedRow() > -1) {
                String code = (String) degreeTable.getValueAt(degreeTable.getSelectedRow(), 0);
                String name = (String) degreeTable.getValueAt(degreeTable.getSelectedRow(), 1);
                Degree degToDelete = new Degree(code);
                
                
                
                    
                    
                
                JLabel label_login = new JLabel("If you wish to continue please insert your account details.");
                JLabel labem_email = new JLabel("Email:");
                JTextField email = new JTextField();

                JLabel label_password = new JLabel("Password:");
                JPasswordField password = new JPasswordField();

                Object[] array = {label_login, labem_email, email, label_password, password };

                int res = JOptionPane.showConfirmDialog(null, array, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (res == JOptionPane.OK_OPTION) {
                	try {
                		if(Account.delVerification(email.getText(), password.getText(), 3)) {
                			degToDelete.deleteDegree();
    	                }else
    	                	JOptionPane.showMessageDialog(null, "Please insert the correct account details");    
	                } catch (Exception e1) {
	                    e1.printStackTrace();
	                }
	                this.degreeManagement.setVisible(false);
	                DegreeManagementScreen newDegMan = new DegreeManagementScreen(this.screen,this.teachingScreen);
	                try {
						newDegMan.draw();
					} catch (Exception e1) {
						//newDegMan TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                JOptionPane.showMessageDialog(null, "Successfully deleted degree: " + name);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a Degree to delete");
            }
        });

        degreeTable = new JTable(TableModel.buildTableModel(Degree.getDegList()));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(degreeTable);

        tablePanel.add(scrollPane);

        screen.frame.add(this.degreeManagement);

        
        degreeTable.addMouseListener(new MouseAdapter() {
            private JComponent degreeManagement;
			private ScreenManager screen;
			private DegreeManagementScreen DegreeManagementScreen;
			private Degree deg;

			public void MouseAdapter() {
				this.DegreeManagementScreen= getdegreeManagement();
				this.screen = getScreen();
				this.degreeManagement = getJPanel();
		
			}
			public void mouseClicked(MouseEvent e) {
            	if (e.getClickCount() == 2) {
            		MouseAdapter();
            		 String code = (String) degreeTable.getValueAt(degreeTable.getSelectedRow(), 0);
                     String name = (String) degreeTable.getValueAt(degreeTable.getSelectedRow(), 1);
            		try {
						deg = Degree.getDegree(code);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            		this.degreeManagement.setVisible(false);
            		DegreeDetailsScreen degreeCreate;
					
                    try {
                    	degreeCreate = new DegreeDetailsScreen(this.screen, this.DegreeManagementScreen, deg);
						degreeCreate.draw();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
            	
            		
            	}
            }
        });
        
    }


     
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        backToTeachingBtn = new JButton();
        degreeManagementTxt = new JLabel();
        deleteBtn = new JButton();
        createBtn = new JButton();
        tablePanel = new JPanel();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        tablePanel.setBounds(177, 100, 645, 290);

        //---- backToTeachingBtn ----
        backToTeachingBtn.setText("Back");
        add(backToTeachingBtn);
        backToTeachingBtn.setBounds(414, 500, 170, 50);

        //---- degreeManagementTxt ----
        degreeManagementTxt.setText("Degree Management");
        degreeManagementTxt.setFont(degreeManagementTxt.getFont().deriveFont(degreeManagementTxt.getFont().getSize() + 10f));
        degreeManagementTxt.setHorizontalAlignment(SwingConstants.CENTER);
        degreeManagementTxt.setForeground(Color.white);
        add(degreeManagementTxt);
        degreeManagementTxt.setBounds(347, 35, 305, 31);

        //---- deleteBtn ----
        deleteBtn.setText("Delete Degree");
        add(deleteBtn);
        deleteBtn.setBounds(415, 465, 170, 30);

        //---- createBtn ----
        createBtn.setText("Create Degree");
        add(createBtn);
        createBtn.setBounds(415, 430, 170, 30);

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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    private JButton backToTeachingBtn;
    private JLabel degreeManagementTxt;
    private JButton deleteBtn;
    private JButton createBtn;
    private JPanel tablePanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
