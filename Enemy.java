
public abstract class Enemy {

    int health;
    int attack;
    Coordinates enemy;
    String sign;

    public Enemy(Coordinates enemy, String sign, int health, int attack) {
        this.enemy = enemy;

        this.sign = sign;
        this.health = health;
        this.attack = attack;
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

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health += health;
    }

    public int getAttack() {
        return this.attack;
    }
}