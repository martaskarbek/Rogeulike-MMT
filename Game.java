
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Game extends KeyAdapter {
    
    Board board;
    private final Coordinates w = new Coordinates(-1, 0);
    private final Coordinates s = new Coordinates(1, 0);
    private final Coordinates a = new Coordinates(0, -1);
    private final Coordinates d = new Coordinates(0, 1);



    public Game(Player player) {
            this.board = new Board(player);
            board.printBoard();
        }


    @Override
    public void keyPressed(KeyEvent event) {
        
        char ch = event.getKeyChar();
        clearScreen();

        switch(ch) {
            case 'w':
                // System.out.println(board.canPlayerMove(this.w));
                if (board.canPlayerMove(this.w) && board.isEnemy(this.w)) {
                    board.getPlayer().move(this.w);
                }
                break;
            case 's':
                if (board.canPlayerMove(this.s) && board.isEnemy(this.s)) {
                    board.getPlayer().move(this.s);
                }
                break;
            case 'a':
                if (board.canPlayerMove(this.a) && board.isEnemy(this.a)){
                    board.getPlayer().move(this.a);
                }
                break;
            case 'd':
                if (board.canPlayerMove(this.d) && board.isEnemy(this.d)) {
                    board.getPlayer().move(this.d);
                }
                break;   
        }
        // System.out.println(board.getPlayer().toString());
        
        board.printBoard();
        // print board again
    }

    public static void clearScreen() {
        System. out. print("\033[H\033[2J");
        System. out. flush();
    }
}