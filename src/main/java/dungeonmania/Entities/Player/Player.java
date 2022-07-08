package dungeonmania.Entities.Player;


import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.Entities.staticEntities.Static;
import dungeonmania.Entities.staticEntities.Wall;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Entity {

    public Player(String id, String type, Position position, boolean isInterctable) {
        super(id, type, position, isInterctable);
    }
    
    public void movement(Position direction, GameController game) {
        //Get the nextPosition
        Position nextPosition = getPosition().translateBy(direction);
        //Check if any Walls are in that position
        List<Entity> entities = game.entitiesSamePosition(nextPosition);
        
        for (Entity entity: entities) {
            if (!(entity instanceof Static)) {
                break;
            }
            if (entity instanceof Wall) {
                break;
            }
            if (entity instanceof Boulder) {

            }
        }
        for  (Entity entity : entities) {
            if (entity instanceof Collectable) {
                
            }
        }

    }
    
}
