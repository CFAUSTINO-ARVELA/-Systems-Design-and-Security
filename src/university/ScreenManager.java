package university;

import javax.swing.*; // needs to be changed to not *

import university.UI.LoginScreen;
import university.UI.ProfileScreen;

public class ScreenManager {

    protected int width;
    protected int height;
    protected JPanel panel;
    public JFrame frame;
    private LoginScreen loginScreen;


    public ScreenManager(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void createWindow() {
        this.frame = new JFrame("University Software");
        this.loginScreen = new LoginScreen(this);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(this.width, this.height);

        this.navToLogin();
        this.frame.setVisible(true);
    }

    public void navToLogin() {
        this.loginScreen.draw();
    }
}