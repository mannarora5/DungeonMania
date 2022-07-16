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


    public static void setZombieToastAttack(int enemyAttack) {
        ZombieToast.zombieAttack = enemyAttack;
    }

    public static void setZombieToastHealth(int enemyHealth) {
        ZombieToast.zombieHealth = enemyHealth;
    }



    public int getCurrentzombieHealth() {
        return this.currentzombieHealth;
    }

    public void setCurrentzombieHealth(int currentzombieHealth) {
        this.currentzombieHealth = currentzombieHealth;
    }

    public int getZombieAttack(){
        return ZombieToast.zombieAttack;
    }


    
}
