public class Player {
    Coordinates position;

    public Player(){
        this.position = new Coordinates(5, 5);
    }

    public void move(Coordinates coord){
        position.setX(position.getX() + coord.getX());
        position.setY(position.getY() + coord.getY());
    }
}