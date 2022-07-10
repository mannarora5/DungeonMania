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

    // Getters and Setters
    public Integer getKey() {
        return this.key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean getOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    
}
