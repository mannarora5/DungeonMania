package dungeonmania.Entities.staticEntities;

import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Boulder extends Static{

    public Boulder(String Id, Position position) {
        super(Id,"boulder", position);
    }

    /**
     * Move the boulder
     * @param direction
     * @param game
     * @return
     */
    public boolean move(Direction direction, GameController game) {

        Position nextPosition = super.getPos().translateBy(direction.getOffset());

        List<Entity> switchInPosition = game.entitiesSamePosition(super.getPos()).stream().
        filter(entity -> (entity instanceof Switch)).
        collect(Collectors.toList());

        Boolean on = false;
        Switch onSwitch = null;
        if(!switchInPosition.isEmpty()) {
            onSwitch = (Switch) switchInPosition.get(0);
            on = true;
        }       
        

        List<Entity> staticEntitiesNextPosition = game.entitiesSamePosition(nextPosition).stream().
        filter(entity -> (entity instanceof Static)).
        collect(Collectors.toList());

        if (staticEntitiesNextPosition.isEmpty()){
            super.setPos(nextPosition);
            if(on){
                onSwitch.setActivated(false);
            }
            return true;   
        }
        

        for (Entity entity: staticEntitiesNextPosition) {
            if (entity instanceof Boulder) {
                return false;
            }
            else if (entity instanceof Switch) {
                Switch s = (Switch) entity;
                if (!s.isActivated()){
                    super.setPos(nextPosition);
                    s.setActivated(true);
                    if(on){
                        onSwitch.setActivated(false);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }
}
