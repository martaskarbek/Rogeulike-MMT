public class Player {
    Coordinates position;

    public Player(){
        this.position = new Coordinates(0, 0);
    }

    public void move(Coordinates coord){
        position.setX(position.getX() + coord.getX());
        position.setY(position.getY() + coord.getY());
    }
}