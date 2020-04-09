
public abstract class Enemy {
    int healthPoints;
    Coordinates enemy;
    String sign;

    public Enemy(Coordinates enemy, String sign) {
        this.enemy = enemy;
        this.sign = sign;
    }

    public Coordinates getEnemy(){
        return this.enemy;
    }

    public String getSign() {
        return this.sign;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    // public void setHealthPoints(int healthPoints) {
    //     this.healthPoints += healthPoints;
    // }
}