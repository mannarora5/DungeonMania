package dungeonmania.TestEnemyMovements;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.Test;

import dungeonmania.GameController;
import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.collectableEntities.Invincibility;
import dungeonmania.Entities.collectableEntities.Invisibility;
import dungeonmania.Entities.collectableEntities.Treasure;
import dungeonmania.Entities.enemyEntities.Mercenary;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;

public class TestMercenaryMovements {
    @Test
    public void testScaredMercenaryMovement() {
        // Initialise Game and ZombieToast
        GameController game = new GameController();
        game.newGame("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");


        Mercenary mercenary = new Mercenary("1", new Position(0, 0));
        Player player = new Player("2", new Position(0, 0));
        Inventory inventory = new Inventory();

        Invincibility invincibility = new Invincibility("1", new Position(1, 1));
        inventory.addItem(invincibility);

        player.usePotion(invincibility);
        mercenary.updateMovement(player);
        assert(mercenary.checkMercenaryMovement(mercenary.getCurrentMovementState()) == 4);
    }

    @Test
    public void testRandomMercenaryMovement() {
        // Initialise Game and ZombieToast
        GameController game = new GameController();
        game.newGame("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");


        Mercenary mercenary = new Mercenary("1", new Position(1, 1));
        Player player = new Player("2", new Position(0, 0));
        Inventory inventory = new Inventory();

        Invisibility invisibility = new Invisibility("1", new Position(1, 1));
        inventory.addItem(invisibility);

        player.usePotion(invisibility);
        mercenary.updateMovement(player);
        assert(mercenary.checkMercenaryMovement(mercenary.getCurrentMovementState()) == 3);

    }

    @Test
    public void testAllyMercenaryMovement() throws InvalidActionException {
        GameController game = new GameController();
        game.newGame("d_battleTest_basicMercenary", "c_battleTests_basicMercenaryMercenaryDies");


        Mercenary mercenary = new Mercenary("1", new Position(0, 1));
        Player player = new Player("1", new Position(0, 0));
        Inventory inventory = new Inventory();
        Treasure t1 = new Treasure("1", new Position(2, 1));
        Treasure t2 = new Treasure("1", new Position(3, 1));
        Treasure t3 = new Treasure("1", new Position(1,2));
        Treasure t4 = new Treasure("1", new Position(1, 3));
        inventory.addItem(t1);
        inventory.addItem(t2);
        inventory.addItem(t4);
        inventory.addItem(t3);
        player.setInventory(inventory);
        mercenary.bribe(game, player);
        assert(mercenary.checkMercenaryMovement(mercenary.getCurrentMovementState()) == 2);
    }
}
