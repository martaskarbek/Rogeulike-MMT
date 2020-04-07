
public class Discette extends Item{
    public Discette(String itemLook) {
        super("D");
    }

    @Override
    public void changePoints(Player player) {
        player.points += 1;
    }

    
}