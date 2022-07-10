package TestInventoryUNiT;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.collectableEntities.Arrow;
import dungeonmania.Entities.collectableEntities.Wood;
import dungeonmania.util.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestInventoryUNIT {

    @Test
    @DisplayName("Test adding item")
    public void testAddItem(){
        Inventory inv = new Inventory();

        Wood w = new Wood("1", new Position(1, 1));

        inv.addItem(w);

        assertEquals(w,inv.getItems().indexOf(0));
    }

    @Test
    @DisplayName("Test removing item")
    public void testRemoveItem(){
        Inventory inv = new Inventory();

        Wood w = new Wood("1", new Position(1, 1));

        inv.addItem(w);

        assertEquals(w,inv.getItems().indexOf(0));

        inv.removeItem("1");

        assertEquals(true,inv.getItems().isEmpty());

    }

    
    @Test
    @DisplayName("Test removing multiple items")
    public void testRemoveMultipleItem(){
        Inventory inv = new Inventory();

        Wood w1 = new Wood("1", new Position(1, 1));
        Wood w2 = new Wood("2", new Position(1, 1));
        Wood w3 = new Wood("3", new Position(1, 1));
        Wood w4 = new Wood("4", new Position(1, 1));

        inv.addItem(w1);
        inv.addItem(w2);
        inv.addItem(w4);
        inv.addItem(w3);

        assertEquals(4,inv.getItems().size());

        inv.removeMultipleItems("wood",3);

        assertEquals(1,inv.getItems().size());

    }

    @Test
    @DisplayName("Test gettingitems")
    public void testGetItem(){
        Inventory inv = new Inventory();

        Wood w1 = new Wood("1", new Position(1, 1));
        Wood w2 = new Wood("2", new Position(1, 1));
        Wood w3 = new Wood("3", new Position(1, 1));
        Wood w4 = new Wood("4", new Position(1, 1));

        inv.addItem(w1);
        inv.addItem(w2);
        inv.addItem(w4);
        inv.addItem(w3);

        assertEquals(4,inv.getItems().size());

        assertEquals(w3, inv.getItem("3"));

    }

    @Test
    @DisplayName("Test correct return of buildables")
    public void testCorrectBuildables(){
        Inventory inv = new Inventory();

        Wood w1 = new Wood("1", new Position(1, 1));
        Wood w2 = new Wood("2", new Position(1, 1));
        Wood w3 = new Wood("3", new Position(1, 1));
        Wood w4 = new Wood("4", new Position(1, 1));

        Arrow a1 = new Arrow("1", new Position(1, 1));
        Arrow a2 = new Arrow("2", new Position(1, 1));
        Arrow a3 = new Arrow("3", new Position(1, 1));
        Arrow a4 = new Arrow("4", new Position(1, 1));

        inv.addItem(w1);
        inv.addItem(w2);
        inv.addItem(w4);
        inv.addItem(w3);

        inv.addItem(a1);
        inv.addItem(a2);
        inv.addItem(a4);
        inv.addItem(a3);

        assertEquals(8,inv.getItems().size());

        List<String> actual_builables = new ArrayList<String>();

        actual_builables.add("bow");
        actual_builables.add("sheild");

        assertEquals(actual_builables, inv.buildables());


        inv.removeMultipleItems("arrow",4);

        actual_builables.remove("bow");

        assertEquals(actual_builables, inv.buildables());

    }

    
    
}
