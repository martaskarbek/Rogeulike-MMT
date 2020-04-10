
import javax.swing.JFrame;
import javax.swing.JTextField;

public class App {
    public void startGame() {
        JTextField textField = new JTextField();

        textField.addKeyListener(new Game());
        JFrame jframe = new JFrame();

        jframe.add(textField);
        jframe.setSize(100, 100);
        jframe.setVisible(true);
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.startGame();
    }
}