
public class Tutorial extends Item {

    public Tutorial(String itemLook) {
        super(itemLook);
    }

    @Override
    public void changePoints(Player player) {
        player.points += 2;
    }

}