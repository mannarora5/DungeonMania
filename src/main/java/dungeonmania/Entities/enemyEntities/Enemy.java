package dungeonmania.Entities.enemyEntities;

import dungeonmania.Entities.Entity;
import dungeonmania.util.Position;

public class Enemy extends Entity{

    public Enemy(String id, String type, Position position, boolean isInterctable) {
        super(id, type, position, isInterctable);
    }
    
    public Enemy(String id, String type, Position position) {
        super(id, type, position, false);
    }
    

    public void setpos(Position pos){
        super.setPosition(pos);
    }

    public Position getpos(){
        return super.getPosition();
    }

}
