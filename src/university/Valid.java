package university;

import javax.swing.JTextField;

public class Valid {

    public static boolean check(JTextField input) {
        return (!input.getText().matches("[a-zA-Z0-9 @.]+"));
    }
}