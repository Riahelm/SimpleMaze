package code.util;

public record Pair<X, Y>(X first, Y second) {

    @Override
    public String toString() {
        return "<" + first + ", " + second + ">";
    }

    public int hashCode() {
        return first.hashCode() ^ second.hashCode();
    }

    public boolean equals(Object comPair) { //Funny joke here
        if (comPair == null) {
            return false;
        }
        if (this == comPair) {
            return true;
        }
        if (comPair instanceof Pair<?, ?> toCompare) {
            return (this.first().equals(toCompare.first()) && this.second().equals(toCompare.second()));
        } else {
            return false;
        }
    }
}
