
public class VirusDisc extends Item {

    public VirusDisc(String itemLook) {
        super(itemLook);
    }

    @Override
    public void changePoints(Player player) {
        player.points -= 1;
    }
}