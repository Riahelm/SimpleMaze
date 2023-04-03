package code.model.world.impl;

import code.model.world.api.Position2D;

public class Position2DImpl implements Position2D {
    
    private final Integer posX;
    private final Integer posY;
    
    public Position2DImpl(Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
    }
    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
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
            return other.posY == null;
        } else return posY.equals(other.posY);
    }


    @Override
    public String toString() {
        return "Position2DImpl [posX=" + posX + ", posY=" + posY + "]";
    }
}
