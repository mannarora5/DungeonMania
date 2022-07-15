package dungeonmania.Entities.enemyEntities.enemyMovement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.Entities.staticEntities.Door;
import dungeonmania.Entities.staticEntities.Wall;
import dungeonmania.util.Position;
import dungeonmania.util.PositonDistance;

public class zombieScaredMovementState implements enemyMovementState{


    ZombieToast zombie;


    public zombieScaredMovementState(ZombieToast zombie){
        this.zombie = zombie;
    }

    
    /**
     * @param game
     */
    public void move(GameController game){ 
        Position playerPosition = game.findPlayer().getPosition();
        List<Position> adjacentPositions = zombie.getPosition().getAdjacentPositions();
        List<PositonDistance> distanceAndPositions = new ArrayList<>();

        //Delete all diagonally adjacent cells
        adjacentPositions.remove(zombie.getPosition().translateBy(1, 1));
        adjacentPositions.remove(zombie.getPosition().translateBy(1, -1));
        adjacentPositions.remove(zombie.getPosition().translateBy(-1, -1));
        adjacentPositions.remove(zombie.getPosition().translateBy(-1, 1));

        //Check if entites are in the direction up down, left or right
        Position UP = zombie.getPosition().translateBy(0, 1);
        Position DOWN = zombie.getPosition().translateBy(0, -1);
        Position LEFT = zombie.getPosition().translateBy(-1, 0);
        Position RIGHT = zombie.getPosition().translateBy(1,0);
        //List of all entities in that position
        List<Entity> entitiesUP =  game.entitiesSamePosition(UP);
        List<Entity> entitiesDOWN = game.entitiesSamePosition(DOWN);
        List<Entity> entitiesLEFT = game.entitiesSamePosition(LEFT);
        List<Entity> entitiesRIGHT = game.entitiesSamePosition(RIGHT);
        //Check the valid squares for up, down, left and right 
        checkEntities(entitiesUP, adjacentPositions);
        checkEntities(entitiesDOWN, adjacentPositions);
        checkEntities(entitiesRIGHT, adjacentPositions);
        checkEntities(entitiesLEFT, adjacentPositions);

        //Loop through possible adjacent spaces and check the distance
        for(Position adjacentPosition : adjacentPositions) {
            double distance = PositonDistance.distancebetweenposition(playerPosition, adjacentPosition);
            PositonDistance dist = new PositonDistance(distance, adjacentPosition);
            distanceAndPositions.add(dist);
        }
        //Set the new positon to the higest distance
        double highestdistance = 0;
        for (PositonDistance positonDistance: distanceAndPositions) {
            if (highestdistance < positonDistance.getDistance()) {
                zombie.setPosition(positonDistance.getPosition());
            }
        }
    }



    public void checkEntities(List<Entity> entities,List<Position> adjacentPositions) {
        for (Entity entity : entities) {
            if (entity instanceof Door) {
                Door d = (Door) entity;
                if (d.getOpen() == false) {
                    adjacentPositions.remove(entity.getPosition());
                }
            }
            if (entity instanceof Boulder || entity instanceof Wall) {
                adjacentPositions.remove(entity.getPosition());
            }    
        }
    }
    
   
}
