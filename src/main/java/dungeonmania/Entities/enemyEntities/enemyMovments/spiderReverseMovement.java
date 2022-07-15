package dungeonmania.Entities.enemyEntities.enemyMovments;

import dungeonmania.GameController;
import dungeonmania.Entities.enemyEntities.Spider;

public class spiderReverseMovement implements enemyMovementState{
 
    
    Spider spider;
    int ticks;

    public spiderReverseMovement(Spider spider){
        this.spider = spider;
        this.ticks = 0;

    }


    public void move(GameController game){}






}
