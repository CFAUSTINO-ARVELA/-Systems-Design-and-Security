import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.*;

class StudentStatus implements ActionListener {

    // The account that is being viewed
    private Account stuAccount;

    // Who is logged in, eg a register or student
    private Account loggedInAccount;
    private JPanel studentStatusScreen;
    private ScreenManager screen;
    
    StudentStatus(Account loggedInAcc, Account stuAcc, ScreenManager screen) {
        this.loggedInAccount = loggedInAcc;
        this.stuAccount = stuAcc;
	this.screen = screen;
    }

    public JPanel draw() {
        this.studentStatusScreen = new JPanel();
        JLabel titleTxt = new JLabel("Welcome to student status"); 
        JButton backToProfileBtn = new JButton("Back to profile");

        this.studentStatusScreen.add(titleTxt);
        this.studentStatusScreen.add(backToProfileBtn);

        backToProfileBtn.addActionListener(e -> {
            this.studentStatusScreen.setVisible(false);
            screen.navToProfile(this.loggedInAccount);
        });

        return this.studentStatusScreen;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
