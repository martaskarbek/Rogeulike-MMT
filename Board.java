

import java.util.ArrayList;

public class Board {
    private final int rows = 30, columns = 30;
    Player player;
    ArrayList<Obstacle> obstacles;
    ArrayList<Item> items;
    ArrayList<Enemy> enemies;

    public Board(Player player) {
        this.player = player;
        this.obstacles = new ArrayList<>();
        this.items = new ArrayList<>();
        this.enemies = new ArrayList<>();
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
            int width = enemy.getWidth();
            int height = enemy.getHeight();
            Coordinates pivot = enemy.getPivot();

            for(int i = pivot.getX(); i<pivot.getX()+height; i++) {
                for(int j = pivot.getY(); j< pivot.getY()+width; j++) {
                    output[i][j] = enemy.getSign();
                }
            }
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
        // for (Enemy enemy : enemies) {
        //     System.out.println(enemy.getHealthPoints());
        // }

    }
    
    public boolean isEnemy(Coordinates coord) {
        int x = player.getPosition().getX() + coord.getX();
        int y = player.getPosition().getY() + coord.getY();

        for (Enemy enemy : enemies) {
            int width = enemy.getWidth();
            int height = enemy.getHeight();
            Coordinates pivot = enemy.getPivot();
            
            if (isCoordinatesInRange(x, y, pivot, height, width)) {
                if(enemy instanceof Spider) {
                   this.player.setPoints(-5);
                   enemy.setHealthPoints(-10);
                }
                if(enemy instanceof Vampire) {
                    this.player.setPoints(-20);
                    enemy.setHealthPoints(-10);
                }
                if(enemy instanceof Ghost) {
                    this.player.setPoints(-10);
                    enemy.setHealthPoints(-10);
                }
                if(enemy instanceof Zombie) {
                    this.player.setPoints(-15);
                    enemy.setHealthPoints(-10);
                }
                return false;
            }
        }
        return true;
    }
    
    public Player getPlayer() {
        return this.player;
    }

    private void generateObstacles() {
        Obstacle obstacle1 = new Obstacle(new Coordinates(0,0), 30, 1, "##");
        Obstacle obstacle2 = new Obstacle(new Coordinates(this.rows -1,0),30,1, "##");
        Obstacle obstacle4 = new Obstacle(new Coordinates(0,0),1,30, "#");
        Obstacle obstacle5 = new Obstacle(new Coordinates(0,this.columns-1),1,30, "#");
        Obstacle obstacle3 = new Lava(new Coordinates(8,8),2,2, "\ud83d\udd25\ud83d\udd25");
        

        this.obstacles.add(obstacle1);
        this.obstacles.add(obstacle2);
        this.obstacles.add(obstacle3);
        this.obstacles.add(obstacle4);
        this.obstacles.add(obstacle5);
    }

    private void generateItems(){
        Item candy = new Candy(new Coordinates(20, 20), Emote.CANDY.getEmote());
        Item candy1 = new Candy(new Coordinates(5, 8), Emote.CANDY.getEmote());
        this.items.add(candy);
        this.items.add(candy1);
        

    }
    private void generateEnemies() {
        Enemy ghost = new Ghost(new Coordinates(10, 17), 1, 1, Emote.GHOST.getEmote());
        Enemy vampire = new Vampire(new Coordinates(20, 7), 1 , 1, Emote.MANVAMPIRE.getEmote());
        Enemy spider = new Spider(new Coordinates(16, 4), 1, 1, Emote.SPIDER.getEmote());
        Enemy zombie = new Zombie(new Coordinates(1, 23), 1, 1, Emote.WOMANZOMBIE.getEmote());
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
               return true;
           }
        }

        for (Enemy enemy : enemies) {
            int width = enemy.getWidth();
            int height = enemy.getHeight();
            Coordinates pivot = enemy.getPivot();
            
            if (isCoordinatesInRange(x, y, pivot, height, width)) {
                if(enemy instanceof Spider) {
                   this.player.setPoints(-5);
                   enemy.setHealthPoints(-10);
                }
                if(enemy instanceof Vampire) {
                    this.player.setPoints(-20);
                    enemy.setHealthPoints(-10);
                }
                if(enemy instanceof Ghost) {
                    this.player.setPoints(-10);
                    enemy.setHealthPoints(-10);
                }
                if(enemy instanceof Zombie) {
                    this.player.setPoints(-15);
                    enemy.setHealthPoints(-10);
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

}