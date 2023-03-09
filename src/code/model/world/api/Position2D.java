package code.model.world.api;

import code.exceptions.IllegalPositionException;

public interface Position2D {
    
    public int getPosX();
    public void setPosX(int position) throws IllegalPositionException;

    public int getPosY();
    public void setPosY(int position) throws IllegalPositionException;

    
}

