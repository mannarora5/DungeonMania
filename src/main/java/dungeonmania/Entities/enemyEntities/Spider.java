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
    /**
     * Set spider attack damage
     * @param spiderAttack
     */
    public static void setSpiderAttack(int spiderAttack) {
        Spider.spiderAttack = spiderAttack;
        
    }

    /**
     * Set spider health 
     * @param spiderHealth
     */
    public static void setSpiderHealth(int spiderHealth) {
        Spider.spiderHealth = spiderHealth;
    }

    /**
     * Move Spider
     */
    public void move(GameController game) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Set position of spider
     * @param pos
     */
    public void setPos(Position pos){
        super.setPosition(pos);
    }

    /**
     * Get position of spider
     * @return
     */
    public Position getPos(){
        return super.getPosition();
    }
    
    
}
