package dungeonmania.Entities.enemyEntities.enemyMovement;

import dungeonmania.GameController;
import dungeonmania.Entities.enemyEntities.Spider;

public class spiderNormalMovement implements enemyMovementState{



    Spider spider;
    int ticks;

    public spiderNormalMovement(Spider spider){
        this.spider = spider;
        this.ticks = 0;

    }


    /**
     * Move the spider
     */
    public void move(GameController game){

        int movementTick = this.ticks % 8 ;

        if (movementTick == 0 || movementTick == 1) {

            this.spider.getPos().translateBy(1, 0);

        } else if (movementTick == 2 || movementTick == 3) {

            this.spider.getPos().translateBy(0, 1);


        } else if (movementTick == 4 || movementTick == 5) {

            this.spider.getPos().translateBy(-1, 0);


        } else if (movementTick == 6 || movementTick == 7 || movementTick == 0) {

            this.spider.getPos().translateBy(0, -1);


        } 



        this.ticks += 1;

    }
    
    /**
     * Get spider
     * @return
     */
    public Spider getSpider() {
        return this.spider;
    }

    /**
     * Set spider
     * @param spider
     */
    public void setSpider(Spider spider) {
        this.spider = spider;
    }

    /**
     * Get ticks
     * @return
     */
    public int getTicks() {
        return this.ticks;
    }

    /**
     * Set ticks
     * @param ticks
     */
    public void setTicks(int ticks) {
        this.ticks = ticks;
    }



}