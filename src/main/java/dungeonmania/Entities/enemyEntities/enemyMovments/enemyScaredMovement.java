package dungeonmania.Entities.enemyEntities.enemyMovments;

import java.util.ArrayList;
import java.util.List;


import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.enemyEntities.Enemy;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.Entities.staticEntities.Door;
import dungeonmania.Entities.staticEntities.Wall;
import dungeonmania.util.Position;
import dungeonmania.util.PositonDistance;

public class enemyScaredMovement implements enemyMovementState{

    private Enemy enemy;

    public enemyScaredMovement(Enemy enemy){
        this.enemy = enemy;
    }


    public void move(GameController game){


        Position enemyPosition = this.enemy.getPosition();
        Position playerPostion = game.findPlayer().getPosition();
        List<Position> AdjacentPositions = enemyPosition.getAdjacentPositions();
        List<PositonDistance> distanceAndPositions = new ArrayList<>();

        //Delete the cardinally diagonal grids
        AdjacentPositions.remove(enemyPosition.translateBy(1, 1));
        AdjacentPositions.remove(enemyPosition.translateBy(1, -1));
        AdjacentPositions.remove(enemyPosition.translateBy(-1, -1));
        AdjacentPositions.remove(enemyPosition.translateBy(-1, 1));

        //Check if entites are in the direction up down, left or right
        Position UP = enemyPosition.translateBy(0, 1);
        Position DOWN = enemyPosition.translateBy(0, -1);
        Position LEFT = enemyPosition.translateBy(-1, 0);
        Position RIGHT = enemyPosition.translateBy(1,0);

        //Get a list of all entities in that position
        List<Entity> entitiesUP =  game.entitiesSamePosition(UP);
        List<Entity> entitiesDOWN = game.entitiesSamePosition(DOWN);
        List<Entity> entitiesLEFT = game.entitiesSamePosition(LEFT);
        List<Entity> entitiesRIGHT = game.entitiesSamePosition(RIGHT);

        //Check type of entity to see if mercenary can move to next grid
        checkEntities(entitiesUP, AdjacentPositions);
        checkEntities(entitiesDOWN, AdjacentPositions);
        checkEntities(entitiesRIGHT, AdjacentPositions);
        checkEntities(entitiesLEFT, AdjacentPositions);

        //Loop through possible adjacent spaces and check the distance
        for(Position adjacentPosition : AdjacentPositions) {
            double distance = PositonDistance.distancebetweenposition(playerPostion, adjacentPosition);
            PositonDistance dist = new PositonDistance(distance, adjacentPosition);
            distanceAndPositions.add(dist);
        }

        //Loop through and find the shortest distance between player and mercenary
        double highestdistance = 0;
        PositonDistance furtheDistance = new PositonDistance(-1, UP);
        for (PositonDistance positonDistance: distanceAndPositions) {
            if (highestdistance < positonDistance.getDistance()) {
                furtheDistance = positonDistance;
                highestdistance = positonDistance.getDistance();
            }
        }

        //Set the new Postion
        this.enemy.setPosition(furtheDistance.getPosition());

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



    public Enemy getEnemy() {
        return this.enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
    
}
