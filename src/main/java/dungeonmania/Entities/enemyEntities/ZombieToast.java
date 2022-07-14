package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {
    private static int EnemyHealth;
    private static int EnemyAttack;

    public ZombieToast(String id, Position position, int damageMultiplier) {
        
        super(id, "ZombieToast", position, true);
        //TODO Auto-generated constructor stub
    }

    public static void setZombieToastAttack(int enemyAttack) {
        ZombieToast.EnemyAttack = enemyAttack;
    }

    public static void setZombieToastHealth(int enemyHealth) {
        ZombieToast.EnemyHealth = enemyHealth;
    }

    @Override
    public void movement(Direction direction, GameController game) {
        // TODO Auto-generated method stub
        
    }
    
}
