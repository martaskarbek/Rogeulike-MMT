
public abstract class Enemy {
    Coordinates pivot;
    String sign;

    public Enemy(Coordinates pivot, String sign) {
        this.pivot = pivot;
        this.sign = sign;
    }

    public Coordinates getPivot(){
        return this.pivot;
    }

    public String getSymbol() {
        return this.sign;
    }
}