package dungeonmania.Goals;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.staticEntities.zombieSpawner;

public class EnemyGoal implements GoalComponent {

    public static int totalEnemies;  


    public static void settotalEnemies(int totalEnemies) {
        EnemyGoal.totalEnemies = totalEnemies;
    }
    

    @Override
    public boolean goalcompleted(GameController game) {
        //enemies destroyed
        int enemiesDestroyed = game.findPlayer().enemiesDestroyed;

        int numEnemySpawners = 0;
        //Check if all spawners are destroyed
        for (Entity entity : game.entities) {
            if (entity instanceof zombieSpawner) {
                numEnemySpawners += 1;
            }
        }
        if (enemiesDestroyed == totalEnemies && numEnemySpawners == 0) {
            return true;
        }
        return false; 
    }
    
    @Override
    public String toString() {
        return ":enemies";
    }

}
