package dungeonmania.Entities.enemyEntities.enemyMovments;

import dungeonmania.Entities.enemyEntities.Spider;

public class spiderNormalMovement implements enemyMovementState{



    Spider spider;
    int ticks;

    public spiderNormalMovement(Spider spider){
        this.spider = spider;
        this.ticks = 0;

    }



    public void move(){

        int movementTick = this.getTicks();
        

    }
    

    public Spider getSpider() {
        return this.spider;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
    }

    public int getTicks() {
        return this.ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }



}
