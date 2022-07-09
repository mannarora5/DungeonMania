package dungeonmania.Entities.Player;


import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.staticEntities.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Entity {

    public Player(String id,Position position) {
        super(id, "player", position, false);
    }
    
    public void movement(Direction direction, GameController game) {
        //Get the nextPosition
        Position nextPosition = super.getPosition().translateBy(direction.getOffset());

        //Get a list of all Entities in that position
        List<Entity> entities = game.entitiesSamePosition(nextPosition);
        //Loop through entities and see if player can move to nextPosition

        if (entities.isEmpty()){
            super.setPosition(nextPosition);
            return;
        }

        for (Entity entity: entities) {

            if (!(entity instanceof Static)) {
                break;
            }

            if (entity instanceof Wall) {
                return;
            } else if (entity instanceof Boulder) {

                Boulder b = (Boulder) entity;
                boolean boulderMoved = b.move(direction,game);
                if (boulderMoved) {
                    super.setPosition(nextPosition);
                    return;
                }
            
            } else if (entity instanceof Exit) {
                //TODO:
                return;

            } else if (entity instanceof Portal) {
                // TODO:
                return;

            } if (entity instanceof Door) {
                super.setPosition(nextPosition);
                //More stuff down the line
                // TODO:
                return;

            } if (entity instanceof Switch || entity instanceof zombieSpawner) {
                super.setPosition(nextPosition);
                return;
            }

        }

        for  (Entity entity : entities) {
            if (entity instanceof Collectable) {

            }
        }

    }


}
