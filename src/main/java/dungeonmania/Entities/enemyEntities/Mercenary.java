package dungeonmania.Entities.enemyEntities;


import dungeonmania.GameController;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.mercenaryAllyMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.mercenaryNormalMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.mercenaryRandomMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.mercenaryScaredMovementState;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;

public class Mercenary extends Enemy {

    public static int mercenaryHealth;
    public static int mercenaryAttack;

    public static int bribeAmount;
    public static int bribeRadius;

    public boolean mercenaryBribed;

    public int currentMercenaryHealth;

    enemyMovementState allyMercenarystate;
    enemyMovementState normalMercenarystate;
    enemyMovementState scaredMercenaryMovement;
    enemyMovementState randomMercenaryMovement;
    enemyMovementState currentMovementState;

    public Mercenary(String id, Position position) {
            
        super(id, "mercenary", position, true);
        this.mercenaryBribed = false;

        this.currentMercenaryHealth = Mercenary.mercenaryHealth;
        this.allyMercenarystate = new mercenaryAllyMovementState(this);
        this.normalMercenarystate = new mercenaryNormalMovementState(this);
        this.scaredMercenaryMovement = new mercenaryScaredMovementState(this);
        this.randomMercenaryMovement = new mercenaryRandomMovementState(this);
        this.currentMovementState = this.normalMercenarystate;
    }

    /**
     * Set attack damage of mercenary
     * @param enemyAttack
     */
    public static void setMecenaryAttack(int enemyAttack) {
        Mercenary.mercenaryAttack = enemyAttack;
    }

    /**
     * Set Mercenary Health
     * @param enemyHealth
     */
    public static void setMecenaryHealth(int enemyHealth) {
        Mercenary.mercenaryHealth = enemyHealth;
    }

    public void move(GameController game) {
        this.currentMovementState.move(game);
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

    /**
     * Return bribed boolean
     * @return
     */
    public boolean getMercenaryBribed() {
        return this.mercenaryBribed;
    }

    /**
     * Set whether mercenary is bribed or not
     * @param mercenaryBribed
     */
    public void setMercenaryBribed(boolean mercenaryBribed) {
        this.mercenaryBribed = mercenaryBribed;
    }

    /**
     * Return health of mercenary
     * @return
     */
    public int getCurrentHealth() {
        return this.currentMercenaryHealth;
    }

    /**
     * Set health of mercenary
     * @param currentHealth
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentMercenaryHealth = currentHealth;
    }

    
}
