
import javax.swing.JFrame;
import javax.swing.JTextField;

public class App {
    public static void main(String[] args) {
        JTextField textField = new JTextField();
        Player player = new Player();
        textField.addKeyListener(new Game(player));
        JFrame jframe = new JFrame();

        jframe.add(textField);
        jframe.setSize(100, 100);
        jframe.setVisible(true);
    }
}