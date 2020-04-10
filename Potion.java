import java.util.ArrayList;

public class Potion extends Item {

    public Potion(Coordinates item, String sign){
        super(item, sign);
    }

    public void setStats(Player player, ArrayList<Item> items, ArrayList<Item> inventory) {
        player.setHealth(25);
        items.remove(this);
    }
}