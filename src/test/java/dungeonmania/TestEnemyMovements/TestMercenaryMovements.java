package dungeonmania.TestEnemyMovements;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.junit.jupiter.api.Test;

import dungeonmania.GameController;
import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.collectableEntities.Invincibility;
import dungeonmania.Entities.enemyEntities.Mercenary;
import dungeonmania.Entities.enemyEntities.ZombieToast;
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

    

    
}
