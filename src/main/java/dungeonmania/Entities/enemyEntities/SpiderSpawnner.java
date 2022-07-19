package dungeonmania.Entities.enemyEntities;
import java.util.Random;

import dungeonmania.GameController;
import dungeonmania.JSONExtract;
import dungeonmania.util.Position;

import java.util.concurrent.ThreadLocalRandom;

public class SpiderSpawnner {
    
    Random random = new Random();
    public static Integer spawnRate;

    /**
     * Spawn zombie
     * @param game
     */
    public static void spawn(GameController game) {

        // Get the current postion of player and spawn the spider in a random
        // Locaton around a 7 x 7 box around the player

        Position playerPosition = game.findPlayer().getPosition();

        int minX = playerPosition.getX() - 3 ;
        int maxX = playerPosition.getX() + 3 ;

        int minY = playerPosition.getY() - 3 ;
        int maxY = playerPosition.getY() + 3 ;

        int randomXcoordinate = ThreadLocalRandom.current().nextInt(minX, maxX + 1);
        int randomYcoordinate = ThreadLocalRandom.current().nextInt(minY, maxY + 1);


        String spider_id = JSONExtract.entities_created.toString();
        JSONExtract.increaseEntitiesCreates();

        Spider spider  = new Spider(spider_id , new Position(randomXcoordinate, randomYcoordinate));
        game.addentity(spider);

    }

    /**
     * Set spawn rate of zombie toaster
     */
    public static void setSpawnRate(Integer rate){
        SpiderSpawnner.spawnRate = rate;
    }  
}
