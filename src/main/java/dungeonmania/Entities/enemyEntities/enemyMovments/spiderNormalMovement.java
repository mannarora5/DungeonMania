package dungeonmania.Entities.enemyEntities.enemyMovments;

import dungeonmania.Entities.enemyEntities.Spider;

public class spiderNormalMovement implements enemyMovementState{


    Spider spider;
    int currentPostick;

    public spiderNormalMovement(Spider spider){
        this.spider = spider;
        this.currentPostick = 0;
    }



    public void move(){

        if (movementTick == 0 || movementTick == 1) {

            this.spider.getPos().translateBy(1, 0);

        } else if (movementTick == 2 || movementTick == 3) {

            this.spider.getPos().translateBy(0, 1);


        } else if (movementTick == 4 || movementTick == 5) {

            this.spider.getPos().translateBy(-1, 0);


        } else if (movementTick == 6 || movementTick == 7 || movementTick == 0) {

            this.spider.getPos().translateBy(0, -1);


        } 

        this.currentPostick += 1;

    }
    

    public Spider getSpider() {
        return this.spider;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
    }


    public int getCurrentPostick() {
        return this.currentPostick;
    }

    public void setCurrentPostick(int currentPostick) {
        this.currentPostick = currentPostick;
    }

}
