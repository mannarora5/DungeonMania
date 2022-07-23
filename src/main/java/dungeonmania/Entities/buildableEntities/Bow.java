package dungeonmania.Entities.buildableEntities;


import dungeonmania.Entities.Player.Inventory;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;

public class Bow extends Buildable{


    public static Integer bowDuration;

    private Integer currentBowDuration;

    public Bow(String Id, Position position) {
        super(Id, "bow", position, false);
        this.currentBowDuration = Bow.bowDuration;
    }



    public static void buildBow(Inventory inventory) throws InvalidActionException{

        int woodAmount = inventory.quantity("wood");
        int arrowAmount = inventory.quantity("arrow");

        if (woodAmount < 1) {
            throw new InvalidActionException("Not enough wood to build bow");
        } else if (arrowAmount < 3) {
            throw new InvalidActionException("Not enough arrows to build bow");
        } else {
            inventory.removeMultipleItems("wood", 1);
            inventory.removeMultipleItems("arrow", 3);

            inventory.addItem(new Bow("1000", new Position(-1000, -1000)));
        }

    }

    // Getters and Setters
    
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
