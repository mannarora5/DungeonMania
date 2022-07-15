package dungeonmania.Entities.enemyEntities.enemyMovments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.Entities.staticEntities.Door;
import dungeonmania.Entities.staticEntities.Portal;
import dungeonmania.Entities.staticEntities.Wall;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Position;

public class zombieRandomMovmentState implements enemyMovementState{

    Random rand = new Random();
    ZombieToast zombie;


    public zombieRandomMovmentState(ZombieToast zombie){
        this.zombie = zombie;
    }


    public void move(GameController game){
    Position zombieCurrentPosition = zombie.getPosition();
    List<Entity> entities = game.getEntities();
    List<Position> AdjacentPositions = zombieCurrentPosition.getAdjacentPositions();

    for(Entity entity: entities) {
        if (AdjacentPositions.contains(entity.getPosition()) == true) {
            if (entity instanceof Boulder || entity instanceof Portal || entity instanceof Wall || entity instanceof Door) {
                //Remove the position
                for (Position position: AdjacentPositions) {
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
    zombie.setPosition(newPosition);
    }
}
