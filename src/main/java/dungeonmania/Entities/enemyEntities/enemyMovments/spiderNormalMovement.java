package dungeonmania.Entities.enemyEntities.enemyMovments;

import dungeonmania.GameController;
import dungeonmania.Entities.enemyEntities.Spider;
import dungeonmania.util.Position;

public class spiderNormalMovement implements enemyMovementState{


    public Spider spider;
    public int currentPostick;

    public spiderNormalMovement(Spider spider){
        this.spider = spider;
        this.currentPostick = 0;
    }



    public void move(GameController game) {

        int tick = getCurrentPostick();

        Position spider_position = this.spider.getPosition();

        int xVal = spider_position.getX();
        int yVal = spider_position.getY();

        if (tick == 1 || tick == 8) {

            this.spider.setPos(new Position(xVal + 1, yVal));

        } else if (tick == 2 || tick == 3) {

            this.spider.setPos(new Position(xVal, yVal + 1));


        } else if (tick == 4 || tick == 5) {

            this.spider.setPos(new Position(xVal - 1 , yVal));

        } else if (tick == 6 || tick == 7 || tick == 0) {

            this.spider.setPos(new Position(xVal, yVal - 1));

        } 

        this.currentPostick += 1;

        if (this.currentPostick == 9) {
            this.currentPostick = 1;
        }

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
