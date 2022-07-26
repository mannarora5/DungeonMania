package dungeonmania.TestEnemyMovements;

import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.util.Position;

public class TestZombieMovement {
    @Test
    public void testNoZombieMovement() {
        // Initialise Game and ZombieToast
        GameController game = new GameController();
        ZombieToast zombie = new ZombieToast("1", new Position(0, 0));

        // No Movement Initially --> Remain at (0,0)
        assert(zombie.getpos().equals(new Position(0, 0)));
    }

    // @Test
    // public void testNormalZombieMovement() {
    //     // make dungeon
    //     DungeonManiaController dungeon = new DungeonManiaController();

    //     // Initialise Game and ZombieToast
    //     GameController game = new GameController();
    //     game.newGame("dungeon", "1");


    //     ZombieToast zombie = new ZombieToast("1", new Position(0, 0));
    //     List<Entity> entity_list = game.getEntities();
    //     entity_list.add(zombie);
        
    //     // Movement 1
    //     zombie.move(game);
    //     // Assert that zombie has moved from (0,0)
    //     assert(zombie.getpos().equals(new Position(0,0)) == false);
    //     Position prev_position = zombie.getpos();

    //     // Movement 2
    //     zombie.move(game);
    //     // Assert previous position is not the same as new position of zombie
    //     assert(zombie.getpos().equals(prev_position) == false);

    // }
  
}
