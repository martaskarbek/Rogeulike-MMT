
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game3 extends KeyAdapter{
    Board3 board3;
    private final Coordinates w = new Coordinates(-1, 0);
    private final Coordinates s = new Coordinates(1, 0);
    private final Coordinates a = new Coordinates(0, -1);
    private final Coordinates d = new Coordinates(0, 1);



    public Game3(Player player) {
            this.board3 = new Board3(player);
            board3.printBoard3();
        }


    @Override
    public void keyPressed(KeyEvent event) {
        
        char ch = event.getKeyChar();
        clearScreen();

        switch(ch) {
            case 'w':
                // System.out.println(board.canPlayerMove(this.w));
                if (board3.canPlayerMove(this.w) && board3.isEnemy(this.w)) {
                    board3.getPlayer().move(this.w);
                }
                break;
            case 's':
                if (board3.canPlayerMove(this.s) && board3.isEnemy(this.s)) {
                    board3.getPlayer().move(this.s);
                }
                break;
            case 'a':
                if (board3.canPlayerMove(this.a) && board3.isEnemy(this.a)){
                    board3.getPlayer().move(this.a);
                }
                break;
            case 'd':
                if (board3.canPlayerMove(this.d) && board3.isEnemy(this.d)) {
                    board3.getPlayer().move(this.d);
                }
                break;   
        }
        // System.out.println(board.getPlayer().toString());
        
        board3.printBoard3();
        // print board again
    }

    public static void clearScreen() {
        System. out. print("\033[H\033[2J");
        System. out. flush();
    }
}
