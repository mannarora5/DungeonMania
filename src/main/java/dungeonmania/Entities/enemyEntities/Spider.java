package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

// extract  spider health + base damage

public class Spider extends Enemy {
    private static int EnemyHealth;
    private static int EnemyAttack;
    

    public Spider(String id, Position position) {
        super(id, "spider", position, false);
    }

    public static void setSpiderAttack(int enemyAttack) {
        Spider.EnemyAttack = enemyAttack;
        
    }

    public static void setSpiderHealth(int enemyHealth) {
        Spider.EnemyHealth = enemyHealth;
    }

    @Override
    public void movement(Direction direction, GameController game) {
        // TODO Auto-generated method stub
        
    }
        
    
    
}
