package code.view;

import code.model.util.Pair;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    SPACE;

    public static Direction fromInt(int d) {
        return switch (d) {
            case 0 -> Direction.UP;
            case 1 -> Direction.DOWN;
            case 2 -> Direction.LEFT;
            case 3 -> Direction.RIGHT;
            default -> Direction.SPACE;
        };
    }

    public Pair<Integer, Integer> toPair() {
        return switch (this) {
            case UP-> new Pair<>(0, 1);
            case DOWN -> new Pair<>(0, -1);
            case LEFT -> new Pair<>(-1, 0);
            case RIGHT -> new Pair<>(1, 0);
            case SPACE -> new Pair<>(0,0);
        };
    }
}
