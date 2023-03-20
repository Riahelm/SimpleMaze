package code.view;

import code.model.util.Pair;

import javax.swing.*;

public enum Directions {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Directions fromInt(int d) {
        return switch (d) {
            case 0 -> Directions.UP;
            case 1 -> Directions.DOWN;
            case 2 -> Directions.LEFT;
            case 3 -> Directions.RIGHT;
            default -> null;
        };
    }

    public Pair<Integer, Integer> toPair() {
        return switch (this) {
            case UP-> new Pair<>(0, 1);
            case DOWN -> new Pair<>(0, -1);
            case LEFT -> new Pair<>(-1, 0);
            case RIGHT -> new Pair<>(1, 0);
        };
    }
}
