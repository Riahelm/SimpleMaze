package code.model.util;

import code.model.world.impl.TileType;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class MapReader {
    public static TileType[][] readMap(int size, URL mapFile) throws IOException {
        TileType[][] tiles = new TileType[size][size];
        Scanner myMapScanner = new Scanner(mapFile.openStream());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = (myMapScanner.next().equals("0"))? TileType.IMPASSABLE: TileType.PASSABLE;
            }
        }
        myMapScanner.close();
        return tiles;
    }
}
