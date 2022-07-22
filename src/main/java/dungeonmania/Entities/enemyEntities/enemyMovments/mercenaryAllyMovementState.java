package dungeonmania.Entities.enemyEntities.enemyMovments;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.enemyEntities.Mercenary;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.Entities.staticEntities.Door;
import dungeonmania.Entities.staticEntities.Wall;
import dungeonmania.util.Position;
import dungeonmania.util.PositonDistance;

public class mercenaryAllyMovementState implements enemyMovementState{

    private Mercenary mercenary;


    public mercenaryAllyMovementState(Mercenary mercenary){
        this.mercenary = mercenary;
    }


    public void move(GameController game){
        Position mercenarypPosition = mercenary.getPosition();
        Position playerPostionprevious = game.getPlayerPositions().get(game.getPlayerPositions().size()-1);
        List<Position> AdjacentPositions = mercenarypPosition.getAdjacentPositions();
        List<PositonDistance> distanceAndPositions = new ArrayList<>();

        //Add the current position to teh AdjacentPositionsList
        AdjacentPositions.add(mercenarypPosition);
        //Delete the cardinally diagonal grids
        AdjacentPositions.remove(mercenarypPosition.translateBy(1, 1));
        AdjacentPositions.remove(mercenarypPosition.translateBy(1, -1));
        AdjacentPositions.remove(mercenarypPosition.translateBy(-1, -1));
        AdjacentPositions.remove(mercenarypPosition.translateBy(-1, 1));

        //Check if entites are in the direction up down, left or right
        Position UP = mercenarypPosition.translateBy(0, 1);
        Position DOWN = mercenarypPosition.translateBy(0, -1);
        Position LEFT = mercenarypPosition.translateBy(-1, 0);
        Position RIGHT = mercenarypPosition.translateBy(1,0);

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
            double distance = PositonDistance.distancebetweenposition(playerPostionprevious, adjacentPosition);
            PositonDistance dist = new PositonDistance(distance, adjacentPosition);
            distanceAndPositions.add(dist);
        }

        //Loop through and find the shortest distance between player and mercenary
        double shortestDistance = 1000;
        PositonDistance shortDistance = new PositonDistance(-1, UP);
        for (PositonDistance positonDistance: distanceAndPositions) {
            if (shortestDistance > positonDistance.getDistance()) {
                shortDistance = positonDistance;
                shortestDistance = positonDistance.getDistance();
            }
        }

        //Set the new Postion
        mercenary.setPosition(shortDistance.getPosition());
    }

    public void checkEntities(List<Entity> entities,List<Position> adjacentPositions) {
        for (Entity entity : entities) {
            if (entity instanceof Door) {
                Door d = (Door) entity;
                if (d.getOpen() == false) {
                    adjacentPositions.remove(entity.getPosition());
                }
            }
            if (entity instanceof Boulder || entity instanceof Wall || entity instanceof Player) {
                adjacentPositions.remove(entity.getPosition());
            }    
        }
    }



    public Mercenary getMercenary() {
        return this.mercenary;
    }

    public void setMercenary(Mercenary mercenary) {
        this.mercenary = mercenary;
    }


}
