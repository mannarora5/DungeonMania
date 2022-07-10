package dungeonmania.Goals;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.staticEntities.Exit;

public class ExitGoal implements GoalComponent{
    
    @Override
    public boolean goalcompleted(GameController game) {
        for (Entity entity : game.entities) {
            if (entity instanceof Exit) {
                Exit e = (Exit) entity;
                if (e.getPosition() == game.findPlayer().getPosition()) {
                    return true;
                }
            }
        }
        return false;
        
    }

}
