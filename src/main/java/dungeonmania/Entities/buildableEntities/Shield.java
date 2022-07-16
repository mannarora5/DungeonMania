package dungeonmania.Entities.buildableEntities;

import dungeonmania.util.Position;

public class Shield extends Buildable{

    public static Integer shieldDuration;
    public static Integer shieldDefence;

    public Shield(String Id, Position position) {
        super(Id, "shield", position, false);
    }
    
    public static void setShielduration(Integer duration){
        Shield.shieldDuration = duration;
    }  

    public static void setSheildDefence(Integer defence){
        Shield.shieldDefence = defence;
    }  
    
}
