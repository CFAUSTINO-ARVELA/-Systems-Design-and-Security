import javax.swing.*; // needs to be changed to not *

class ScreenManager {

    protected int width;
    protected int height;
    protected JPanel panel;
    private JFrame frame;
    private LoginScreen loginScreen;
    private ProfileScreen profileScreen;
    private AdminScreen adminScreen;

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

    public void navToProfile(String profileType) {

        this.loginScreen.destroy();

        this.profileScreen = new ProfileScreen(profileType, this);
        this.frame.add(this.profileScreen.draw());
    }

    public void navToLogin() {
        this.frame.add(this.loginScreen.draw());
    }

    public void navToAdminScreen() {
        this.profileScreen.destroy();
        this.adminScreen = new AdminScreen(this);
        this.frame.add(this.adminScreen.draw());
    }
}