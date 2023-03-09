package code.model.util;

public class Pair<X, Y> {
    
    private X x;
    private Y y;
    
    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    public int hashCode(){
        return x.hashCode() ^ y.hashCode();
    }
    public boolean equals(Object comPair){
        if(comPair == null){
            return false;
        }
        if(this == comPair){
            return true;
        }
        if(comPair instanceof Pair<?, ?>){
            Pair<?,?> toCompare = (Pair<?,?>) comPair;
            return (this.getX().equals(toCompare.getX()) && this.getY().equals(toCompare.getY()));
        }else{
            return false;
        }
    }
}
