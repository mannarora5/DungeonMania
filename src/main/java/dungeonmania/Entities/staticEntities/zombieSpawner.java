package dungeonmania.Entities.staticEntities;

import dungeonmania.util.Position;

public class zombieSpawner extends Static {

    public static Integer spawnRate;

    public zombieSpawner(String Id, Position position) {
        super(Id, "zombie_toast_spawner", position, true);
    }
    
    public static void setSpawnRate(Integer rate){
        zombieSpawner.spawnRate = rate;
    }    
}
