package dungeonmania.Entities.collectableEntities;

import dungeonmania.util.Position;

public class Sword extends Collectable{

    public static Integer swordDuration;
    public static Integer swordAttack;

    public Sword(String Id, Position position) {
        super(Id, "sword", position);
    }

    public static void setSwordDuration(Integer duration){

        Sword.swordDuration = duration;

    }

    public static void setSwordAttack(Integer attack){

        Sword.swordAttack = attack;

    }


}
