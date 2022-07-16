package dungeonmania.Entities.staticEntities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.GameController;
import dungeonmania.JSONExtract;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.util.Position;

public class zombieSpawner extends Static {

    public static Integer spawnRate;


    public zombieSpawner(String Id, Position position) {
        super(Id, "zombie_toast_spawner", position, true);
    }
    

    /**
     * Spawn the zomie
     * @param game
     */
    public void spawn( GameController game) {

        List<Entity> ListEntities = game.getEntities();
        List<Position> illegalPositions = new ArrayList<>();
        List<Position> cardinalpPositions = new ArrayList<>();

        cardinalpPositions.add(new Position(getX()  , getY()-1));
        cardinalpPositions.add(new Position(getX()-1  , getY()));
        cardinalpPositions.add(new Position(getX()  , getY()+1));
        cardinalpPositions.add(new Position(getX()+1  , getY()));
        
        for (Entity entity : ListEntities) {

            if (Position.isAdjacent(entity.getPosition(), getPos()) == true) {
                if ((entity instanceof Wall) || (entity instanceof Boulder) || (entity instanceof Door)) {
                    illegalPositions.add(entity.getPosition());
                }
            }
        
        }

        cardinalpPositions.removeAll(illegalPositions);

        if (cardinalpPositions.isEmpty()){
            return;
        }

        String zombie_id = JSONExtract.entities_created.toString();
        JSONExtract.increaseEntitiesCreates();

        ZombieToast zombie  = new ZombieToast(zombie_id ,cardinalpPositions.get(1));
        game.addentity(zombie);

    

    }

    /**
     * Set the spawn rate
     * @param rate
     */
    public static void setSpawnRate(Integer rate){
        zombieSpawner.spawnRate = rate;
    }    


}
