import java.util.ArrayList;;

public class Board2 {
    private final int rows = 30, columns = 30;
    Player player;
    ArrayList<Obstacle> obstacles;
    ArrayList<Enemy> enemies;
    ArrayList<Item> inventory;
    ArrayList<Item> items;

    public Board2() {
        this.player = new Player();
        this.obstacles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.items = new ArrayList<>();
        generateObstacles();
        generateItems();
        generateEnemies();
        
    }

    public void printBoard() {
        String[][] output = new String[rows][columns];
        String print = "";

        output[this.player.getPosition().getX()][this.player.getPosition().getY()] = this.player.getSign();
 
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

        Obstacle obstacle1 = new Obstacle(new Coordinates(0,0), 30, 1, Emote.WALL1.getEmote()); // top bound
        Obstacle obstacle2 = new Obstacle(new Coordinates(this.rows -1,0),30,1, Emote.WALL1.getEmote()); // bot bound
        Obstacle obstacle3 = new Obstacle(new Coordinates(0,0),1,30, Emote.WALL2.getEmote()); // left bound
        Obstacle obstacle4 = new Obstacle(new Coordinates(0,this.columns-1),1,30, Emote.WALL2.getEmote()); // right bound
        Obstacle obstacle5 = new Obstacle(new Coordinates(22,22), 1, 7, Emote.WALL3.getEmote()); // pion gora ognista
        Obstacle obstacle6 = new Obstacle(new Coordinates(22,22), 3, 1, Emote.WALL3.getEmote()); // pozioma gora ognista 1
        Obstacle obstacle7 = new Obstacle(new Coordinates(22,25), 3, 1, Emote.WALL3.getEmote()); // pozioma gora ognista 2
        Obstacle obstacle8 = new Obstacle(new Coordinates(22, 28),1, 1, Emote.DOORS.getEmote()); // door
        Obstacle obstacle9 = new Obstacle(new Coordinates(6, 1), 18, 1, Emote.TREE.getEmote()); // poziom drzewa 1
        Obstacle obstacle10 = new Obstacle(new Coordinates(6, 20), 9, 1, Emote.TREE.getEmote());// poziom drzewa 2
        Obstacle obstacle11 = new Obstacle(new Coordinates(8,3),20,1, Emote.TREE.getEmote());
        Obstacle obstacle12 = new Obstacle(new Coordinates(9, 3), 1, 19, Emote.TREE.getEmote());
        Obstacle obstacle13 = new Obstacle(new Coordinates(26,4), 15, 1, Emote.WALL5.getEmote());
        Obstacle obstacle14 = new Obstacle(new Coordinates(20,19), 1, 6, Emote.TREE2.getEmote());
        Obstacle obstacle15 = new Obstacle(new Coordinates(19, 20), 9, 1, Emote.TREE2.getEmote());

        this.obstacles.add(obstacle1);
        this.obstacles.add(obstacle2);
        this.obstacles.add(obstacle3);
        this.obstacles.add(obstacle4);
        this.obstacles.add(obstacle5);
        this.obstacles.add(obstacle6);
        this.obstacles.add(obstacle7);
        this.obstacles.add(obstacle8);
        this.obstacles.add(obstacle9);
        this.obstacles.add(obstacle10);
        this.obstacles.add(obstacle11);
        this.obstacles.add(obstacle12);
        this.obstacles.add(obstacle13);
        this.obstacles.add(obstacle14);
        this.obstacles.add(obstacle15);
    }

    private void generateItems(){
        Candy candy = new Candy(new Coordinates(28, 28), Emote.CANDY.getEmote());
        Key key = new Key(new Coordinates(28, 1), Emote.KEY.getEmote());

        this.items.add(candy);
        this.items.add(key);
    }

    private void generateEnemies() {
        Enemy ghost = new Ghost(new Coordinates(6, 19), Emote.GHOST.getEmote(), 20, 5);

        this.enemies.add(ghost);
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