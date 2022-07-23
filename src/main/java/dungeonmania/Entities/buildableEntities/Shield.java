package dungeonmania.Entities.buildableEntities;

import dungeonmania.Entities.Player.Inventory;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;

public class Shield extends Buildable{

    public static Integer shieldDuration;
    public static Integer shieldDefence;

    private Integer currentShieldDuration;

    public Shield(String Id, Position position) {
        super(Id, "shield", position, false);
        this.currentShieldDuration = Shield.shieldDuration;
    }

    public static void buildShield(Inventory inventory) throws InvalidActionException {
        int woodAmount = inventory.quantity("wood");
        int keyAmount = inventory.quantity("key");
        int treasureAmount = inventory.quantity("treasure");


        if (woodAmount < 2) {

            throw new InvalidActionException("Not enough wood to build shield");

        } else if (keyAmount < 1 && treasureAmount < 1) {

            throw new InvalidActionException("Not enough treasure or key to build shield");

        } else {

            inventory.removeMultipleItems("wood", 2);

            if (treasureAmount > 0) {
                inventory.removeMultipleItems("treasure", 1);
            } else {
                inventory.removeMultipleItems("key", 1);
            }

            inventory.addItem(new Shield("1000", new Position(-1000, -1000)));
        }
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
