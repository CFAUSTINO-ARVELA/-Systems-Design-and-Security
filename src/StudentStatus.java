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
    }

    public JPanel draw() {
        this.studentStatusScreen = new JPanel();
        JLabel titleTxt = new JLabel("Welcome to student status"); 
        JButton backToProfileBtn = new JButton("Back to profile");

        backToProfileBtn.addActionListener(e -> {
            this.studentStatusScreen.setVisible(false);
            screen.navToProfile(this.loggedInAccount);
        });

        this.studentStatusScreen.add(titleTxt);
        this.studentStatusScreen.add(backToProfileBtn);

        return this.studentStatusScreen;
    }
}