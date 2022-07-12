package dungeonmania.TestEnemyMovements;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dungeonmania.Entities.enemyEntities.Spider;
import dungeonmania.Entities.enemyEntities.enemyMovments.spiderNormalMovement;
import dungeonmania.util.Position;






public class TestSpiderMovmentsUNIT {


    @Test
    @DisplayName("Normal Movement")
    public void normalSpiderMovmenttest(){

        Spider spider = new Spider("1",new Position(0, 0));

        spiderNormalMovement spiderNormalMovement = new spiderNormalMovement(spider);

        // No Movement
        assert(spider.getPos().equals(new Position(0, 0)));


        // Test entire movment
        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(0, -1)));


        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(1, -1)));

        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(1, 0)));

        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(1, 1)));

        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(0, 1)));

        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(-1, 1)));

        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(-1, 0)));

        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(-1, 1)));

        // Movement goes around in circles again


        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(0, -1)));


        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(1, -1)));

        spiderNormalMovement.move();
        assert(spider.getPos().equals(new Position(1, 0)));



    }

    @Test
    @DisplayName("Reverse Movement")
    public void reverseSpiderMovementtest(){

    }

    


    
}
