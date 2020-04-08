public class Board {
    private final int rows = 30, columns = 70;
    Player player;
    // ArrayList<Item> items;
    // ArrayList<Enemy> enemy;

    public Board(String[][] bufferMap) {
        this.player = new Player();
        // int rows = 30, columns = 70;
        // gameBoard = new Square[rows][columns];
        // for (int row = 0; row < rows; row++) {
        //     for (int column = 0; column < columns; column++) {
        //         gameBoard[row][column] = new Square(bufferMap[row][column]);
        //     }
        // }
    }

    public void printBoard() {
        String[][] output = new String[rows][columns];

        output[this.player.getPosition().getX()][this.player.getPosition().getY()] = this.player.getSign();
        // assign items from list
        // assign enemies for list

        for (String[] row : output) {
            for (String square : row) {
                if(square == null) {
                    System.out.print(".");
                    continue;
                }
                System.out.print(square);
            }
            System.out.println();
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    // public Square[][] getGameBoard() {
    //     return gameBoard;
    // }
}