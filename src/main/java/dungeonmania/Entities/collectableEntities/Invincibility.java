package dungeonmania.Entities.collectableEntities;

import dungeonmania.util.Position;


public class Invincibility extends Collectable {
    
    private static Integer potionDuration;



    public Invincibility(String Id, Position position) {
        super(Id, "invincibility_potion", position);
    }



    public static void setPotionDuration(Integer duration){
        Invincibility.potionDuration = duration;
    } 

    public int getPotionDuration() {
        return potionDuration;
    }

    public void use() {
        /*
        if (playerState == NormalState) {
            player.usePotion(this);
        } else {
            player.queuePotion(this);
        }
         */
    }


}
