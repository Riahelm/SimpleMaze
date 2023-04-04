package code.view.instructions;

import code.model.actor.impl.EntityType;
import code.model.world.impl.TileType;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class InstructionsPanel extends JPanel {

    JLabel[] tileTypes;
    JLabel[] entityTypes;

    public InstructionsPanel(){
        setLayout(new FlowLayout());
        tileTypes = new JLabel[TileType.values().length];
        entityTypes = new JLabel[EntityType.values().length];
        add(new JLabel("Tiles"));
        for (int i = 0; i < tileTypes.length; ++i) {
            if(!(TileType.values()[i].equals(TileType.ACCESSIBLE_WITH_ENEMY) || TileType.values()[i].equals(TileType.SPAWNPOINT))){
                tileTypes[i] = new JLabel(TileType.values()[i].toString());
                tileTypes[i].setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../resources/tiles/" + TileType.values()[i].toString() + ".JPG"))));
                add(tileTypes[i]);
            }
        }
        add(new JLabel("Entities"));
        for (int i = 0; i < entityTypes.length; ++i){
            entityTypes[i] = new JLabel(EntityType.values()[i].toString());
            entityTypes[i].setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../../../resources/entities/" + EntityType.values()[i].toString() + ".JPG"))));
            add(entityTypes[i]);
        }
    }
}
