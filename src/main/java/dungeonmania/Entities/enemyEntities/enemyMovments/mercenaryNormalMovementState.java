package dungeonmania.Entities.enemyEntities.enemyMovments;

import dungeonmania.Entities.enemyEntities.Mercenary;

public class mercenaryNormalMovementState implements enemyMovementState {

    Mercenary mercenary;


    public mercenaryNormalMovementState(Mercenary mercenary){
        this.mercenary = mercenary;
    }


    public void move(){}

}
