package dungeonmania.Entities.enemyEntities;

import dungeonmania.GameController;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

// extract  spider health + base damage

public class Spider extends Enemy {

    public static int spiderHealth;
    public static int spiderAttack;

    public int currentSpiderHealth;

    

    public Spider(String id, Position position) {
        super(id, "spider", position, false);
        this.currentSpiderHealth = Spider.spiderHealth;
    }

    public static void setSpiderAttack(int spiderAttack) {
        Spider.spiderAttack = spiderAttack;
        
    }

    public static void setSpiderHealth(int spiderHealth) {
        Spider.spiderHealth = spiderHealth;
    }

    @Override
    public void movement(Direction direction, GameController game) {
        // TODO Auto-generated method stub
        
    }
        
    
    
}
