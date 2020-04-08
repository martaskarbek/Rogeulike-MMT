
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Game extends KeyAdapter {
    
    Map2 map2;
    Board board;
    private final Coordinates w = new Coordinates(-1, 0);
    private final Coordinates s = new Coordinates(1, 0);
    private final Coordinates a = new Coordinates(0, -1);
    private final Coordinates d = new Coordinates(0, 1);

    public Game() {
        this.map2 = new Map2();
        map2.file = "map2.txt";
        this.board = new Board(map2.bufferMap());
        board.printBoard();
    }

    @Override
    public void keyPressed(KeyEvent event) {

        char ch = event.getKeyChar();

        clearScreen();
        switch(ch) {
            case 'w':
                board.getPlayer().move(this.w);
                break;
            case 's':
                board.getPlayer().move(this.s);
                break;
            case 'a':
                board.getPlayer().move(this.a);
                break;
            case 'd':
                board.getPlayer().move(this.d);
                break;   
        }
        // System.out.println(board.getPlayer().toString());
        
        board.printBoard();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}