package dungeonmania.Entities.enemyEntities;

import dungeonmania.util.Position;

public class Spider extends Enemy {

    public Spider(String Id, Position position) {
        super(Id, "spider", position);
    }
    

    // Getters and Setters
    public void setPos(Position pos){
        super.setPosition(pos);
    }

    public Position getPos(){
        return super.getPosition();
    }
}
