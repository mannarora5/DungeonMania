package dungeonmania.Entities.Player;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Inventory {
    private List<Collectable> items;

    /**
     * @param item a collectable entity
     * Adds collectable entity to list of items in inventory
     */
    public void addItem(Collectable item) {
    }

    /**
     * @param item a collectable entity
     * Adds collectable entity to list of items in inventory
     */
    public void addItems(List<Collectable> items) {
    }

    /**
     * @param Id  a certain id of a collectable entity
     * Removes collectable entity to list of items in inventory with matching Id
     */
    public boolean removeItem(String Id) {
        return true;
    }

    /**
     * @param Type collectable entity type to remove from inventory
     * @param Quantity how many of these types to remove
     * Removes collectable entity to list of items in inventory with matching Id
     */
    public boolean removeMultipleItems(String type, Integer quantity) {
        return true;
    }

    /**
     * @param Id  a certain id of a collectable entity
     * Returns collectable entity to list of items in inventory with matching Id
     */
    public Collectable getItem(String Id){

        return new Collectable("1", "Blank", new Position(99, 99));

    }
    

    /**
     * @param Type  type of  collectable entity
     * Returns number of collectable entities of given type
     */
    public Integer quantity(String type) {
        return 1;
    }


    /**
     * @return all the types of buildables that can be built given current inventory
     */
    public List<String> buildables() {
        return new ArrayList<String>();
    }


     /**
     * @return ItemRespnse objects for entities in inventory
     */
    public List<ItemResponse> InfoItemResponses() {
        return new ArrayList<ItemResponse>();
    }



    // Getters and Setters//


    public List<Collectable> getItems() {
        return this.items;
    }

    public void setItems(List<Collectable> items) {
        this.items = items;
    }

    
}
