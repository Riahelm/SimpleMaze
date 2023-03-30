package code.model.util;

import code.model.util.api.Pair;

public class PairImpl<X, Y> implements Pair{
    
    private X x;
    private Y y;
    
    public PairImpl(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    @Override
    public String toString() {
        return "<" + x + ", " + y + ">";
    }

    public int hashCode(){
        return x.hashCode() ^ y.hashCode();
    }
    public boolean equals(Object comPair){ //Funny joke here
        if(comPair == null){
            return false;
        }
        if(this == comPair){
            return true;
        }
        if(comPair instanceof PairImpl<?, ?>){
            PairImpl<?,?> toCompare = (PairImpl<?,?>) comPair;
            return (this.getX().equals(toCompare.getX()) && this.getY().equals(toCompare.getY()));
        }else{
            return false;
        }
    }
}
