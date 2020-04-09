public class Player {
    private int points;
    private int health = 200;
    Coordinates position;
    private String sign = Emote.PLAYER.getEmote();

    public Player(){
        this.position = new Coordinates(5, 5);
    }

    public void move(Coordinates coord){
        position.setX(position.getX() + coord.getX());
        position.setY(position.getY() + coord.getY());
    }

    public String getSign() {
        return this.sign;
    }

    public Coordinates getPosition() {
        return this.position;
    }

    public String toString() {
        return position.getX() + ", " + position.getY();
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health += health;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points += points;
    }
}