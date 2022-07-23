package dungeonmania.Entities.buildableEntities;

import dungeonmania.Entities.Player.Inventory;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;

public class Sceptre extends Buildable {

    public static Integer mindControlDuration;


    public Sceptre(String Id, Position position) {
        super(Id, "sceptre", position, false);
    }


    public static void buildSceptre(Inventory inventory) throws InvalidActionException {

        int woodAmount = inventory.quantity("wood");
        int keyAmount = inventory.quantity("key");
        int treasureAmount = inventory.quantity("treasure");
        int arrowAmount = inventory.quantity("arrow");
        int noSunStone = inventory.quantity("sun_stone");

        if (woodAmount < 1 && arrowAmount < 2) {

            throw new InvalidActionException("Not enough wood or arrows to build sceptre");

        } else if ((keyAmount < 1 && treasureAmount < 1) && noSunStone < 2) {

            throw new InvalidActionException("Not enough treasure or key to build shield");

        } else if (noSunStone < 1) {
            throw new InvalidActionException("Not enough sun stone to build sceptre");

        } else {

            if (woodAmount < 1){
                inventory.removeMultipleItems("arrow", 2);   
            } else {
                inventory.removeMultipleItems("wood", 1);   
            }

            if (noSunStone >= 2){
                // One Stone acts as treasure or key and the other as a sunstone
                inventory.removeMultipleItems("sun_stone", 1);   
                inventory.addItem(new Sceptre("1000", new Position(-1000, -1000)));
                return;
            }

            if (treasureAmount >= 1){
                inventory.removeMultipleItems("treasure", 1);   
            } else {
                inventory.removeMultipleItems("key", 1);   
            }

            inventory.removeMultipleItems("sun_stone", 1);   
            inventory.addItem(new Sceptre("1000", new Position(-1000, -1000)));


        }
    }

    public static void setMindControlDuration(Integer duration){
        Sceptre.mindControlDuration = duration;
    } 
}
