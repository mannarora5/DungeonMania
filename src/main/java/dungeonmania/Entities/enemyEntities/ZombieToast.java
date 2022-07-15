package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {

    private static int zombieHealth;
    private static int zombieAttack;

    private  int currentzombieHealth;

    public ZombieToast(String Id, Position position) {
        
        super(Id, "zombie_toast", position, true);

        this.currentzombieHealth = ZombieToast.zombieHealth;
        
    }

    /**
     * Move zombie toast
     */
    public void move(GameController game) {
        // TODO Auto-generated method stub
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
