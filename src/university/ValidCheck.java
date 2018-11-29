package university;

import javax.swing.JTextField;

public class ValidCheck {

    public static boolean email(String input) {
        return (input.matches("[a-zA-Z0-9@.]+"));
    }

    public static boolean input(JTextField input) {
        return (input.getText().matches("[a-zA-Z0-9 ]+"));
    }
    
    public static boolean pass(String input) {
        return (input.matches("[a-zA-Z0-9 ]+"));
    }

    public static boolean grade(JTextField input) {
        try {
            int num = Integer.parseInt(input.getText());

            if (num >= 0 && num <= 100) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}