
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Game extends KeyAdapter {
    
    static Board board;
    Board2 board2;
    Player player = new Player();
    private final Coordinates w = new Coordinates(-1, 0);
    private final Coordinates s = new Coordinates(1, 0);
    private final Coordinates a = new Coordinates(0, -1);
    private final Coordinates d = new Coordinates(0, 1);

    public Game() {
        // this.board = new Board();
        // board.printBoard();
        mainMenu();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        char ch = event.getKeyChar();
            
        clearScreen();
        switch (ch) {
            case 'w':
                // System.out.println(board.canPlayerMove(this.w));
                if (board.canPlayerMove(this.w, player)) {
                    board.getPlayer(player).move(this.w);
                }
                break;
            case 's':
                if (board.canPlayerMove(this.s, player)) {
                    board.getPlayer(player).move(this.s);
                }
                break;
            case 'a':
                if (board.canPlayerMove(this.a, player)) {
                    board.getPlayer(player).move(this.a);
                }
                break;
            case 'd':
                if (board.canPlayerMove(this.d, player)) {
                    board.getPlayer(player).move(this.d);
                }
                break;
        }
        // System.out.println(board.getPlayer().toString());

        board.printBoard(player);
        if (player.getPoints() >= 20) {
            clearScreen();
            board2.printBoard2(player);
            nextLevel();

            
        }
        // print board again
    }

    public void nextLevel() {
        board2.printBoard2(player);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void mainMenu() {
        Input input = new Input();
        board = new Board();
        board2 = new Board2();
        System.out.println("1. Play the game\n2. High Score\n0. Exit");
        final int option = input.getIntInput("Enter a number to choose: ");
        clearScreen();
        System.out.println("");
        switch (option) {
            case 1:
                board.printBoard(player);
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