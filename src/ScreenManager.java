import javax.swing.*; // needs to be changed to not *

class ScreenManager {

    protected int width;
    protected int height;
    protected JPanel panel;
    private JFrame frame;
    private LoginScreen loginScreen;
    private ProfileScreen profileScreen;
    private AccountManagementScreen accountScreen;
    private TeachingManagementScreen teachingScreen;
    private StudentStatus studentStatus;

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

    public void navToProfile(Account account) {
        this.profileScreen = new ProfileScreen(account, this);
        this.frame.add(this.profileScreen.draw());
    }

    public void navToLogin() {
        // this.frame.add(this.loginScreen.draw());
        this.frame.add(this.loginScreen.draw());
    }

    public void navToAccountManagementScreen(Account account) {
        this.accountScreen = new AccountManagementScreen(this, account);
        this.frame.add(this.accountScreen.draw());
    }

    public void navToTeachingManagementScreen(Account account) {
        this.teachingScreen = new TeachingManagementScreen(this, account);
        this.frame.add(this.teachingScreen.draw());
    }

    public void navToStudentStatus(Account loggedInAcc, Account stuAcc) {
        this.studentStatus = new StudentStatus(loggedInAcc, this);
        this.frame.add(this.studentStatus.draw());
    }
}