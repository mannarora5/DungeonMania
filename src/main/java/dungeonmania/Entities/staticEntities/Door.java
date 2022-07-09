package dungeonmania.Entities.staticEntities;

import dungeonmania.util.Position;

public class Door extends Static {

    private Integer key;

    public Door(String Id, Position position, Integer key) {
        super(Id, "door", position);
        this.key = key;
    }


    // Getters and Setters
    public Integer getKey() {
        return this.key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    
}
