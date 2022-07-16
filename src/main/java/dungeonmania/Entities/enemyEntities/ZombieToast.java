package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.zombieRandomMovmentState;
import dungeonmania.Entities.enemyEntities.enemyMovments.zombieScaredMovementState;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {

    private static int zombieHealth;
    private static int zombieAttack;

    enemyMovementState normalZombieMovement;
    enemyMovementState scaredZombieMovement;
    enemyMovementState currentMovementState;

    private  int currentzombieHealth;

    public ZombieToast(String Id, Position position) {
        
        super(Id, "zombie_toast", position, true);

        this.currentzombieHealth = ZombieToast.zombieHealth;

        this.normalZombieMovement = new zombieRandomMovmentState(this);
        this.scaredZombieMovement = new zombieScaredMovementState(this);

        this.currentMovementState = this.normalZombieMovement;
        
    }

    public void move(GameController game) {
        this.currentMovementState.move(game);
    }

    /**
     * Set attack damage for zombie toast
     * @param enemyAttack
     */
    public static void setZombieToastAttack(int enemyAttack) {
        ZombieToast.zombieAttack = enemyAttack;
    }

    /**
     * Set health for zombie 
     * @param enemyHealth
     */
    public static void setZombieToastHealth(int enemyHealth) {
        ZombieToast.zombieHealth = enemyHealth;
    }

    /**
     * Get health of zombie
     * @return
     */
    public int getCurrentzombieHealth() {
        return this.currentzombieHealth;
    }

    /**
     * Set health of current zombie
     * @param currentzombieHealth
     */
    public void setCurrentzombieHealth(int currentzombieHealth) {
        this.currentzombieHealth = currentzombieHealth;
    }

    /**
     * Get zombie damage
     * @return
     */
    public int getZombieAttack(){
        return ZombieToast.zombieAttack;
    }


    
}
