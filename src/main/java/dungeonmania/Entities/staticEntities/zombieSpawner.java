package dungeonmania.Entities.staticEntities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.GameController;
import dungeonmania.JSONExtract;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.util.Position;
import dungeonmania.util.*;

public class zombieSpawner extends Static {

    public static Integer spawnRate;


    public zombieSpawner(String Id, Position position) {
        super(Id, "zombie_toast_spawner", position, true);
    }
    
    public static void setSpawnRate(Integer rate){
        zombieSpawner.spawnRate = rate;
    }    

    // given a certain number of ticks add a zombie on a specific tick
    public void spawn(int ticks, GameController game) {
        List<Entity> ListEntities = game.getEntities();
        List<Position> illegalPositions = new ArrayList<>();
        List<Position> adjacentPositions = getPosition().getAdjacentPositions();
        
        // 1. ticks mod spawnrate (if == 0) (then spawn) (get tick from game.getticks)
        // 2. check position of zombie spawner (check up down left right for any wall, boulders, door)
        // 3. new zombie object and then add to game with the correct position 
        if (ticks % spawnRate == 0)  {
            for (Entity entity : ListEntities) {
                if (Position.isAdjacent(entity.getPosition(), getPos()) == true) {
                    if ((entity instanceof Wall) || (entity instanceof Boulder) || (entity instanceof Door)) {
                        illegalPositions.add(entity.getPosition());
                    }
                    adjacentPositions.removeAll(illegalPositions);
                }
                
                int zombie_id = JSONExtract.getEntities_created() + 1;
                String id = Integer.toString(zombie_id);
            
                ZombieToast zombie  = new ZombieToast(id , adjacentPositions.get(1));
                JSONExtract.setEntities_created(JSONExtract.getEntities_created() + 1);
                game.entities.add(zombie);

            }


            
        }

        


    }


}
