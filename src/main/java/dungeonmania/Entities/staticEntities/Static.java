package dungeonmania.Entities.staticEntities;

import dungeonmania.Entities.Entity;
import dungeonmania.util.Position;

public class Static extends Entity{

    public Static(String id, String type, Position position) {
        super(id, type, position, false);
    }
    
    public Static(String id, String type, Position position, boolean isInterctable) {
        super(id, type, position, isInterctable);
    }

    public void setPos(Position pos) {
        super.setPosition(pos);
    }

    public Position getPos() {
        return super.getPosition();
    }

}
