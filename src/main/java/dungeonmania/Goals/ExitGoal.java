package dungeonmania.Goals;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.staticEntities.Exit;

public class ExitGoal implements GoalComponent{
    
    @Override
    public boolean checkgoalcompleted(GameController game) {
        for (Entity entity : game.getEntities()) {
            if (entity instanceof Exit) {
                if (entity.getPosition().equals(game.findPlayer().getPosition())) {
                    return true;
                }
            }
        }
        return false;
        
    }

    @Override
    public String toString() {
        return ":exit";
    }
}
