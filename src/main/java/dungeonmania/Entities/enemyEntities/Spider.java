package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.spiderNormalMovement;
import dungeonmania.Entities.enemyEntities.enemyMovments.spiderReverseMovement;
import dungeonmania.util.Position;

public class Spider extends Enemy {

    public static int spiderHealth;
    public static int spiderAttack;

    public int currentSpiderHealth;

    enemyMovementState normalState;
    enemyMovementState reverselState;
    enemyMovementState currentState;

    public int currentPostick;


    public Spider(String Id, Position position) {
        super(Id, "spider", position);
        this.normalState = new spiderNormalMovement(this);
        this.reverselState = new spiderReverseMovement(this);
        this.currentState = normalState;
        this.currentPostick = 0;

    }

    // extract  spider health + base damage
    public static void setSpiderAttack(int spiderAttack) {
        Spider.spiderAttack = spiderAttack;
        
    }

    public static void setSpiderHealth(int spiderHealth) {
        Spider.spiderHealth = spiderHealth;
    }

    public void move(GameController game) {
        this.currentState.move(game);
    }
    
    // Getters and Setters
    public void setPos(Position pos){
        super.setPosition(pos);
    }

    public Position getPos(){
        return super.getPosition();
    }

    public int getCurrentSpiderHealth() {
        return this.currentSpiderHealth;
    }

    public void setCurrentSpiderHealth(int currentSpiderHealth) {
        this.currentSpiderHealth = currentSpiderHealth;
    }

    public enemyMovementState getNormalState() {
        return this.normalState;
    }

    public void setNormalState(enemyMovementState normalState) {
        this.normalState = normalState;
    }

    public enemyMovementState getReverselState() {
        return this.reverselState;
    }

    public void setReverselState(enemyMovementState reverselState) {
        this.reverselState = reverselState;
    }

    public enemyMovementState getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(enemyMovementState currentState) {
        this.currentState = currentState;
    }

    public int getCurrentPostick() {
        return this.currentPostick;
    }

    public void setCurrentPostick(int currentPostick) {
        this.currentPostick = currentPostick;
    }

    public void increasetick(){
        this.currentPostick += 1;
    }

    public void decreasetick(){
        this.currentPostick -= 1;
    }

}

