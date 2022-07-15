package dungeonmania.util;



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

}
