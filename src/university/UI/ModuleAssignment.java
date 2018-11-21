import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 21 13:18:41 GMT 2018
 */



/**
 * @author Katie
 */
public class ModuleAssignment extends JPanel {
    public ModuleAssignment() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Katie
        degreeTxt = new JLabel();
        degreeScrollPane = new JScrollPane();
        degreePanel = new JPanel();
        moduleScrollPane = new JScrollPane();
        modulePanel = new JPanel();
        levelTxt = new JLabel();
        coreTxt = new JLabel();
        coreInput = new JComboBox();
        levelInput = new JTextField();
        submitBtn = new JButton();
        backToProfileBtn = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(null);

        //---- degreeTxt ----
        degreeTxt.setText("Module Assignment");
        degreeTxt.setFont(degreeTxt.getFont().deriveFont(degreeTxt.getFont().getSize() + 10f));
        degreeTxt.setHorizontalAlignment(SwingConstants.CENTER);
        degreeTxt.setForeground(Color.white);
        add(degreeTxt);
        degreeTxt.setBounds(347, 35, 305, 31);

        //======== degreeScrollPane ========
        {

            //======== degreePanel ========
            {
                degreePanel.setLayout(new BorderLayout());
            }
            degreeScrollPane.setViewportView(degreePanel);
        }
        add(degreeScrollPane);
        degreeScrollPane.setBounds(515, 115, 395, 230);

        //======== moduleScrollPane ========
        {

            //======== modulePanel ========
            {
                modulePanel.setLayout(new BorderLayout());
            }
            moduleScrollPane.setViewportView(modulePanel);
        }
        add(moduleScrollPane);
        moduleScrollPane.setBounds(70, 115, 395, 230);

        //---- levelTxt ----
        levelTxt.setText("Level:");
        levelTxt.setFont(levelTxt.getFont().deriveFont(levelTxt.getFont().getSize() + 2f));
        levelTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        add(levelTxt);
        levelTxt.setBounds(315, 370, 100, 35);

        //---- coreTxt ----
        coreTxt.setText("Core?");
        coreTxt.setFont(coreTxt.getFont().deriveFont(coreTxt.getFont().getSize() + 2f));
        coreTxt.setHorizontalAlignment(SwingConstants.RIGHT);
        add(coreTxt);
        coreTxt.setBounds(315, 420, 100, 35);
        add(coreInput);
        coreInput.setBounds(435, 420, 130, coreInput.getPreferredSize().height);
        add(levelInput);
        levelInput.setBounds(435, 375, 130, levelInput.getPreferredSize().height);

        //---- submitBtn ----
        submitBtn.setText("Submit");
        add(submitBtn);
        submitBtn.setBounds(415, 465, 170, 30);

        //---- backToProfileBtn ----
        backToProfileBtn.setText("Back");
        add(backToProfileBtn);
        backToProfileBtn.setBounds(415, 500, 170, 50);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Katie
    private JLabel degreeTxt;
    private JScrollPane degreeScrollPane;
    private JPanel degreePanel;
    private JScrollPane moduleScrollPane;
    private JPanel modulePanel;
    private JLabel levelTxt;
    private JLabel coreTxt;
    private JComboBox coreInput;
    private JTextField levelInput;
    private JButton submitBtn;
    private JButton backToProfileBtn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
