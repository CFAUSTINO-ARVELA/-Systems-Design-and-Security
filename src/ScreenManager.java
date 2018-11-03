import javax.swing.*; // needs to be changed to not *

class ScreenManager {

    protected int width;
    protected int height;
    protected JPanel panel;
    private JFrame frame;
    private LoginScreen loginScreen;
    private ProfileScreen profileScreen;

    public ScreenManager(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void init() {
        this.frame = new JFrame("University Software");
        this.loginScreen = new LoginScreen(this.width, this.height);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(this.width, this.height);

        this.frame.add(this.loginScreen.draw());
        this.frame.setVisible(true);
    }

    public void navToProfile() {

        // this.loginScreen.destroy();
        this.profileScreen = new ProfileScreen();
        this.frame.add(this.profileScreen.draw());
    }

}