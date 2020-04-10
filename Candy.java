import java.util.ArrayList;

public class Candy extends Item {

    public Candy(Coordinates item, String sign){
        super(item, sign);
    }

    public void setStats(Player player, ArrayList<Item> items, ArrayList<Item> inventory) {
        player.setPoints(10);
        items.remove(this);
    }
}