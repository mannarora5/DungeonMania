package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.Entities.Player.PlayerStateSubject;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.spiderNormalMovement;
import dungeonmania.Entities.enemyEntities.enemyMovments.spiderReverseMovement;
import dungeonmania.util.Position;

public class Spider extends Enemy implements EnemyObserver{

    public static int spiderHealth;
    public static int spiderAttack;

    private double currentSpiderHealth;

    enemyMovementState normalState;
    enemyMovementState reverselState;
    enemyMovementState currentState;

    public int currentPostick;


    public Spider(String Id, Position position) {
        super(Id, "spider", position, false, Spider.spiderAttack);
        this.normalState = new spiderNormalMovement(this);
        this.reverselState = new spiderReverseMovement(this);
        this.currentState = normalState;
        this.currentPostick = 0;

    }

    // extract  spider health + base damage
    public static void setSpiderAttack(int spiderAttack) {
        Spider.spiderAttack = spiderAttack;
        
    }

    /**
     * Set spider health 
     * @param spiderHealth
     */
    public static void setSpiderHealth(int spiderHealth) {
        Spider.spiderHealth = spiderHealth;
    }

    public void move(GameController game) {
        this.currentState.move(game);
    }

    @Override
    public double getEnemyHealth(){
        return this.currentSpiderHealth;
    }

    @Override
    public double setEnemyHealth(double health){
        return this.currentSpiderHealth =  health;
    }

    @Override
    public void updateMovement(PlayerStateSubject player){
        
    }
    
    /**
     * Set position of spider
     * @param pos
     */
    public void setPos(Position pos){
        super.setPosition(pos);
    }

    /**
     * Get position of spider
     * @return
     */
    public Position getPos(){
        return super.getPosition();
    }

    public double getCurrentSpiderHealth() {
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

