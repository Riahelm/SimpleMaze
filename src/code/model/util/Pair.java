package code.model.util;

public record Pair<X, Y>(X x, Y y) {

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    public int hashCode() {
        return x.hashCode() ^ y.hashCode();
    }

    public boolean equals(Object comPair) { //Funny joke here
        if (comPair == null) {
            return false;
        }
        if (this == comPair) {
            return true;
        }
        if (comPair instanceof Pair<?, ?> toCompare) {
            return (this.x().equals(toCompare.x()) && this.y().equals(toCompare.y()));
        } else {
            return false;
        }
    }
}
