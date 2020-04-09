public abstract class Item {

    Coordinates item;
    String sign;

    public Item(Coordinates item, String sign) {
        this.item = item;
        this.sign = sign;
    }

    // public int getX() {
    //     return this.x;
    // }

    // public int getY() {
    //     return this.y;
    // }

    public Coordinates getItem(){
        return this.item;
    }

    public String getSign() {
        return this.sign;
    }
}