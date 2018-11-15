import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ProfileScreen implements ActionListener {

    // protected string accountType;
    private JPanel profileScreen;
    private ScreenManager screen;
    private Account account;

    public ProfileScreen(Account acc, ScreenManager screenManager) {
        this.screen = screenManager;
        this.account = acc;
    }

    public void draw() {
        this.profileScreen = new JPanel();
        this.profileScreen.setBackground(new Color(250, 100, 250));

        this.addSharedComponents();
        screen.frame.add(this.profileScreen);
    }

    public void addSharedComponents() {
        JLabel profileText = new JLabel("Profile: " + this.account.getForename());
        JButton logoutButton = new JButton("Log-out");

        logoutButton.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            screen.navToLogin();
        });

        this.profileScreen.add(logoutButton);
        this.profileScreen.add(profileText);

        System.out.println(this.account.getClearance());

        switch (this.account.getClearance()) {
        // Implement cases for teacher and registrar
            case STUDENT:
                this.studentComponents();
                break;
             case ADMIN:
                this.adminComponents();
                break;
        // others
        }
    }

    public void studentComponents() {
        JLabel profileTxt = new JLabel("Student Profile");
        JLabel titleTxt = new JLabel("Title: " + this.account.getTitle());
        JLabel nameTxt = new JLabel("Name: " + this.account.getForename() + " " + this.account.getSurname());
        JButton statusBtn = new JButton("Student Status");

        this.profileScreen.add(profileTxt);
        this.profileScreen.add(titleTxt);
        this.profileScreen.add(nameTxt);
        this.profileScreen.add(statusBtn);

        statusBtn.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            screen.navToStudentStatus(this.account, this.account);
        });
    }

    public void adminComponents() {
        JLabel welcomeText = new JLabel("Welcome to edit accounts");
        JButton accountManagementBtn = new JButton("Account Management");
        JButton teachingManagementBtn = new JButton("Teaching Management");

        accountManagementBtn.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            screen.navToAccountManagementScreen(account);
        });
        teachingManagementBtn.addActionListener(e -> {
            this.profileScreen.setVisible(false);
            screen.navToTeachingManagementScreen(account);
        });
        this.profileScreen.add(welcomeText);
        this.profileScreen.add(teachingManagementBtn);
        this.profileScreen.add(accountManagementBtn);
    }

    public void regComponents() {

    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

    // private void logout() {
    // screen.navToLogin();
    // }
}