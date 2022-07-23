package dungeonmania.Entities.buildableEntities;

import dungeonmania.Entities.Player.Inventory;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;

public class MidnightArmour extends Buildable {
    
    public static Integer armourAttack;
    public static Integer armourDefence;


    public MidnightArmour(String Id, Position position) {
        super(Id, "midnight_armour", position, false);
    }


    public static void buildArmour(Inventory inventory) throws InvalidActionException {

        int swordAmount = inventory.quantity("sword");
        int sunAmount = inventory.quantity("sun_stone");

        if (swordAmount < 1) {

            throw new InvalidActionException("No sword to build armour");

        } else if (sunAmount < 1) {

            throw new InvalidActionException("No sun_stone to build armour");

        } else {

            inventory.removeMultipleItems("sword", 1);
            inventory.removeMultipleItems("sun_stone", 1);

            inventory.addItem(new MidnightArmour("1000", new Position(-1000, -1000)));

        }

    }


    public static void setArmourAttack(Integer armourAttack) {
        MidnightArmour.armourAttack = armourAttack;
    }

    public static void setArmourDefence(Integer armourDefence) {
        MidnightArmour.armourDefence = armourDefence;
    }


}
