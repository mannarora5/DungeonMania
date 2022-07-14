package dungeonmania.Entities.enemyEntities;

import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import javassist.bytecode.CodeIterator.Gap;

public class Mercenary extends Enemy {
    private static int EnemyHealth;
    private static int EnemyAttack;
    private static int bribeAmount;
    private static int bribeRadius;
    private boolean mercenaryBribed;

    

    public Mercenary(String id, Position position) {
            
        super(id, "mecenary", position, true);
        this.mercenaryBribed = false;
        
    }

    public static void setMecenaryAttack(int enemyAttack) {
        Mercenary.EnemyAttack = enemyAttack;
    }

    public static void setMecenaryHealth(int enemyHealth) {
        Mercenary.EnemyHealth = enemyHealth;
    }

    @Override
    public void movement(Direction direction, GameController game) {
        
    }

    /**
     * Set bribe amount for merecenary
     * @param bribe_amount
     */
    public static void setBribe_amount(int bribe_amount) {
        Mercenary.bribeAmount = bribe_amount;
    }

    /**
     * Set bribe radius for mercenary
     * @param bribe_radius
     */
    public static void setBribe_radius(int bribe_radius) {
        Mercenary.bribeRadius = bribe_radius;
    }

    
    /**
     * Bribes mecenary
     * @param game
     * @param player
     * @throws InvalidActionException
     */
    public void bribe(GameController game, Player player) throws InvalidActionException {
       int quantity = player.getInventory().quantity("treasure");
       // check if player has enough treasure to bribe mercenary 
       if (quantity < bribeAmount) {
        throw new InvalidActionException("Not enough treasure for bribe");
       }
       // checks if player is in radius of enemy
       if ((Position.calculatePositionBetween(player.getPosition(), getPosition()).getX() < bribeRadius) && 
            (Position.calculatePositionBetween(player.getPosition(), getPosition()).getY() < bribeRadius)) {
                player.getInventory().removeMultipleItems("treasure", quantity);
                this.mercenaryBribed = true;
       }
       else {
        throw new InvalidActionException("Not in radius to bribe mercenary");
       }
    }

    
    /**
     * Return whether mercenary is bribed or not
     * @return
     */
    public boolean isMercenaryBribed() {
        return mercenaryBribed;
    }
    
}
