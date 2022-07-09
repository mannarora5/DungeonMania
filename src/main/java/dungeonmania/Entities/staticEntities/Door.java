package dungeonmania.Entities.staticEntities;

import dungeonmania.util.Position;

public class Door extends Static {

    private Integer key;
    private boolean open;

    public Door(String Id, Position position, Integer key) {
        super(Id, "door", position);
        this.key = key;
        this.open = false;
    }


    // Getters and Setters
    public Integer getKey() {
        return this.key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    
}
