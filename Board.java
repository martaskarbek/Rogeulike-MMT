import java.util.ArrayList;;

public class Board {
    private final int rows = 30, columns = 30;
    //private Player player;
    private ArrayList<Obstacle> obstacles;
    private  ArrayList<Enemy> enemies;
    private ArrayList<Item> inventory;
    private ArrayList<Item> items;

    public Board() {
        //this.player = new Player();
        this.obstacles = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.items = new ArrayList<>();
        generateObstacles();
        generateItems();
        generateEnemies();   
    }

    public void printBoard(Player player) {
        
        String[][] output = new String[rows][columns];
        String print = "";

        output[player.getPosition().getX()][player.getPosition().getY()] = player.getSign();
        
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
        System.out.println("Attack: " + player.getAttack());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Points: " + player.getPoints());
    }

    public Player getPlayer(Player player) {
        return player;
    }

    private void generateObstacles() {
        Obstacle wall1 = new Obstacle(new Coordinates(0,0), 30, 1, Emote.WALL1.getEmote());
        Obstacle wall2 = new Obstacle(new Coordinates(this.rows -1,0),30,1, Emote.WALL1.getEmote());
        Obstacle wall3 = new Obstacle(new Coordinates(0,0),1,30, Emote.WALL2.getEmote());
        Obstacle wall4 = new Obstacle(new Coordinates(0,this.columns-1),1,30, Emote.WALL2.getEmote());
        Obstacle wall5 = new Obstacle(new Coordinates(22,22), 1, 7, Emote.WALL4.getEmote());
        Obstacle wall6 = new Obstacle(new Coordinates(22,23), 2, 1, Emote.WALL3.getEmote());
        Obstacle wall7 = new Obstacle(new Coordinates(21, 1), 5, 1, Emote.WALL1.getEmote());
        Obstacle wall8 = new Obstacle(new Coordinates(23, 6), 1, 6, Emote.WALL1.getEmote());
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
        this.obstacles.add(wall7);
        this.obstacles.add(wall8);
    }

    private void generateItems(){
        Item candy = new Candy(new Coordinates(20, 20), Emote.CANDY.getEmote());
        Item candy1 = new Candy(new Coordinates(5, 8), Emote.CANDY.getEmote());
        Item pumpkin = new Candy(new Coordinates(26, 26), Emote.PUMPKIN.getEmote());
        Item key = new Key(new Coordinates(26, 3), Emote.KEY.getEmote());
        Item sword = new Sword(new Coordinates(15, 15), Emote.SWORD.getEmote());
        Item potion = new Potion(new Coordinates(18, 23), Emote.POTION.getEmote());
        this.items.add(candy);
        this.items.add(candy1);
        this.items.add(pumpkin);
        this.items.add(key);
        this.items.add(sword);
        this.items.add(potion); 
    }

    private void generateEnemies() {
        Enemy ghost = new Ghost(new Coordinates(10, 17), Emote.GHOST.getEmote(), 20, 5);
        Enemy vampire = new Vampire(new Coordinates(22, 5), Emote.MANVAMPIRE.getEmote(), 20, 15);
        Enemy spider = new Spider(new Coordinates(16, 4), Emote.SPIDER.getEmote(), 20, 5);
        Enemy zombie = new Zombie(new Coordinates(1, 23), Emote.WOMANZOMBIE.getEmote(), 20, 10);
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

    public boolean canPlayerMove(Coordinates coord, Player player) {
        int x = player.getPosition().getX() + coord.getX();
        int y = player.getPosition().getY() + coord.getY();

        for (Obstacle obstacle : obstacles) {
            int width = obstacle.getWidth();
            int height = obstacle.getHeight();
            Coordinates pivot = obstacle.getPivot();

           if (isCoordinatesInRange(x, y, pivot, height, width)) {
               if(obstacle instanceof Lava) {
                   player.setHealth(-10);
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
                    player.setPoints(10);
                    items.remove(item);
                }
                if(item instanceof Key) {
                    this.inventory.add(item);
                    items.remove(item);
                }
                if(item instanceof Sword) {
                    player.setAttack(5);
                    items.remove(item);
                }
                if(item instanceof Potion) {
                    player.setHealth(25);
                    items.remove(item);
                }
                if(item instanceof Pumpkin) {
                    Input input = new Input();
                    System.out.println("DUPA");
                    final int answear = input.getIntInput("next level? /1");
                        switch (answear) {
                            case 1:
                                Board2 board2 = new Board2();
                                board2.printBoard2(player);
                                break;
                            default:
                            System.out.println("See you soon!");
                                break;
                        }

                }
               return true;
            }
        }

        for (Enemy enemy : enemies) {
            Coordinates signn = enemy.getEnemy();
            
            if (isPlayerOnItemOrEnemy(x, y, signn)) {
                if(enemy instanceof Spider) {
                    interactionWithEnemy(enemy, player);
                }
                if(enemy instanceof Vampire) {
                    interactionWithEnemy(enemy, player);
                }
                if(enemy instanceof Ghost) {
                    interactionWithEnemy(enemy, player);
                }
                if(enemy instanceof Zombie) {
                    interactionWithEnemy(enemy, player);

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

    public void interactionWithEnemy(Enemy enemy, Player player) {
        player.setHealth(-enemy.getAttack());
        enemy.setHealth(-player.getAttack());
            if(enemy.getHealth() == 0)
                enemies.remove(enemy);
    }
}