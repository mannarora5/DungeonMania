package dungeonmania.Goals;

import java.io.Serializable;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.staticEntities.Switch;

public class BouldersGoal implements GoalComponent, Serializable {
    
    @Override
    public boolean checkgoalcompleted(GameController game) {
        int numSwitchNotActivated = 0;
        //Check if a switch is not activated

        for (Entity entity: game.getEntities()) {
            if (entity instanceof Switch) {
                Switch s = (Switch) entity;
                if (s.getActivated() == false) {
                    numSwitchNotActivated += 1;
                }
            }
        }
        if (numSwitchNotActivated != 0) {
            return false;
        }
        return true; 

    }

    @Override
    public String toString() {
        return ":boulders";
    }

}
