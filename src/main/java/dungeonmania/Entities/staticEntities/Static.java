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

    /**
     * Update position of static entity
     * @param pos
     */
    public void setPos(Position pos) {
        super.setPosition(pos);
    }

    /**
     * Return position of static entity
     * @return
     */
    public Position getPos() {
        return super.getPosition();
    }

}
