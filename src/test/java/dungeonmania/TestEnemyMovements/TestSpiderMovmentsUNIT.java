package dungeonmania.TestEnemyMovements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.Entities.enemyEntities.Spider;
import dungeonmania.util.Position;






public class TestSpiderMovmentsUNIT {


    @Test
    @DisplayName("Normal Movement")
    public void normalSpiderMovmenttest(){

        Spider spider = new Spider("1",new Position(0, 0));

        // No Movement
        assert(spider.getPos().equals(new Position(0, 0)));


        // Test entire movment
        spider.move();
        assert(spider.getPos().equals(new Position(0, -1)));


        spider.move();
        assert(spider.getPos().equals(new Position(1, -1)));

        spider.move();
        assert(spider.getPos().equals(new Position(1, 0)));

        spider.move();
        assert(spider.getPos().equals(new Position(1, 1)));

        spider.move();
        assert(spider.getPos().equals(new Position(0, 1)));

        spider.move();
        assert(spider.getPos().equals(new Position(-1, 1)));

        spider.move();
        assert(spider.getPos().equals(new Position(-1, 0)));

        spider.move();
        System.out.println(spider.getPos());

        assert(spider.getPos().equals(new Position(-1, -1)));

        // movement goes around in circles again


        spider.move();
        assert(spider.getPos().equals(new Position(0, -1)));


        spider.move();
        assert(spider.getPos().equals(new Position(1, -1)));

        spider.move();
        assert(spider.getPos().equals(new Position(1, 0)));



    }

    @Test
    @DisplayName("Reverse Movement")
    public void reverseSpiderMovementtest(){

    }




    
}
