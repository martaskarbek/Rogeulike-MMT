import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Game extends KeyAdapter {
    
    static Board board;
    Player player;
    private final Coordinates w = new Coordinates(-1, 0);
    private final Coordinates s = new Coordinates(1, 0);
    private final Coordinates a = new Coordinates(0, -1);
    private final Coordinates d = new Coordinates(0, 1);

    public Game() {
        mainMenu();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        char ch = event.getKeyChar();
            
        clearScreen();
        switch (ch) {
            case 'w':
                if (board.canPlayerMove(this.w)) {
                    board.getPlayer().move(this.w);
                }
                break;
            case 's':
                if (board.canPlayerMove(this.s)) {
                    board.getPlayer().move(this.s);
                }
                break;
            case 'a':
                if (board.canPlayerMove(this.a)) {
                    board.getPlayer().move(this.a);
                }
                break;
            case 'd':
                if (board.canPlayerMove(this.d)) {
                    board.getPlayer().move(this.d);
                }
                break;
        }
        board.printBoard();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void mainMenu() {
        board = new Board();
        System.out.println("1. Play the game\n2. High Score\n0. Exit");
        int option;
        option = Input.getIntInput("Enter a number to choose: ");
        clearScreen();
        System.out.println("");
        switch (option) {
            case 1:
                board.printBoard();
                break;
            case 2:
                // High Score
                break;
            case 0:
                // exitProgram();
                break;

        }
    }
}