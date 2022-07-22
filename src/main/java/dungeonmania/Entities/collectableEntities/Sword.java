package dungeonmania.Entities.collectableEntities;

import dungeonmania.util.Position;

public class Sword extends Collectable{

    public static Integer swordDuration;
    public static Integer swordAttack;

    private Integer currentSwordDuration;

    public Sword(String Id, Position position) {
        super(Id, "sword", position);
        this.currentSwordDuration = Sword.swordDuration;
    }

    /**
     * Set duration of sword
     * @param duration
     */
    public static void setSwordDuration(Integer duration){

        Sword.swordDuration = duration;

    }

    /**
     * set sword damage 
     * @param attack
     */
    public static void setSwordAttack(Integer attack){

        Sword.swordAttack = attack;

    }


    public Integer getCurrentSwordDuration() {
        return this.currentSwordDuration;
    }

    public void setCurrentSwordDuration(Integer currentSwordDuration) {
        this.currentSwordDuration = currentSwordDuration;
    }



}
