package dungeonmania.Entities.buildableEntities;


import dungeonmania.util.Position;

public class Bow extends Buildable{


    public static Integer bowDuration;

    public Bow(String Id, Position position) {
        super(Id, "bow", position, false);
    }
    
    public static void setbowDuration(Integer duration){
        Bow.bowDuration = duration;
    }  
    
}
