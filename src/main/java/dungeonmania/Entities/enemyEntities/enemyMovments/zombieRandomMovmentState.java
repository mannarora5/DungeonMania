package dungeonmania.Entities.enemyEntities.enemyMovments;

import dungeonmania.Entities.enemyEntities.ZombieToast;

public class zombieRandomMovmentState implements enemyMovementState{


    ZombieToast zombie;


    public zombieRandomMovmentState(ZombieToast zombie){
        this.zombie = zombie;
    }


    public void move(){}

}
