import java.util.ArrayList;

public class Sword extends Item {

    public Sword(Coordinates item, String sign){
        super(item, sign);
    }

    public void setStats(Player player, ArrayList<Item> items, ArrayList<Item> inventory) {
        player.setAttack(5);
        items.remove(item);
    }
}