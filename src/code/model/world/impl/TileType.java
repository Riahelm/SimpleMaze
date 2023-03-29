package code.model.world.impl;

import code.view.Direction;

public enum TileType {
    NON_ACCESSIBLE,
    ACCESSIBLE,
    ACCESSIBLE_WITH_ENEMY,
    SPAWNPOINT,
    STAIRS,
    EXIT;

    public static TileType fromInt(int d) {
        return switch (d) {
            case 0 -> TileType.NON_ACCESSIBLE;
            case 1 -> TileType.ACCESSIBLE;
            case 2 -> TileType.ACCESSIBLE_WITH_ENEMY;
            case 3 -> TileType.SPAWNPOINT;
            case 4 -> TileType.STAIRS;
            case 5 -> TileType.EXIT;
            default -> throw new IllegalStateException("Unexpected value: " + d);
        };
    }

}