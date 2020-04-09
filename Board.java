
import java.util.ArrayList;

public class Board {
    private final int rows = 30, columns = 30;
    Player player;
    ArrayList<Obstacle> obstacles;
    ArrayList<Item> items;
    ArrayList<Enemy> enemies;
    ArrayList<Item> inventory;

    public Board() {
        this.player = new Player();
        this.obstacles = new ArrayList<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.inventory = new ArrayList<>();
        generateObstacles();
        generateItems();
        generateEnemies();
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

        for (Enemy enemy : enemies) {
            Coordinates sign = enemy.getEnemy();
            output[sign.getX()][sign.getY()] = enemy.getSign();
        }
        
        for (String[] row : output) {
            for (String square : row) {
                if(square == null) {
                    print += " .";
                    continue;
                }
                print+=square;
            }
            print+="\n";
        }

        System.out.println(print);
        // System.out.println(this.player.getPoints());
        for (Enemy enemy : enemies) {
            System.out.println(enemy.getHealth());
        }
        System.out.println(inventory);

    }

    public Player getPlayer() {
        return this.player;
    }

    private void generateObstacles() {
        Obstacle wall1 = new Obstacle(new Coordinates(0,0), 30, 1, Emote.WALL1.getEmote());
        Obstacle wall2 = new Obstacle(new Coordinates(this.rows -1,0),30,1, Emote.WALL1.getEmote());
        Obstacle wall3 = new Obstacle(new Coordinates(0,0),1,30, Emote.WALL2.getEmote());
        Obstacle wall4 = new Obstacle(new Coordinates(0,this.columns-1),1,30, Emote.WALL2.getEmote());
        Obstacle wall5 = new Obstacle(new Coordinates(22,22), 1, 7, Emote.WALL4.getEmote());
        Obstacle wall6 = new Obstacle(new Coordinates(22,23), 2, 1, Emote.WALL3.getEmote());
        Obstacle lava = new Lava(new Coordinates(22,21), 1, 7, Emote.LAVA.getEmote());
        Obstacle lava1 = new Lava(new Coordinates(21,21), 6, 1, Emote.LAVA.getEmote());
        Obstacle door = new Door(new Coordinates(21,27), 2, 1, Emote.DOORS.getEmote());
        this.obstacles.add(wall1);
        this.obstacles.add(wall2);
        this.obstacles.add(wall3);
        this.obstacles.add(wall4);
        this.obstacles.add(lava);
        this.obstacles.add(wall5);
        this.obstacles.add(wall6);
        this.obstacles.add(lava1);
        this.obstacles.add(door);
    }

    private void generateItems(){
        Item candy = new Candy(new Coordinates(20, 20), Emote.CANDY.getEmote());
        Item candy1 = new Candy(new Coordinates(5, 8), Emote.CANDY.getEmote());
        Item key = new Key(new Coordinates(6, 6), Emote.KEY.getEmote());
        this.items.add(candy);
        this.items.add(candy1);
        this.items.add(key);
    }

    private void generateEnemies() {
        Enemy ghost = new Ghost(new Coordinates(10, 17), Emote.GHOST.getEmote(), 20, 5);
        Enemy vampire = new Vampire(new Coordinates(20, 7), Emote.MANVAMPIRE.getEmote(), 20, 5);
        Enemy spider = new Spider(new Coordinates(16, 4), Emote.SPIDER.getEmote(), 20, 5);
        Enemy zombie = new Zombie(new Coordinates(1, 23), Emote.WOMANZOMBIE.getEmote(), 20, 5);
        this.enemies.add(ghost);
        this.enemies.add(vampire);
        this.enemies.add(spider);
        this.enemies.add(zombie);
    }

    public ArrayList<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public ArrayList<Item> getItems() {
        return this.items;
    }

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    public boolean canPlayerMove(Coordinates coord) {
        int x = player.getPosition().getX() + coord.getX();
        int y = player.getPosition().getY() + coord.getY();

        for (Obstacle obstacle : obstacles) {
            int width = obstacle.getWidth();
            int height = obstacle.getHeight();
            Coordinates pivot = obstacle.getPivot();
            // Item key = inventory.get(0);

           if (isCoordinatesInRange(x, y, pivot, height, width)) {
               if(obstacle instanceof Lava) {
                   this.player.setHealth(-10);
                }
            
                if(obstacle instanceof Door && !inventory.isEmpty()) {
                    return true;
                 }
               return false;
            
            }
        }
        for (Item item : items) {
            Coordinates sign = item.getItem();
            
           if (isPlayerOnItemOrEnemy(x, y, sign)) {
               if(item instanceof Candy) {
                    this.player.setPoints(10);
                    items.remove(item);
                    System.out.println(this.player.getPoints());
                }
                if(item instanceof Key) {
                    this.inventory.add(item);
                    items.remove(item);
                }
               return true;
            }
        }

        for (Enemy enemy : enemies) {
            Coordinates sign = enemy.getEnemy();
            
            if (isPlayerOnItemOrEnemy(x, y, sign)) {
                if(enemy instanceof Spider) {
                    interactionWithEnemy(enemy);
                }
                if(enemy instanceof Vampire) {
                    interactionWithEnemy(enemy);
                }
                if(enemy instanceof Ghost) {
                    interactionWithEnemy(enemy);
                }
                if(enemy instanceof Zombie) {
                    interactionWithEnemy(enemy);
                }
                return false;
            }
        }
        return true;
    }

    // public boolean isEnemy(Coordinates coord) {
    //     int x = player.getPosition().getX() + coord.getX();
    //     int y = player.getPosition().getY() + coord.getY();
    //     return true;
    // }

    private boolean isCoordinatesInRange(int x, int y, Coordinates pivot, int height, int width) {
        return x >= pivot.getX()
            && x < pivot.getX()+height 
            && y >= pivot.getY() 
            && y < pivot.getY()+width;
    }

    private boolean isPlayerOnItemOrEnemy(int x, int y, Coordinates sign){
        return sign.getX() == x
                && sign.getY() == y;
    }

    public void interactionWithEnemy(Enemy enemy) {
        this.player.setHealth(-enemy.getAttack());
        enemy.setHealth(-player.getAttack());
        System.out.println(this.player.getHealth());
        System.out.println(enemy.getHealth());
            if(enemy.getHealth() == 0)
                enemies.remove(enemy);
    }

}