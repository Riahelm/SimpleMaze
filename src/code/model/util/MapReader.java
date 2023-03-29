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
                tiles[i][j] = TileType.fromInt(myMapScanner.nextInt());
            }
        }
        myMapScanner.close();
        return tiles;
    }
}
