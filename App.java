import javax.swing.JFrame;
import javax.swing.JTextField;

public class App {
    public static void main(String[] args) {
        JTextField textField = new JTextField();

        textField.addKeyListener(new Game());
        JFrame jframe = new JFrame();

        jframe.add(textField);
        jframe.setSize(100, 100);
        jframe.setVisible(true);
        
        Map2 map2 = new Map2();
        map2.file = "map2.txt";
        Board board = new Board(map2.bufferMap());
        board.printBoard();
    }
}