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
        //Get a list of all Entities in that position
        List<Entity> entities = game.entitiesSamePosition(nextPosition);
        //Loop through entities and see if player can move to nextPosition
        for (Entity entity: entities) {
            if (!(entity instanceof Static)) {
                break;
            }
            if (entity instanceof Wall) {
                return;
            }
            if (entity instanceof Boulder) {
                boolean check = checkWallinPositon(direction, game);
                if (check == false) {
                    setboulderposition(direction, entity);
                    return;
                }
            }
        }

        for  (Entity entity : entities) {
            if (entity instanceof Collectable) {

            }
        }

    }


    /**
     * Check if a wall is in the position
     * @param direction
     * @param game
     * @return
     */
    public boolean checkWallinPositon(Position direction, GameController game) {
        //Get the nextPosition
        Position nextPosition = getPosition().translateBy(direction);
        //Check if any Walls are in that position
        List<Entity> entities = game.entitiesSamePosition(nextPosition);
        //return true if there is a wall
        return entities.stream().anyMatch(Entity -> (Entity instanceof Wall));
        
    }
    
    /**
     * Set new position of boulder after player moves it
     * @param directions
     * @param entity
     */
    public void setboulderposition(Position direction, Entity entity) {
        Position newposition = entity.getPosition().translateBy(direction);
        setPosition(newposition);
    }

}
