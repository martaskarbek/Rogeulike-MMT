public class Player {
    public int points;
    Coordinates position;
    private String sign = " @";

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

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points += points;
    }
}