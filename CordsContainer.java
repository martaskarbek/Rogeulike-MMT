import java.util.ArrayList;
import java.util.List;

public class CordsContainer {
    private List<Coordinates> coordinates = new ArrayList<>();

    //public CordsContainer() {

    //}

    public void addCoordinates(Coordinates coord, List<Coordinates> coordinates) {
        coordinates.add(coord);
    }
}