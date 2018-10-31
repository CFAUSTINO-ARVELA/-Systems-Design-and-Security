import javax.swing.*;

class Canvas {

    private int width;
    private int height;

    
    public Canvas(int w, int h){
        this.width = w;
        this.height = h;
    }

    public void drawCanvas() {
        JFrame frame = new JFrame("My First GUI");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(this.width,this.height);
       JButton button = new JButton("Press");
       frame.getContentPane().add(button); // Adds Button to content pane of frame
       frame.setVisible(true);
    }
}