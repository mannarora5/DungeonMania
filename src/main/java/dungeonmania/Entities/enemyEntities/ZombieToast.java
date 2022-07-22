package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.Entities.Player.PlayerStateSubject;
import dungeonmania.Entities.Player.PlayerState.InvincibleState;
import dungeonmania.Entities.Player.PlayerState.NormalState;
import dungeonmania.Entities.Player.PlayerState.PlayerState;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyRandomMovement;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyScaredMovement;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy implements EnemyObserver{

    private static int zombieHealth;
    private static int zombieAttack;

    private enemyMovementState normalZombieMovement;
    private enemyMovementState scaredZombieMovement;
    private enemyMovementState currentMovementState;

    private  double currentzombieHealth;

    public ZombieToast(String Id, Position position) {
        
        super(Id, "zombie_toast", position, true, ZombieToast.zombieAttack);

        this.currentzombieHealth = ZombieToast.zombieHealth;

        this.normalZombieMovement = new enemyRandomMovement(this);
        this.scaredZombieMovement = new enemyScaredMovement(this);

        this.currentMovementState = this.normalZombieMovement;
        
    }

    /**
     * Move for zombie
     */
    public void move(GameController game) {
        this.currentMovementState.move(game);
    }



    @Override
    public double getEnemyHealth(){
        return this.currentzombieHealth;
    }

    @Override
    public double setEnemyHealth(double health){
        return this.currentzombieHealth =  health;
    }

    /**
     * Update movememnt ofzombie
     */
    @Override
    public void updateMovement(PlayerStateSubject player)  {

        PlayerState state = player.getState();

        if (state instanceof NormalState) {
            this.currentMovementState = this.normalZombieMovement;

        } else if (state instanceof InvincibleState) {

            this.currentMovementState = this.scaredZombieMovement;
        }
        
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
    public double getCurrentzombieHealth() {
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
