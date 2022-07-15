package dungeonmania.Entities.collectableEntities;

import dungeonmania.util.Position;

public class Invisibility extends Collectable {


    private static Integer potionDuration;


    public Invisibility(String Id, Position position) {
        super(Id, "invisibility_potion", position);
    }


    public static void setPotionDuration(Integer duration){
        Invisibility.potionDuration = duration;
    } 

    public Integer getPotionDuration(){
        return Invisibility.potionDuration;
    }    

}
