package dungeonmania.Entities.Player;

import java.util.ArrayList;
import java.util.List;


import dungeonmania.Entities.collectableEntities.Arrow;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Key;
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

        return this.items.stream().filter(item -> (item.getId() == Id)).findFirst().orElse(null);

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
     * @return ItemRespnse objects for entities in inventory
     */
    public List<ItemResponse> InfoItemResponses() {
        List<ItemResponse> responses = new ArrayList<ItemResponse>();

        this.items.forEach(item -> {
            responses.add(new ItemResponse(item.getId(), item.getType()));
        });

        return responses;
    }


    public boolean hasKey(){
        return this.items.stream().filter(e -> e instanceof Key).count() == 1;
    }



    // Getters and Setters//


    public List<Collectable> getItems() {
        return this.items;
    }

    public void setItems(List<Collectable> items) {
        this.items = items;
    }

    
}
