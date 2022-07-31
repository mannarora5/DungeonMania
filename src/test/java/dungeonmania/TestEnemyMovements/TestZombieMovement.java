package dungeonmania.TestEnemyMovements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.collectableEntities.Invincibility;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyScaredMovement;
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

    @Test
    public void testNormalZombieMovement() {
    
        // Initialise Game and ZombieToast
        GameController game = new GameController();
        game.newGame("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");


        ZombieToast zombie = new ZombieToast("1", new Position(0, 0));
        List<Entity> entity_list = game.getEntities();
        entity_list.add(zombie);
        
        // Movement 1
        zombie.move(game);
        // Assert that zombie has moved from (0,0)
        assert(zombie.getpos().equals(new Position(0,0)) == false);
        Position prev_position = zombie.getpos();

        // Movement 2
        zombie.move(game);
        // Assert previous position is not the same as new position of zombie
        assert(zombie.getpos().equals(prev_position) == false);

    }

    @Test
    public void testScaredZombieMovement() {
        GameController game = new GameController();
        game.newGame("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");


        ZombieToast zombie = new ZombieToast("1", new Position(2, 2));
        List<Entity> entity_list = game.getEntities();
        entity_list.add(zombie);

        Player player = new Player("2", new Position(0, 0));
        Inventory inventory = new Inventory();

        Invincibility invincibility = new Invincibility("1", new Position(1, 1));
        inventory.addItem(invincibility);

        player.usePotion(invincibility);
        zombie.updateMovement(player);

        assert(zombie.checkZombieMovement(zombie.getCurrentMovementState()) == true);



        

        // assert(zombie.getCurrentMovementState() == new enemyScaredMovement(zombie));

        










    }
  
}
