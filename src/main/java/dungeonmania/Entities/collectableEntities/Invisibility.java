package dungeonmania.Entities.collectableEntities;

import dungeonmania.util.Position;

public class Invisibility extends Collectable {


    private static Integer potionDuration;


    public Invisibility(String Id, Position position) {
        super(Id, "invisibility_potion", position);
    }

    /**
     * Set the duration of the potion
     * @param duration
     */
    public static void setPotionDuration(Integer duration){
        Invisibility.potionDuration = duration;
    } 

    /**
     * Return the duration of the potion
     * @return
     */
    public Integer getPotionDuration(){
        return Invisibility.potionDuration;
    }    

}
