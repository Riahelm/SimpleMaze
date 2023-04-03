package code.model.util;


import code.model.world.impl.TileType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapReader {


    public static TileType[][] readMap(URL mapFile) throws IOException {
        int size = 0;
        Scanner myMapScanner = new Scanner(mapFile.openStream());
        List<TileType> tiles = new ArrayList<>();

        while(myMapScanner.hasNext()){
            size++;
            tiles.add(TileType.fromInt(myMapScanner.nextInt()));
        }

        size = (int)Math.sqrt(tiles.size());

        TileType[][] result = new TileType[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = tiles.get(i * size + j);
            }
        }
        myMapScanner.close();
        return result;
    }
}
