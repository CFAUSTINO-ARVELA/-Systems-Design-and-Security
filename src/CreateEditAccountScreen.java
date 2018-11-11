import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

class AccountManagementScreen implements ActionListener {

    public JPanel accountManagement; 
    private ScreenManager screen;
    private Account account;

    AccountManagementScreen(ScreenManager scr, Account acc) {
        this.screen = scr;
        this.account = acc;
    }

    public JPanel draw() {
        this.accountManagement = new JPanel();
        JLabel titleTxt = new JLabel("Welcome to account management");
        JButton backToProfileBtn = new JButton("Back");

        this.accountManagement.add(titleTxt);
        this.accountManagement.add(backToProfileBtn);

        backToProfileBtn.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            screen.navToProfile(this.account);
        });
        
        return this.accountManagement;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}