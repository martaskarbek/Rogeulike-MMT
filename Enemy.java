
public abstract class Enemy {
    int healthPoints;
    Coordinates pivot;
    int width;
    int height;
    String sign;

    public Enemy(Coordinates pivot, int width, int height, String sign) {
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

    public String getSign() {
        return this.sign;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }
}