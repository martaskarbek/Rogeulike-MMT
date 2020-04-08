import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map2 {
    String file;

    public ArrayList<String> readMap(String file) {
        final ArrayList<String> welcomeReaded = new ArrayList<>();
        String welcomeLine = "";
        try {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((welcomeLine = reader.readLine()) != null){
            welcomeReaded.add(welcomeLine);
        }
        reader.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return welcomeReaded;
    }

    public String[][] bufferMap() {
        int mapHeight = 30;
        int mapWidth = 70;
        String[][] map = new String[mapHeight][mapWidth];
        ArrayList<String> mapList = readMap(file);
        for (int y = 0; y < mapHeight; y++) {
            String line = mapList.get(y);
            String[] mapRow = line.split("");
            for (int x = 0; x < mapWidth; x++) {
                map[y][x] = mapRow[x];
            }
        }
        return map;
    }
}