package dungeonmania;

import org.junit.jupiter.api.Test;

import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.buildableEntities.Bow;
import dungeonmania.Entities.collectableEntities.Arrow;
import dungeonmania.Entities.collectableEntities.Wood;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;

public class TestBuildables {

    @Test
    public void testBowBuildable() throws InvalidActionException {
    Inventory inventory = new Inventory();
    Wood w = new Wood("1", new Position(1, 1));
    Arrow a1 = new Arrow("1", new Position(1, 2));
    Arrow a2 = new Arrow("1", new Position(2, 1));
    Arrow a3 = new Arrow("1", new Position(0, 2));


    inventory.addItem(w);
    inventory.addItem(a1);
    inventory.addItem(a2);
    inventory.addItem(a3);

    assert(inventory.quantity("wood") == 1);
    assert(inventory.quantity("arrow") == 3);
    

    Bow.buildBow(inventory);
    assert(inventory.quantity("bow") == 1);
    }

    
    
}
