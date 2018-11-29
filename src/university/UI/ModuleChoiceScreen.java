package university.UI;

import university.Degree;
import university.Module;
import university.ScreenManager;
import university.Student;
import university.StudentStatus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class ModuleChoiceScreen extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel moduleScreen;
    private ScreenManager screen;
    private StudentManagementScreen studentScreen;
    private Student student;
    private List<JCheckBox> coreCheckBoxes = new ArrayList<>();
    private List<JCheckBox> optionalCheckBoxes = new ArrayList<>();
    private ArrayList<Module> chosenModules = new ArrayList<>();

    public ModuleChoiceScreen(ScreenManager scr, StudentManagementScreen stuScreen, Student stu) {
        this.screen = scr;
        this.student = stu;
        this.studentScreen = stuScreen;
        initComponents();
        this.initListeners();
    }

    private void initListeners() {
        submitBtn.addActionListener(e -> {

            for (JCheckBox box : coreCheckBoxes) {
                if (box.isSelected()) {
                    try {
                        chosenModules.add(Module.getModule(box.getText().substring(0, 7)));
                        System.out.println(box.getText().substring(0, 7));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            for (JCheckBox box : optionalCheckBoxes) {
                if (box.isSelected()) {
                    try {
                        chosenModules.add(Module.getModule(box.getText().substring(0, 7)));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }

            boolean degreeType = false;
            if (this.student.getDegree().getType().equals("MSc")) {
                degreeType = true;
            }

            if (Module.checkCredits(chosenModules, degreeType)) {
                try {
                    this.student.setModuleChoices(chosenModules);
                    StudentStatus status = this.student.getStudentStatus();
                    status.setRegistered(true);
                    this.moduleScreen.setVisible(false);
                    this.studentScreen.draw();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                if (degreeType) {
                    JOptionPane.showMessageDialog(null, "Please ensure 180 credits are selected");
                } else {
                    JOptionPane.showMessageDialog(null, "Please ensure 120 credits are selected");
                }
            }
            chosenModules = new ArrayList<>();
            JOptionPane.showMessageDialog(null, "Successfully assigned modules");
        });

        backToProfileBtn.addActionListener(e -> {
            try {
                this.studentScreen.draw();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            this.moduleScreen.setVisible(false);
        });
    }

    public void draw() {
        this.moduleScreen = new JPanel();
        this.moduleScreen.setBackground(new Color(70, 70, 70));
        this.moduleScreen.setLayout(null);

        this.moduleScreen.add(degreeTxt);
        this.moduleScreen.add(backToProfileBtn);
        this.moduleScreen.add(submitBtn);
        this.moduleScreen.add(coreScrollPane);
        this.moduleScreen.add(optionalScollPane);
        this.moduleScreen.add(coreTxt);
        this.moduleScreen.add(optionalTxt);
        this.moduleScreen.add(promptTxt);

        JPanel corePanel = new JPanel();
        JPanel optionalPanel = new JPanel();
        coreScrollPane.add(corePanel);
        optionalScollPane.add(optionalPanel);

        try {
            if (this.student.getLevel() != "P") {

                ArrayList<Module> coreModules = Degree.getCoreModules(this.student.getDegree(),
                        Integer.parseInt(this.student.getLevel()));
                System.out.println(coreModules);
                ArrayList<Module> optionalModules = Degree.getOptionalModules(this.student.getDegree(),
                        Integer.parseInt(this.student.getLevel()));
                System.out.println(optionalModules);

                for (Module module : coreModules) {
                    String moduleCode = module.getCode();
                    String moduleName = module.getName();
                    int moduleCredits = module.getCredits();
                    JCheckBox box = new JCheckBox(moduleCode + " " + moduleName + " " + moduleCredits + " credits");
                    coreCheckBoxes.add(box);
                    box.setSelected(true);
                    box.setEnabled(false);
                    corePanel.add(box);
                }

                coreScrollPane.setViewportView(corePanel);
                Degree deg = this.student.getDegree();
                corePanel.setLayout(new BoxLayout(corePanel, BoxLayout.Y_AXIS));
                coreModules = Degree.getCoreModules(deg,
                        Integer.parseInt(this.student.getLevel()));

                if (deg.getType().equals("MSc")) {
                    promptTxt.setText("Please choose 180 credits");
                }

                for (Module module : optionalModules) {
                    String moduleCode = module.getCode();
                    String moduleName = module.getName();
                    int moduleCredits = module.getCredits();
                    JCheckBox box = new JCheckBox(moduleCode + " " + moduleName + " " + moduleCredits + " credits");
                    optionalCheckBoxes.add(box);
                    optionalPanel.add(box);
                }

                coreScrollPane.setViewportView(corePanel);
                corePanel.setLayout(new BoxLayout(corePanel, BoxLayout.Y_AXIS));
                optionalScollPane.setViewportView(optionalPanel);
                optionalPanel.setLayout(new BoxLayout(optionalPanel, BoxLayout.Y_AXIS));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }

        this.screen.frame.add(this.moduleScreen);
    }

    private void initComponents() {
        degreeTxt = new JLabel();
        backToProfileBtn = new JButton();
        submitBtn = new JButton();
        coreScrollPane = new JScrollPane();
        corePanel = new JPanel();
        optionalScollPane = new JScrollPane();
        optionalPanel = new JPanel();
        coreTxt = new JLabel();
        optionalTxt = new JLabel();
        promptTxt = new JLabel();

        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
                new javax.swing.border.EmptyBorder(0, 0, 0, 0), "JFormDesigner Evaluation",
                javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM,
                new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName()))
                    throw new RuntimeException();
            }
        });

        setLayout(null);

        degreeTxt.setText("Module Choice");
        degreeTxt.setFont(degreeTxt.getFont().deriveFont(degreeTxt.getFont().getSize() + 10f));
        degreeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        degreeTxt.setForeground(Color.white);
        add(degreeTxt);
        degreeTxt.setBounds(347, 35, 305, 31);

        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(415, 500, 170, 50);

        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(415, 465, 170, 30);

        // ======== coreScrollPane ========
        {

            // ======== corePanel ========
            {
                corePanel.setLayout(null);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < corePanel.getComponentCount(); i++) {
                        Rectangle bounds = corePanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = corePanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    corePanel.setMinimumSize(preferredSize);
                    corePanel.setPreferredSize(preferredSize);
                }
            }
            coreScrollPane.setViewportView(corePanel);
        }
        add(coreScrollPane);
        coreScrollPane.setBounds(275, 130, 450, 140);

        // ======== optionalScollPane ========
        {

            // ======== optionalPanel ========
            {
                optionalPanel.setLayout(null);

                { // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < optionalPanel.getComponentCount(); i++) {
                        Rectangle bounds = optionalPanel.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = optionalPanel.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    optionalPanel.setMinimumSize(preferredSize);
                    optionalPanel.setPreferredSize(preferredSize);
                }
            }
            optionalScollPane.setViewportView(optionalPanel);
        }
        add(optionalScollPane);
        optionalScollPane.setBounds(275, 300, 450, 155);

        // ---- coreTxt ----
        coreTxt.setText("Core Modules:");
        coreTxt.setForeground(new Color(225, 255, 255));
        add(coreTxt);
        coreTxt.setBounds(275, 105, 285, coreTxt.getPreferredSize().height);

        // ---- optionalTxt ----
        optionalTxt.setText("Optional Modules:");
        optionalTxt.setForeground(Color.white);
        add(optionalTxt);
        optionalTxt.setBounds(275, 280, 285, 16);

        // ---- promptTxt ----
        promptTxt.setText("Please choose 120 credits");
        promptTxt.setFont(promptTxt.getFont().deriveFont(promptTxt.getFont().getSize() + 2f));
        promptTxt.setHorizontalAlignment(SwingConstants.CENTER);
        promptTxt.setForeground(Color.white);
        add(promptTxt);
        promptTxt.setBounds(362, 65, 275, 45);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
    }

    private JLabel degreeTxt;
    private JButton backToProfileBtn;
    private JButton submitBtn;
    private JScrollPane coreScrollPane;
    private JPanel corePanel;
    private JScrollPane optionalScollPane;
    private JPanel optionalPanel;
    private JLabel coreTxt;
    private JLabel optionalTxt;
    private JLabel promptTxt;

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // ActionPerformed
    }
}
