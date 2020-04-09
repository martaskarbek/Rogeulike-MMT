
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int rows = 30, columns = 30;
    Player player;
    ArrayList<Obstacle> obstacles;
    ArrayList<Item> items;
    // ArrayList<Enemy> enemy;

    public Board() {
        this.player = new Player();
        this.obstacles = new ArrayList<>();
        this.items = new ArrayList<>();
        generateObstacles();
        generateItems();
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

            for(int i = pivot.getX(); i<pivot.getX()+height; i++) {
                for(int j = pivot.getY(); j< pivot.getY()+width; j++) {
                    output[i][j] = obstacle.getSymbol();
                }
            }
        }

        for (Item item : items) {
            Coordinates sign = item.getItem();
            output[sign.getX()][sign.getY()] = item.getSign();
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
        Obstacle obstacle1 = new Obstacle(new Coordinates(0,0), 30, 1, "##"); //top bound
        Obstacle obstacle2 = new Obstacle(new Coordinates(this.rows -1,0),30,1, "##"); // bottom bound
        Obstacle obstacle3 = new Lava(new Coordinates(8,8),2,2, "\ud83d\udd25\ud83d\udd25");
        Obstacle obstacle4 = new Obstacle(new Coordinates(0,0),1,30, "#");
        Obstacle obstacle5 = new Obstacle(new Coordinates(0,this.columns-1),1,30, "#");

        this.obstacles.add(obstacle1);
        this.obstacles.add(obstacle2);
        this.obstacles.add(obstacle3);
        this.obstacles.add(obstacle4);
        this.obstacles.add(obstacle5);
    }

    private void generateItems(){
        Candy candy = new Candy(new Coordinates(20, 20), Emote.CANDY.getemote());
        Candy candy1 = new Candy(new Coordinates(5, 8), Emote.CANDY.getemote());
        this.items.add(candy);
        this.items.add(candy1);
    }

    public ArrayList<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public boolean canPlayerMove(Coordinates coord) {
        int x = player.getPosition().getX() + coord.getX();
        int y = player.getPosition().getY() + coord.getY();

        for (Obstacle obstacle : obstacles) {
            int width = obstacle.getWidth();
            int height = obstacle.getHeight();
            Coordinates pivot = obstacle.getPivot();
            
           if (isCoordinatesInRange(x, y, pivot, height, width)) {
               if(obstacle instanceof Lava) {
                   this.player.setPoints(-10);
                   System.out.println(this.player.getPoints());
               }
               return false;
           }
        }

        for (Item item : items) {
            Coordinates sign = item.getItem();
            
           if (isPlayerOnItem(x, y, sign)) {
               if(item instanceof Candy) {
                   this.player.setPoints(10);
                   items.remove(item);
                   System.out.println(this.player.getPoints());
               }
               return false;
           }
        }

        return true;
    }

    private boolean isCoordinatesInRange(int x, int y, Coordinates pivot, int height, int width) {
        return x >= pivot.getX()
            && x < pivot.getX()+height 
            && y >= pivot.getY() 
            && y < pivot.getY()+width;
    }

    private boolean isPlayerOnItem(int x, int y, Coordinates sign){
        return sign.getX() == x
                && sign.getY() == y;
    }
}