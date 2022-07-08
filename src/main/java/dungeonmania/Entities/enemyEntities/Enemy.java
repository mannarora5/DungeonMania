package dungeonmania.Entities.enemyEntities;

import dungeonmania.Entities.Entity;
import dungeonmania.util.Position;

public class Enemy extends Entity{

    public Enemy(String id, String type, Position position, boolean isInterctable) {
        super(id, type, position, isInterctable);
    }
    
}
