import javax.swing.*; // needs to be changed to not *

class ScreenManager {

    protected int width;
    protected int height;
    protected String screen;
    protected JFrame frame;
    protected JPanel panel;

    public ScreenManager(int w, int h) {
        this.screen = "login";
        this.width = w;
        this.height = h;
    }

    protected void addComponents() {
        
    }

    protected void destroy() {

    }
}