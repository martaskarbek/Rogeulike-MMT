
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int rows = 30, columns = 30;
    Player player;
    ArrayList<Obstacle> obstacles;

    // ArrayList<Item> items;
    // ArrayList<Enemy> enemy;

    public Board(String[][] bufferMap) {
        this.player = new Player();
        this.obstacles = new ArrayList<>();
        generateObstacles();
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
        String print = "";

        output[this.player.getPosition().getX()][this.player.getPosition().getY()] = this.player.getSign();
        // assign items from list
        // assign enemies for list

        for (Obstacle obstacle : obstacles) {
            int width = obstacle.getWidth();
            int height = obstacle.getHeight();
            Coordinates pivot = obstacle.getPivot();

            for(int i = pivot.getX(); i<height; i++) {
                for(int j = pivot.getY(); j<width; j++) {
                    output[i][j] = obstacle.getSymbol();
                }
            }
        }

        for (String[] row : output) {
            for (String square : row) {
                // if i, j is equal to player position, then print+=" @"
                if(square == null) {
                    print += " .";
                    continue;
                }
                print+=square;
            }
            print+="\n";
        }

        System.out.println(print);
    }

    public Player getPlayer() {
        return this.player;
    }

    private void generateObstacles() {
        Obstacle obstacle1 = new Obstacle(new Coordinates(0,0), 30, 1);

        this.obstacles.add(obstacle1);
    }

    public ArrayList<Obstacles> getObstacles() {
        return this.obstacles;
    }

    public boolean canPlayerMove(Coordinates coord) {

        return true;
    }
}