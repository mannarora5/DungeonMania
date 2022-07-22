package dungeonmania.Entities.enemyEntities.enemyMovments;

import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.enemyEntities.Spider;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.util.Position;

public class spiderNormalMovement implements enemyMovementState{


    private Spider spider;

    public spiderNormalMovement(Spider spider){
        this.spider = spider;
    }

    public void move(GameController game) {

        int tick = this.spider.getCurrentPostick();

        Position spider_position = this.spider.getPosition();

        int xVal = spider_position.getX();
        int yVal = spider_position.getY();

        Boolean foundBoulder = false;

        Position temp = new Position(xVal + 1, yVal);

        if (tick == 1 || tick == 8) {

            // Right
            temp = new Position(xVal + 1, yVal);

            foundBoulder = checkBoulderNextPos(game, temp);

        } else if (tick == 2 || tick == 3) {

            // Down
            temp = new Position(xVal, yVal + 1);

            foundBoulder = checkBoulderNextPos(game, temp);

        } else if (tick == 4 || tick == 5) {

            // Left
            temp = new Position(xVal - 1 , yVal);

            foundBoulder = checkBoulderNextPos(game, temp);

        } else if (tick == 6 || tick == 7 || tick == 0) {

            // Up

            temp = new Position(xVal, yVal - 1);

            foundBoulder = checkBoulderNextPos(game, temp);
        } 

        if (!foundBoulder) {
            this.spider.setPos(temp);
        }

        if (!foundBoulder){
             this.spider.increasetick();
        }

        if (this.spider.getCurrentPostick() == 9) {
            this.spider.setCurrentPostick(1);
        }

    }
    

    public Boolean checkBoulderNextPos(GameController game, Position position){


        List<Entity> entities = game.entitiesSamePosition(position);

        for (Entity entity: entities){
            if (entity instanceof Boulder){
                this.spider.setCurrentState(this.spider.getReverselState());
                this.spider.move(game);
                return true;
            }
            
        }

        return false;

    }

    public Spider getSpider() {
        return this.spider;
    }

    public void setSpider(Spider spider) {
        this.spider = spider;
    }


}
