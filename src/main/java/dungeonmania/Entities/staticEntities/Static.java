package dungeonmania.Entities.staticEntities;

import dungeonmania.Entities.Entity;
import dungeonmania.util.Position;
import java.util.UUID;

public class Static extends Entity{

    public Static(String type, Position position) {
        super(UUID.randomUUID().toString(), type, position, false);
    }
    

}
