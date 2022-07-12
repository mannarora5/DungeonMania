package dungeonmania.Entities.enemyEntities.enemyMovments;

import dungeonmania.Entities.enemyEntities.ZombieToast;

public class zombieScaredMovementState implements enemyMovementState{


    ZombieToast zombie;


    public zombieScaredMovementState(ZombieToast zombie){
        this.zombie = zombie;
    }


    public void move(){}
   
}
