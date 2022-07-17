package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.util.Position;

public abstract class Enemy extends Entity{

    public double enemyAttack;

    // enemy id, type, position, health, attack damage, multiplier and interactable 
    public Enemy(String id, String type, Position position, Boolean isInterctable, double attack) {
        super(id, type, position, isInterctable);
        this.enemyAttack = attack;
    }

    public Enemy(String id, String type, Position position) {
        super(id, type, position, false);
    }
    // enemy movement
    public abstract void move(GameController game);
        

    public void setpos(Position pos){
        super.setPosition(pos);
    }

    public Position getpos(){
        return super.getPosition();
    }


    public double getEnemyAttack() {
        return this.enemyAttack;
    }

    public void setEnemyAttack(double enemyAttack) {
        this.enemyAttack = enemyAttack;
    }


}
