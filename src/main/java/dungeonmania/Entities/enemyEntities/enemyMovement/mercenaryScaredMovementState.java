package dungeonmania.Entities.enemyEntities.enemyMovement;

import dungeonmania.Entities.enemyEntities.Mercenary;

public class mercenaryScaredMovementState implements enemyMovementState{

    Mercenary mercenary;


    public mercenaryScaredMovementState(Mercenary mercenary){
        this.mercenary = mercenary;
    }


    public void move(){}

    
}
