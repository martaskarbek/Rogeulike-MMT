
import javax.swing.JFrame;
import javax.swing.JTextField;

public class App {
    public static void startGame() {
        Player player = new Player();
        JTextField textField = new JTextField();
        JFrame jframe = new JFrame();
        Input input = new Input();
        jframe.add(textField);
        jframe.setSize(100, 100);
        jframe.setVisible(true);
        textField.addKeyListener(new Game(player));
        final String nextLevel = input.getStrInput("press n to play next level");
        if (nextLevel.equalsIgnoreCase("n")) {
            textField.addKeyListener(new Game3(player));}
        
    }

    public static void main(String[] args) {
        startGame();
    }
}