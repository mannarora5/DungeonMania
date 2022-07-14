package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public abstract class Enemy extends Entity{
    // private int attackDamage;
    // private int damageMultiplier;
    private boolean EnemyState;
    


    // enemy id, type, position, health, attack damage, multiplier and interactable 
    public Enemy(String id, String type, Position position, Boolean isInterctable) {
        super(id, type, position, isInterctable);
        this.EnemyState = false;
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

    // returns state of enemy 
    public boolean isEnemyState() {
        return EnemyState;
    }

    // /**
    //  * Set enemy attack
    //  * @param enemyAttack
    //  */
    // public abstract void setEnemyAttack(int enemyAttack);

    // /**
    //  * Set Enemy health
    //  * @param enemyHealth
    //  */
    // public abstract void setEnemyHealth(int enemyHealth);
    
  

    
        
    
}
