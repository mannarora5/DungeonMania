package dungeonmania.Entities.enemyEntities.enemyMovments;

import dungeonmania.Entities.enemyEntities.Mercenary;

public class mercenaryAllyMovementState implements enemyMovementState{

    Mercenary mercenary;


    public mercenaryAllyMovementState(Mercenary mercenary){
        this.mercenary = mercenary;
    }


    public void move(){}

}
