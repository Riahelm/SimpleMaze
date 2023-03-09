package code.model.world.impl;

import code.exceptions.IllegalPositionException;
import code.model.world.api.GameMap;
import code.model.world.api.Position2D;

public class Position2DImpl implements Position2D {
    
    private Integer posX;
    private Integer posY;
    
    public Position2DImpl(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }
    @Override
    public int getPosX() {
        return this.posX;
    }
    @Override
    public void setPosX(int position) throws IllegalPositionException {
        if(position > GameMap.MAX_MAP_SIZE){
            throw new IllegalPositionException();
        }else{
            this.posX = position;
        }
        
    }
    @Override
    public int getPosY() {
        return this.posY;
    }
    @Override
    public void setPosY(int position) throws IllegalPositionException {
        if(position > GameMap.MAX_MAP_SIZE){
            throw new IllegalPositionException();
        }else{
            this.posY = position;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position2DImpl other = (Position2DImpl) obj;
        if (posX == null) {
            if (other.posX != null)
                return false;
        } else if (!posX.equals(other.posX))
            return false;
        if (posY == null) {
            if (other.posY != null)
                return false;
        } else if (!posY.equals(other.posY))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Position2DImpl [posX=" + posX + ", posY=" + posY + "]";
    }
}
