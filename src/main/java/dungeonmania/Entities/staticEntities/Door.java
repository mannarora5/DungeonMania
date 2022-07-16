package dungeonmania.Entities.staticEntities;

import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.collectableEntities.Key;
import dungeonmania.util.Position;

public class Door extends Static {

    private Integer key;
    private boolean open;

    public Door(String Id, Position position, Integer key) {
        super(Id, "door", position);
        this.key = key;
        this.open = false;
    }

    /**
     * Open the door
     * @param inventory
     * @return
     */
    public boolean openDoor(Inventory inventory){

        if(inventory.hasKey()){
            Key k = inventory.getKey();
            if(this.key == k.getDigit()){
                setOpen(true);
                super.setType("door_open");
                inventory.removeMultipleItems("key", 1); // Can remove later with use interface
                return true;
            } else{
                return false;
            } 
        }
        return false;
    }

    /**
     * returns current key
     * @return
     */
    public Integer getKey() {
        return this.key;
    }

    /**
     * Update key
     * @param key
     */
    public void setKey(Integer key) {
        this.key = key;
    }

    /**
     * Check if door is open
     * @return
     */
    public boolean isOpen() {
        return this.open;
    }

    /**
     * Return whether door open
     * @return
     */
    public boolean getOpen() {
        return this.open;
    }

    /**
     * Update whether door is opened
     * @param open
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    
}
