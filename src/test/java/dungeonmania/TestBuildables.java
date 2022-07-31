package dungeonmania;

import org.junit.jupiter.api.Test;

import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.buildableEntities.Bow;
import dungeonmania.Entities.buildableEntities.Shield;
import dungeonmania.Entities.collectableEntities.Arrow;
import dungeonmania.Entities.collectableEntities.Key;
import dungeonmania.Entities.collectableEntities.Treasure;
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

    @Test
    public void testShieldBuildableWithKey() throws InvalidActionException {
        Inventory inventory = new Inventory();
        Wood w1 = new Wood("1", new Position(1, 1)); 
        Wood w2 = new Wood("2", new Position(1, 0));

        inventory.addItem(w1);
        inventory.addItem(w2);

        Key k = new Key("1", new Position(0,0), 1);

        inventory .addItem(k);
        assert(inventory.quantity("key") == 1);
        Shield.buildShield(inventory);

        assert(inventory.quantity("shield") == 1);
    }

    @Test
    public void testShieldBuildableWithoutKey() throws InvalidActionException {
        Inventory inventory = new Inventory();
        Wood w1 = new Wood("1", new Position(1, 1)); 
        Wood w2 = new Wood("2", new Position(1, 0));

        inventory.addItem(w1);
        inventory.addItem(w2);

        Treasure t = new Treasure("1", new Position(0, 0));

        inventory .addItem(t);
        Shield.buildShield(inventory);

        assert(inventory.quantity("shield") == 1);
    }


    
}
