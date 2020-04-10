import java.util.ArrayList;


public abstract class Item {

    Coordinates item;
    String sign;

    public Item(Coordinates item, String sign) {
        this.item = item;
        this.sign = sign;
    }

    public Coordinates getItem(){
        return this.item;
    }

    public String getSign() {
        return this.sign;
    }

    public void setStats(Player player, ArrayList<Item> items, ArrayList<Item> inventory) {
        // todo
    }
}