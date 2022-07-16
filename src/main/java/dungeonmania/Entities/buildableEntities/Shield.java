package dungeonmania.Entities.buildableEntities;

import dungeonmania.util.Position;

public class Shield extends Buildable{

    public static Integer shieldDuration;
    public static Integer shieldDefence;

    public Integer currentShieldDuration;

    public Shield(String Id, Position position) {
        super(Id, "shield", position, false);
        this.currentShieldDuration = Shield.shieldDuration;
    }
    
    public static void setShielduration(Integer duration){
        Shield.shieldDuration = duration;
    }  

    public static void setSheildDefence(Integer defence){
        Shield.shieldDefence = defence;
    }  


    public Integer getCurrentShieldDuration() {
        return this.currentShieldDuration;
    }

    public void setCurrentShieldDuration(Integer currentShieldDuration) {
        this.currentShieldDuration = currentShieldDuration;
    }

    
    
}
