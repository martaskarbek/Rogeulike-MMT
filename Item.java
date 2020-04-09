public class Item {
    Coordinates pivot;
    int width;
    int height;
    String sign;

    public Item(Coordinates pivot, int width, int height, String sign) {
        this.width = width;
        this.height = height;
        this.pivot = pivot;
        this.sign = sign;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Coordinates getPivot(){
        return this.pivot;
    }

    public String getSymbol() {
        return this.sign;
    }
}