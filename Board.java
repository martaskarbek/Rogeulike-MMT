public class Board {
    private Square[][] gameBoard;

    public Board() {
        int rows = 30, columns = 30;
        gameBoard = new Square[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                gameBoard[row][column] = new Square();
            }
        }
    }

    public void printBoard() {
        for (Square[] row : gameBoard) {
            for (Square square : row) {
                System.out.print(square);
            }
            System.out.println();
        }
        
    }

    public Square[][] getGameBoard() {
        return gameBoard;
    }
}