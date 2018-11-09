import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AdminScreen implements ActionListener {

    private ScreenManager screenManager;
    private JPanel adminScreen;

    AdminScreen(ScreenManager scr) {
        this.screenManager = scr;
    }

    public JPanel draw() {
        this.adminScreen = new JPanel();
        
    }

    private void back() {
        screenManager.navToAdminScreen();
    }

    private void createAccount() {

    }

    private void editAccount() {

    }

}