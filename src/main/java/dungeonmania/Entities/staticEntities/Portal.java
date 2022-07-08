package dungeonmania.Entities.staticEntities;

import dungeonmania.util.Position;

public class Portal extends Static {
    String colour; 

    public Portal(String Id, Position position, String colour) {
        super(Id, "portal", position);
        this.colour = colour; 
    }
    
}
