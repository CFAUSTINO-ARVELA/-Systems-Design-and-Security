import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.*;

class AccountManagementScreen {

    public JPanel accountManagement; 
    private ScreenManager screen;
    private Account account;

    AccountManagementScreen(ScreenManager scr, Account acc) {
        this.screen = scr;
        this.account = acc;
    }

    public JPanel draw() {
        this.accountManagement = new JPanel();
        JLabel titleText = new JLabel("Welcome to account management");
        JButton backToProfileButton = new JButton("Back");

        this.accountManagement.add(titleText);
        this.accountManagement.add(backToProfileButton);

        backToProfileButton.addActionListener(e -> {
            this.accountManagement.setVisible(false);
            screen.navToProfile(this.account);
        });
        
        return this.accountManagement;
    }

}