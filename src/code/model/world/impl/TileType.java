package code.model.world.impl;

public enum TileType {
    NON_ACCESSIBLE,

    ACCESSIBLE,
    ACCESSIBLE_WITH_ENEMY,
    ACCESSIBLE_WITH_NPC,
    SPAWN_POINT,
    STAIRS,
    EXIT;

    public static TileType fromInt(int d) {
        return switch (d) {
            case 0 -> TileType.NON_ACCESSIBLE;
            case 1 -> TileType.ACCESSIBLE;
            case 2 -> TileType.ACCESSIBLE_WITH_ENEMY;
            case 3 -> TileType.ACCESSIBLE_WITH_NPC;
            case 4 -> TileType.SPAWN_POINT;
            case 5 -> TileType.STAIRS;
            case 6 -> TileType.EXIT;
            default -> throw new IllegalStateException("Unexpected value: " + d);
        };
    }

}