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

    public void move(GameController game) {
        // TODO Auto-generated method stub
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
