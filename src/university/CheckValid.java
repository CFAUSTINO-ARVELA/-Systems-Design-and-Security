package university;

import javax.swing.JTextField;

public class CheckValid {

    public static boolean symbols(JTextField input) {
        return (!input.getText().matches("[a-zA-Z0-9]+"));
    }
}