package dungeonmania.util;

import java.util.ArrayList;
import java.util.List;

public class PositonDistance {
    public double distance;
    public Position position;

    public PositonDistance(double distance, Position position) {
        this.distance = distance;
        this.position = position;
    }

    public double getDistance() {
        return distance;
    }

    public Position getPosition() {
        return position;
    }
    
    public static final double distancebetweenposition(Position A, Position B) {
        int positionAX = A.getX();
        int positionAY = A.getY();
        int positionBX = B.getX();
        int positionBY = B.getY();
        return Math.sqrt((positionBY - positionAY) * (positionBY - positionAY) + 
                            (positionBX - positionAX) * (positionBX - positionAX));
    }




    // Return cardinally adjacent positions in an array list with the following element positions:
    //   1 
    // 7 p 3
    //   5 
    public static List<Position> getCardinalPositions(Position position) {

        List<Position> adjacentPositions = new ArrayList<>();

        int x = position.getX();
        int y = position.getY();

        adjacentPositions.add(new Position(x  , y-1));
        adjacentPositions.add(new Position(x+1, y));
        adjacentPositions.add(new Position(x  , y+1));
        adjacentPositions.add(new Position(x-1, y));
        return adjacentPositions;
    }


}
