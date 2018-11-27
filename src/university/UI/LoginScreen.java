package university.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import university.*;



public class LoginScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private ScreenManager screen;
    private JPanel loginScreen;
    private ProfileScreen profileScreen;
    private static Connection con;

    public LoginScreen(ScreenManager scr) {
        initComponents();
        this.screen = scr;
    }

    public void draw() {
        this.loginScreen = new JPanel();

        this.loginScreen.setVisible(true);
        this.loginScreen.add(welcomeText);
        this.loginScreen.add(promptText);
        this.loginScreen.add(emailInput);
        this.loginScreen.add(passwordInput);
        this.loginScreen.add(emailText);
        this.loginScreen.add(passwordText);
        this.loginScreen.add(submitButton);

        submitButton.addActionListener(e -> {
            try {
                this.login();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        this.loginScreen.setLayout(null);
        this.loginScreen.setBackground(new Color(70, 70, 70));

        this.screen.frame.add(this.loginScreen);
    }

    public static void connectToDB() throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team002", "team002", "e8f208af");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void login() throws SQLException {

        connectToDB();
        Statement stmt = null;
        PreparedStatement pst1, pst2 = null;
        ResultSet res1, res2 = null;
        String sql = "select * from account where Email=? and Password=?";
        String clearLvl = "select Clearance from account where Email = ? and Password =?";

        try {
            pst1 = con.prepareStatement(sql);
            pst1.setString(1, emailInput.getText());
            pst1.setString(2, passwordInput.getText());
            res1 = pst1.executeQuery();

            pst2 = con.prepareStatement(clearLvl);
            pst2.setString(1, emailInput.getText());
            pst2.setString(2, passwordInput.getText());
            res2 = pst2.executeQuery();

            if (emailInput.getText().equals("") || passwordInput.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter an email and password");
            } else if (!res1.next()) {
                JOptionPane.showMessageDialog(null, "Account details not found");
            } else {
                res2.next();
                // get data
                String title = res1.getString("Title");
                String forename = res1.getString("Forename");
                String surname = res1.getString("Surname");
                String username = res1.getString("Username");
                String password = res1.getString("Password");
                Clearance clearance = null;

                switch (res2.getInt("Clearance")) {
                case 1:
                    clearance = Clearance.TEACHER;
                    break;
                case 2:
                    clearance = Clearance.REGISTRAR;
                    break;
                case 3:
                    clearance = Clearance.ADMIN;
                    break;
                default:
                    clearance = Clearance.STUDENT;
                    break;
                }
                Account account = new Account(title, forename, surname, username, password, clearance);
                profileScreen = new ProfileScreen(this.screen, account);
                this.loginScreen.setVisible(false);
                profileScreen.draw();
    		}
    		
    	}
    	catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (res2 != null)
	    		con.close();
	    		res2.close();
	    		pst2.close();
		}


    }

    private void initComponents() {
        welcomeText = new JLabel();
        promptText = new JLabel();
        emailInput = new JTextField();
        passwordInput = new JPasswordField();
        emailText = new JLabel();
        passwordText = new JLabel();
        submitButton = new JButton();

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

        welcomeText.setText("Welcome to University Software");
        welcomeText.setFont(welcomeText.getFont().deriveFont(welcomeText.getFont().getSize() + 10f));
        welcomeText.setHorizontalTextPosition(SwingConstants.CENTER);
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setForeground(Color.white);
        add(welcomeText);
        welcomeText.setBounds(310, 30, 385, 105);

        // ---- promptText ----
        promptText.setHorizontalAlignment(SwingConstants.CENTER);
        promptText.setFont(promptText.getFont().deriveFont(promptText.getFont().getSize() + 5f));
        promptText.setText("Please log-in below:");
        promptText.setAlignmentX(-4.0F);
        promptText.setForeground(Color.white);
        add(promptText);
        promptText.setBounds(305, 155, 385, 45);
        add(emailInput);
        emailInput.setBounds(340, 240, 315, emailInput.getPreferredSize().height);
        add(passwordInput);
        passwordInput.setBounds(340, 305, 315, 30);

        // ---- emailText ----
        emailText.setText("Email");
        emailText.setFont(emailText.getFont().deriveFont(emailText.getFont().getSize() + 4f));
        emailText.setHorizontalAlignment(SwingConstants.CENTER);
        emailText.setForeground(Color.white);
        add(emailText);
        emailText.setBounds(470, 215, 55, emailText.getPreferredSize().height);

        // ---- passwordText ----
        passwordText.setText("Password");
        passwordText.setFont(passwordText.getFont().deriveFont(passwordText.getFont().getSize() + 4f));
        passwordText.setHorizontalAlignment(SwingConstants.CENTER);
        passwordText.setForeground(Color.white);
        add(passwordText);
        passwordText.setBounds(455, 280, 80, 21);

        // ---- submitButton ----
        submitButton.setText("Submit");
        add(submitButton);
        submitButton.setBounds(414, 500, 170, 50);
        Style.Button(submitButton);

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
    }

    private JLabel welcomeText;
    private JLabel promptText;
    private JTextField emailInput;
    private JPasswordField passwordInput;
    private JLabel emailText;
    private JLabel passwordText;
    private JButton submitButton;
}
