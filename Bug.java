
public class Bug extends Item{

    public Bug(String itemLook) {
        super(itemLook);
    }

    @Override
    public void changePoints(Player player) {
        player.points -= 2;
    }

}