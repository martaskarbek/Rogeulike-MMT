
public class Obstacle {
    Coordinates pivot;
    int width;
    int height;
    String sign = "__";
    //---------- (0,0), width = 60, height = 1
    // |          (0,0), width=1, height = 30

    public Obstacle(Coordinates pivot, int width, int height) {
        this.width = width;
        this.height = height;
        this.pivot = pivot;
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
