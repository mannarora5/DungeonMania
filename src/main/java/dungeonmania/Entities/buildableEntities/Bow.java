package dungeonmania.Entities.buildableEntities;


import dungeonmania.util.Position;

public class Bow extends Buildable{


    public static Integer bowDuration;

    public Integer currentBowDuration;

    public Bow(String Id, Position position) {
        super(Id, "bow", position, false);
        this.currentBowDuration = Bow.bowDuration;
    }
    
    public static void setbowDuration(Integer duration){
        Bow.bowDuration = duration;
    }  


    public Integer getCurrentBowDuration() {
        return this.currentBowDuration;
    }

    public void setCurrentBowDuration(Integer currentBowDuration) {
        this.currentBowDuration = currentBowDuration;
    }

    
}
