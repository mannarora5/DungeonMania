package dungeonmania.Entities.enemyEntities.enemyMovement;

import dungeonmania.GameController;
import dungeonmania.Entities.enemyEntities.Mercenary;

public class mercenaryNormalMovementState implements enemyMovementState {

    Mercenary mercenary;


    public mercenaryNormalMovementState(Mercenary mercenary){
        this.mercenary = mercenary;
    }


    public void move(GameController game){}

}
