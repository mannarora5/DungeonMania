package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public abstract class Enemy extends Entity{
    // private int attackDamage;
    // private int damageMultiplier;
    private String EnemyState;
    


    

    // enemy id, type, position, health, attack damage, multiplier and interactable 
    public Enemy(String id, String type, Position position, Boolean isInterctable) {
        super(id, type, position, isInterctable);
        this.EnemyState = "normal";
    }

    // returns the damage of the enemy 
    // public int getAttackDamage() {
    //     this.attackDamage = attackDamage * damageMultiplier;
    //     return this.attackDamage;
    // }

    // sets damage multiplier (dependent on state)
    // public void setDamageMultiplier(int multiplier) {
    //     this.damageMultiplier = multiplier;
    // }


    // enemy movement
    public abstract void movement(Direction direction, GameController game);

    public String getEnemyState() {
        return EnemyState;
    }

    public void setEnemyState(String enemyState) {
        EnemyState = enemyState;
    }
    
    
  

    
        
    
}
