package dungeonmania.Entities.Player;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.Entities.buildableEntities.Bow;
import dungeonmania.Entities.buildableEntities.MidnightArmour;
import dungeonmania.Entities.buildableEntities.Sceptre;
import dungeonmania.Entities.buildableEntities.Shield;
import dungeonmania.Entities.collectableEntities.Arrow;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Key;
import dungeonmania.Entities.collectableEntities.Sunstone;
import dungeonmania.Entities.collectableEntities.Sword;
import dungeonmania.Entities.collectableEntities.Treasure;
import dungeonmania.Entities.collectableEntities.Wood;
import dungeonmania.response.models.ItemResponse;

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
        Integer noSunStone = 0;
        Integer noSword = 0;

        for (Collectable item: items){

            if (item instanceof Wood) {
                noWood++;
            } else if (item instanceof Arrow) {
                noArrows++;
            } else if (item instanceof Key) {
                noKey++;
            } else if (item instanceof Treasure) {
                noTreasure++;
            } else if (item instanceof Sunstone){
                noSunStone++;
            } else if (item instanceof Sword){
                noSword++;
            }
        }


        if (noWood >= 1 && noArrows >= 3) {
            builds.add("bow");
        }

        if (noSunStone >= 1 && noSword >= 1) {
            builds.add("midnight_armour");
        }

        if (noWood >= 2 && (noTreasure >= 1 || noKey >= 1)) {
            builds.add("shield");
        }

        if ((noWood >= 1 || noArrows >= 2) && (noTreasure >= 1 || noKey >= 1) && noSunStone >= 1) {
            builds.add("sceptre");
        } else if ((noWood >= 1 || noArrows >= 2) && noSunStone >= 2){
            builds.add("sceptre");

        }


        return builds;
    }



 
    public Bow getBow(){
        
        for (Collectable i: this.items) {
            if (i instanceof Bow) {
                return (Bow)i;
            }
        }

        return null;
    }

    public Shield getShield(){

        for (Collectable i: this.items) {
            if (i instanceof Shield) {
                return (Shield)i;
            }
        }

        return null;

    }

    public Sword getSword(){

        for (Collectable i: this.items) {
            if (i instanceof Sword) {
                return (Sword)i;
            }
        }

        return null;   
    }

    public MidnightArmour getArmour(){

        for (Collectable i: this.items) {
            if (i instanceof MidnightArmour) {
                return (MidnightArmour)i;
            }
        }

        return null;   
    }

    public Sceptre getSceptre(){

        for (Collectable i: this.items) {
            if (i instanceof Sceptre) {
                return (Sceptre)i;
            }
        }

        return null;   
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
     * Get Sun stone
     * @return
     */
    public Sunstone getSunstone() {
        for (Collectable i: this.items) {
            if (i instanceof Sunstone) {
                return (Sunstone)i;
            }
        }

        return null;   
    }

    /**
     * Check if inventory has a Sunstone
     * @return
     */
    public boolean hasSunStone() {
        for (Collectable i: this.items) {
            if (i instanceof Sunstone) {
                return true;
            }
        }
        return false; 
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
