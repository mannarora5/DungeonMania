package dungeonmania.Entities.staticEntities;

import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.util.Position;


public class Switch extends Static {

    public Switch(String Id, String type, Position position) {
        super(Id, type, position);
        
    }
    

    /**
     * Check if boulder has made floor switch triggered
     * @param game
     * @return
     */
    public boolean switchActivated(GameController game) {
        List<Entity> entities = game.entitiesSamePosition(this.getPosition());
        return entities.stream().anyMatch(Entity -> (Entity instanceof Boulder));
    }

}
