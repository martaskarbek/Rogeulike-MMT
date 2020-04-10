import java.util.ArrayList;

public class Key extends Item {

    public Key(Coordinates item, String sign){
        super(item, sign);
    }

    public void setStats(Player player, ArrayList<Item> items, ArrayList<Item> inventory) {
        inventory.add(this);
        items.remove(this);
    }
}