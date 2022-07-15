package dungeonmania.Entities.enemyEntities;

import dungeonmania.Entities.enemyEntities.enemyMovments.enemyMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.spiderNormalMovement;
import dungeonmania.Entities.enemyEntities.enemyMovments.spiderReverseMovement;
import dungeonmania.util.Position;

public class Spider extends Enemy {

    enemyMovementState normalState;
    enemyMovementState reverselState;
    enemyMovementState currentState;


    public Spider(String Id, Position position) {
        super(Id, "spider", position);
        this.normalState = new spiderNormalMovement(this);
        this.reverselState = new spiderReverseMovement(this);
        this.currentState = normalState;
    }
    

    // Getters and Setters
    public void setPos(Position pos){
        super.setPosition(pos);
    }

    public Position getPos(){
        return super.getPosition();
    }
}
