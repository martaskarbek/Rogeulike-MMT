public class Board {
    private final int rows = 30, columns = 30;
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

        for(int i = 0; i< rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(output[i][j] == null) {
                    System.out.print(" .");
                    continue;
                }
                System.out.print(output[i][j]);
            }
            System.out.println();
        }
    }

    public Player getPlayer() {
        return this.player;
    }
}