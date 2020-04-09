
import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int rows = 30, columns = 30;
    Player player;
    ArrayList<Obstacle> obstacles;
    ArrayList<Enemy> enemies;

    // ArrayList<Item> items;
    // ArrayList<Enemy> enemy;

    public Board() {
        this.player = new Player();
        this.obstacles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        generateObstacles();
        generateEnemies();
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

        for (Enemy enemy : enemies) {
            int width = enemy.getWidth();
            int height = enemy.getHeight();
            Coordinates pivot = enemy.getPivot();

            for(int i = pivot.getX(); i<pivot.getX()+height; i++) {
                for(int j = pivot.getY(); j< pivot.getY()+width; j++) {
                    output[i][j] = enemy.getSymbol();
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
        System.out.println(this.player.getPoints());
    }

    public Player getPlayer() {
        return this.player;
    }

    private void generateObstacles() {
        // public final String fire = "\ud83d\udd25"
        // Emoticons.fire
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

    private void generateEnemies() {
        Enemy ghost = new Ghost(new Coordinates(10, 17), 1, 1, Emote.GHOST.getemote());
        Enemy vampire = new Vampire(new Coordinates(20, 7), 1 , 1, Emote.MANVAMPIRE.getemote());
        Enemy spider = new Spider(new Coordinates(16, 4), 1, 1, Emote.SPIDER.getemote());
        Enemy zombie = new Zombie(new Coordinates(1, 23), 1, 1, Emote.WOMANZOMBIE.getemote());
        this.enemies.add(ghost);
        this.enemies.add(vampire);
        this.enemies.add(spider);
        this.enemies.add(zombie);
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
                   this.player.setPoints(-10);
                   //System.out.println(this.player.getPoints());
               }
               return false;
           }
        }
        return true;
    }

    public boolean isEnemy(Coordinates coord) {
        int x = player.getPosition().getX() + coord.getX();
        int y = player.getPosition().getY() + coord.getY();

        for (Enemy enemy : enemies) {
            int width = enemy.getWidth();
            int height = enemy.getHeight();
            Coordinates pivot = enemy.getPivot();
            
           if (isCoordinatesInRange(x, y, pivot, height, width)) {
               if(enemy instanceof Enemy) {
                   this.player.setPoints(-10);
                   //System.out.println(this.player.getPoints());
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