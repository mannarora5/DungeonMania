package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.util.Position;

public abstract class Enemy extends Entity{

    // enemy id, type, position, health, attack damage, multiplier and interactable 
    public Enemy(String id, String type, Position position, Boolean isInterctable) {
        super(id, type, position, isInterctable);
    }

    public Enemy(String id, String type, Position position) {
        super(id, type, position, false);
    }
    
    /**
     * Move enemy
     * @param game
     */
    public abstract void move(GameController game);
    
}
