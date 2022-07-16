package dungeonmania.Entities.collectableEntities;

import dungeonmania.util.Position;

public class Bomb extends Collectable{

    public static Integer radius;

    public Bomb(String Id, Position position) {
        super(Id, "bomb", position);
    }

    public static void setRadius(Integer radius){
        Bomb.radius = radius;
    }
}
