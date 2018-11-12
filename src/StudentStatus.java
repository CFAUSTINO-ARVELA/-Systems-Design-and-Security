import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.*;


class StudentStatus implements ActionListener {

    // Who is logged in, eg a register or student
    private Account loggedInAccount;
    private JPanel studentStatusScreen;
    private ScreenManager screen;

    private String username;
    private Date startDate;
    private Date endDate;
    private int levelStudies;
    private Semester periodStudies;
    private int grade;

    StudentStatus(Account loggedInAcc, ScreenManager screen) {
        this.loggedInAccount = loggedInAcc;
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
        
        switch (loggedInAccount.getClearance()) {
        case STUDENT:
            this.setStudentStatus(loggedInAccount.getUsername());
            break;
        case TEACHER:
            this.drawSearch();
            break;
        }

        return this.studentStatusScreen;
    }

    public void drawForm() {
        JLabel nameTxt = new JLabel("Name: " + this.username);
        JLabel startDateTxt = new JLabel("Start Date: " + this.startDate);
        JLabel endDateTxt = new JLabel("End Date: " + this.endDate);
        JLabel levelStudiesTxt = new JLabel("Level Studies: " + this.levelStudies);
        JLabel periodStudiesTxt = new JLabel("Period Studies: " + this.periodStudies);

        this.studentStatusScreen.add(nameTxt);
        this.studentStatusScreen.add(startDateTxt);
        this.studentStatusScreen.add(endDateTxt);
        this.studentStatusScreen.add(levelStudiesTxt);
        this.studentStatusScreen.add(periodStudiesTxt);
    }

    public void drawSearch() {
        JTextField searchbox = new JTextField();
        JButton searchBtn = new JButton("Search");

        searchBtn.addActionListener(e -> {
            this.setStudentStatus(searchbox.toText());
        });
    }

    public void setStudentStatus(String user) {
        // query the student status table/cols here and set all the data

        this.username = "aca16klw";
        // ...

    }

    private void updateLevelStudies() {

    }

    private void updatePeriodStudies() {

    }

    private int getGradeMean() {
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}
