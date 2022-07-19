package dungeonmania.Entities.enemyEntities.enemyMovments;

import java.util.List;
import java.util.Random;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.enemyEntities.Mercenary;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.Entities.staticEntities.Door;
import dungeonmania.Entities.staticEntities.Portal;
import dungeonmania.Entities.staticEntities.Wall;
import dungeonmania.util.Position;

public class mercenaryRandomMovementState implements enemyMovementState {
    Mercenary mercenary;
    Random rand = new Random();


    public mercenaryRandomMovementState(Mercenary mercenary){
        this.mercenary = mercenary;
    }
    

    public void move(GameController game){
        Position mercenaryCurrentPosition = mercenary.getPosition();
        List<Entity> entities = game.getEntities();
        List<Position> AdjacentPositions = mercenaryCurrentPosition.getAdjacentPositions();
        List<Position> cloneAdjacentPositions = mercenaryCurrentPosition.getAdjacentPositions();
        //Delete all diagonally adjacent cells
        AdjacentPositions.remove(mercenary.getPosition().translateBy(1, 1));
        AdjacentPositions.remove(mercenary.getPosition().translateBy(1, -1));
        AdjacentPositions.remove(mercenary.getPosition().translateBy(-1, -1));
        AdjacentPositions.remove(mercenary.getPosition().translateBy(-1, 1));

        for(Entity entity: entities) {
            if (AdjacentPositions.contains(entity.getPosition()) == true) {
                if (entity instanceof Boulder || entity instanceof Portal || entity instanceof Wall || entity instanceof Door) {
                    //Remove the position
                    for (Position position: cloneAdjacentPositions) {
                        if (position.equals(entity.getPosition())) {
                            AdjacentPositions.remove(position);
                        }
                    }
                }
            }
        }

        //Move zombie in random direction
        int randomIndex = rand.nextInt(AdjacentPositions.size());
        Position newPosition = AdjacentPositions.get(randomIndex);
        mercenary.setPosition(newPosition);
    }
}
    
