package dungeonmania.Entities.collectableEntities;

import dungeonmania.util.Position;


public class Invincibility extends Collectable {
    
    public static Integer potionDuration;



    public Invincibility(String Id, Position position) {
        super(Id, "invincibility_potion", position);
    }

    /**
     * Set the duration of the potion
     * @param duration
     */
    public static void setPotionDuration(Integer duration){
        Invincibility.potionDuration = duration;
    } 

    /**
     * Return the duration of the potion
     * @return
     */
    public Integer getPotionDuration(){
        return Invincibility.potionDuration;
    }    


}
