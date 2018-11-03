import javax.swing.*;
import java.awt.*;

class ProfileScreen {

    // protected string accountType;
    private JPanel profileScreen;

    ProfileScreen() {
        // super();
        // this.accountType = accType;
    }

    public JPanel draw() {
        this.profileScreen = new JPanel();
        this.profileScreen.setBackground(new Color(250, 100, 250));
        JLabel profileText = new JLabel("Profile");
        this.profileScreen.add(profileText);

        return this.profileScreen;
    }

    public void addComponents() {

    }
}