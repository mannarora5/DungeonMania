package dungeonmania.Entities.Player;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entities.buildableEntities.Bow;
import dungeonmania.Entities.buildableEntities.Shield;
import dungeonmania.Entities.collectableEntities.Arrow;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Key;
import dungeonmania.Entities.collectableEntities.Treasure;
import dungeonmania.Entities.collectableEntities.Wood;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Position;

public class Inventory {
    private List<Collectable> items;


    public Inventory(){
        this.items = new ArrayList<Collectable>();
    }

    /**
     * @param item a collectable entity
     * Adds collectable entity to list of items in inventory
     */
    public void addItem(Collectable item) {
        this.items.add(item);
    }

    /**
     * @param item a collectable entity
     * Adds collectable entity to list of items in inventory
     */
    public void addItems(List<Collectable> items) {
        this.items.addAll(items);
    }

    /**
     * @param Id  a certain id of a collectable entity
     * Removes collectable entity to list of items in inventory with matching Id
     */
    public void removeItem(String Id) {

        this.items.removeIf(item ->item.getId() == Id);
    }

    /**
     * @param Type collectable entity type to remove from inventory
     * @param Quantity how many of these types to remove
     * Removes collectable entity to list of items in inventory with matching Id
     */
    public boolean removeMultipleItems(String type, Integer quantity) {

        if (this.quantity(type) < quantity){
            return false;
        } else {
            Integer count = 0;
            List<Collectable> collectables = new ArrayList<Collectable>();

            for (Collectable item: this.items){
                if (count < quantity && item.getType() == type) {
                    collectables.add(item);
                    count++;
                }
            }

            for (Collectable item: collectables){
                this.items.remove(item);
            }

        }

        return true;
    }

    /**
     * @param Id  a certain id of a collectable entity
     * Returns collectable entity to list of items in inventory with matching Id
     */
    public Collectable getItem(String Id){

        return this.items.stream().filter(item -> (item.getId().equals(Id))).findFirst().orElse(null);

    }
    

    /**
     * @param Type  type of  collectable entity
     * Returns number of collectable entities of given type
     */
    public int quantity(String type) {

        return (int) this.items.stream().filter(item ->(item.getType() == type)).count();
    }


    /**
     * @return all the types of buildables that can be built given current inventory
     */
    public List<String> buildables() {

        List<String> builds = new ArrayList<String>();

        Integer noWood = 0;
        Integer noArrows = 0;
        Integer noKey = 0;
        Integer noTreasure = 0;

        for (Collectable item: items){

            if (item instanceof Wood) {
                noWood++;
            } else if (item instanceof Arrow) {
                noArrows++;
            } else if (item instanceof Key) {
                noKey++;
            } else if (item instanceof Treasure) {
                noTreasure++;
            }
        }

        if (noWood >= 1 && noArrows >= 3) {
            builds.add("bow");
        }
        if (noWood >= 2 && (noTreasure >= 1 || noKey >= 1)) {
            builds.add("shield");
        }

        return builds;
    }


    /**
     * Built the bow using wood and arrow
     * @throws InvalidActionException
     */
    public void buildbow() throws InvalidActionException{
        
        int woodAmount = this.quantity("wood");
        int arrowAmount = this.quantity("arrow");

        if (woodAmount < 1) {
            throw new InvalidActionException("Not enough wood to build bow");
        } else if (arrowAmount < 3) {
            throw new InvalidActionException("Not enough arrows to build bow");
        } else {
            this.removeMultipleItems("wood", 1);
            this.removeMultipleItems("arrow", 3);

            this.addItem(new Bow("1000", new Position(-1000, -1000)));
        }
    }

    /**
     * Build shield using wood, key and treausre
     * @throws InvalidActionException
     */
    public void buildshield() throws InvalidActionException{

        int woodAmount = this.quantity("wood");
        int keyAmount = this.quantity("key");
        int treasureAmount = this.quantity("treasure");


        if (woodAmount < 2) {

            throw new InvalidActionException("Not enough wood to build shield");

        } else if (keyAmount < 1 && treasureAmount < 1) {

            throw new InvalidActionException("Not enough treasure or key to build shield");

        } else {

            this.removeMultipleItems("wood", 2);

            if (treasureAmount > 0) {
                this.removeMultipleItems("treasure", 1);
            } else {
                this.removeMultipleItems("key", 1);
            }

            this.addItem(new Shield("1000", new Position(-1000, -1000)));
        }
        
    }


     /**
     * @return ItemRespnse objects for entities in inventory
     */
    public List<ItemResponse> InfoItemResponses() {
        List<ItemResponse> responses = new ArrayList<ItemResponse>();

        this.items.forEach(item -> {
            responses.add(new ItemResponse(item.getId(), item.getType()));
        });

        return responses;
    }

    /**
     * Check has key
     * @return
     */
    public boolean hasKey(){
        return this.items.stream().filter(e -> e instanceof Key).count() == 1;
    }

    /**
     * Get key
     * @return
     */
    public Key getKey(){
        return this.items.stream().filter(e -> e instanceof Key).map(e -> (Key) e).findFirst().orElse(null);
    }


    /**
     * Get items
     */
    public List<Collectable> getItems() {
        return this.items;
    }

    /**
     * Update items
     * @param items
     */
    public void setItems(List<Collectable> items) {
        this.items = items;
    }
}
