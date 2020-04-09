
import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final int rows = 30, columns = 30;
    Player player;
    ArrayList<Obstacle> obstacles;

    // ArrayList<Item> items;
    // ArrayList<Enemy> enemy;

    public Board() {
        this.player = new Player();
        this.obstacles = new ArrayList<>();
        generateObstacles();
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

    // public void RandomIntGenerator(){
        
    //     Random random = new Random();
    //     this.x = random.nextInt(10);
    //     this.y = random.nextInt(10);            
    //     }

    private void generateObstacles() {
        // public final String fire = "\ud83d\udd25"
        // Emoticons.fire
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        Obstacle obstacle1 = new Obstacle(new Coordinates(0,0), 30, 1, "##"); //top bound
        Obstacle obstacle2 = new Obstacle(new Coordinates(this.rows -1,0),30,1, "##"); // bottom bound
        Obstacle obstacle3 = new Lava(new Coordinates(7,7),2,2, "\ud83d\udd25\ud83d\udd25");
        Obstacle obstacle4 = new Obstacle(new Coordinates(0,0),1,30, "#");
        Obstacle obstacle5 = new Obstacle(new Coordinates(0,this.columns-1),1,30, "#");
        Obstacle obstacle6 = new Lava(new Coordinates(2, 2), 3, 3, "\ud83d\udd25\ud83d\udd25");

        this.obstacles.add(obstacle1);
        this.obstacles.add(obstacle2);
        this.obstacles.add(obstacle3);
        this.obstacles.add(obstacle4);
        this.obstacles.add(obstacle5);
        this.obstacles.add(obstacle6);

    }

    public ArrayList<Obstacle> getObstacles() {
        return this.obstacles;
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
                   this.player.setHealth(-20);
                   System.out.println(this.player.getHealth());
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
}