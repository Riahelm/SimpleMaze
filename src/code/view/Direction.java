package code.view;

import code.model.util.PairImpl;

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

    public PairImpl<Integer, Integer> toPair() {
        return switch (this) {
            case UP-> new PairImpl<>(0, 1);
            case DOWN -> new PairImpl<>(0, -1);
            case LEFT -> new PairImpl<>(-1, 0);
            case RIGHT -> new PairImpl<>(1, 0);
            case SPACE -> new PairImpl<>(0,0);
        };
    }
}
