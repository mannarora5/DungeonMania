package dungeonmania.Entities.enemyEntities;


import dungeonmania.GameController;
import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.Player.PlayerStateSubject;
import dungeonmania.Entities.Player.PlayerState.InvincibleState;
import dungeonmania.Entities.Player.PlayerState.InvisibleState;
import dungeonmania.Entities.Player.PlayerState.NormalState;
import dungeonmania.Entities.Player.PlayerState.PlayerState;
import dungeonmania.Entities.buildableEntities.Sceptre;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyAllyMovment;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyHuntMovement;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyMovementState;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyRandomMovement;
import dungeonmania.Entities.enemyEntities.enemyMovments.enemyScaredMovement;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;
import dungeonmania.util.PositonDistance;

public class Mercenary extends Enemy implements EnemyObserver{

    public static int mercenaryHealth;
    public static int mercenaryAttack;

    public static int bribeAmount;
    public static int bribeRadius;

    private boolean mercenaryBribed;
    private int mindControlDuration;

    private double currentMercenaryHealth;

    private enemyMovementState allyMercenarystate;
    private enemyMovementState normalMercenarystate;
    private enemyMovementState scaredMercenaryMovement;
    private enemyMovementState randomMercenaryMovement;
    private enemyMovementState currentMovementState;

    public Mercenary(String id, Position position) {
            
        super(id, "mercenary", position, true, Mercenary.mercenaryAttack);
        this.mercenaryBribed = false;

        this.currentMercenaryHealth = Mercenary.mercenaryHealth;
        this.allyMercenarystate = new enemyAllyMovment(this);
        this.normalMercenarystate = new enemyHuntMovement(this);
        this.scaredMercenaryMovement = new enemyScaredMovement(this);
        this.randomMercenaryMovement = new enemyRandomMovement(this);
        this.currentMovementState = this.normalMercenarystate;
        this.mindControlDuration = 0;
    }


    public void move(GameController game) {


        if(this.mindControlDuration > 0) {
            // Mind control active 
            this.currentMovementState.move(game);

            this.decreaseMindControlDuration();

            // Mind control finish
            if (this.mindControlDuration <= 0){
                this.currentMovementState = this.normalMercenarystate;
                this.mercenaryBribed = false;
            }

        } else {
            this.currentMovementState.move(game);
        }

    }

    @Override
    public double getEnemyHealth(){
        return this.currentMercenaryHealth;
    }

    @Override
    public double setEnemyHealth(double health){
        return this.currentMercenaryHealth =  health;
    }

    @Override
    public void updateMovement(PlayerStateSubject player)  {

        PlayerState state = player.getState();

        if (this.isMercenaryBribed()){
            this.currentMovementState = this.allyMercenarystate;
            return;
        } 

        if (state instanceof NormalState) {
            this.currentMovementState = this.normalMercenarystate;

        } else if (state instanceof InvincibleState) {

            this.currentMovementState = this.scaredMercenaryMovement;

        } else if (state instanceof InvisibleState) {
            this.currentMovementState = this.randomMercenaryMovement;
        } 
        
        
    }

    
    /**
     * Bribes mecenary
     * @param game
     * @param player
     * @throws InvalidActionException
     */
    public void bribe(GameController game, Player player) throws InvalidActionException {

        if (this.getMercenaryBribed()){
            throw new InvalidActionException("Mercenary already bribed/mind controlled");
        }

        Inventory inventory = player.getInventory();

        Sceptre sceptre =  inventory.getSceptre();

        if (sceptre != null){
            this.mindControl(player);
            return;
        }

       int quantity = inventory.quantity("treasure");
       // check if player has enough treasure to bribe mercenary 
       if (quantity < Mercenary.bribeAmount) {
        throw new InvalidActionException("Not enough treasure for bribe");
       }

       double distance = PositonDistance.distancebetweenposition(player.getPosition(), this.getPosition());

       // checks if player is in radius of enemy
       if (distance <= Mercenary.bribeRadius) {

            inventory.removeMultipleItems("treasure", quantity);
            this.setMercenaryBribed(true);
            this.setCurrentMovementState(this.getAllyMercenarystate());
            player.increaseAlly();
       }
       else {
        throw new InvalidActionException("Not in radius to bribe mercenary");
       }


    }


    public void mindControl(Player player) {

        this.mercenaryBribed = true;

        this.mindControlDuration = Sceptre.mindControlDuration;

        this.currentMovementState = this.allyMercenarystate;

        player.getInventory().removeMultipleItems("sceptre", 1);

        player.increaseAlly();

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
    public double getCurrentHealth() {
        return this.currentMercenaryHealth;
    }

    /**
     * Set health of mercenary
     * @param currentHealth
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentMercenaryHealth = currentHealth;
    }


    public double getCurrentMercenaryHealth() {
        return this.currentMercenaryHealth;
    }

    public void setCurrentMercenaryHealth(int currentMercenaryHealth) {
        this.currentMercenaryHealth = currentMercenaryHealth;
    }

    public enemyMovementState getAllyMercenarystate() {
        return this.allyMercenarystate;
    }

    public void setAllyMercenarystate(enemyMovementState allyMercenarystate) {
        this.allyMercenarystate = allyMercenarystate;
    }

    public enemyMovementState getNormalMercenarystate() {
        return this.normalMercenarystate;
    }

    public void setNormalMercenarystate(enemyMovementState normalMercenarystate) {
        this.normalMercenarystate = normalMercenarystate;
    }

    public enemyMovementState getScaredMercenaryMovement() {
        return this.scaredMercenaryMovement;
    }

    public void setScaredMercenaryMovement(enemyMovementState scaredMercenaryMovement) {
        this.scaredMercenaryMovement = scaredMercenaryMovement;
    }

    public enemyMovementState getRandomMercenaryMovement() {
        return this.randomMercenaryMovement;
    }

    public void setRandomMercenaryMovement(enemyMovementState randomMercenaryMovement) {
        this.randomMercenaryMovement = randomMercenaryMovement;
    }

    public enemyMovementState getCurrentMovementState() {
        return this.currentMovementState;
    }

    public void setCurrentMovementState(enemyMovementState currentMovementState) {
        this.currentMovementState = currentMovementState;
    }

    

    public int getMindControlDuration() {
        return this.mindControlDuration;
    }

    public void setMindControlDuration(int mindControlDuration) {
        this.mindControlDuration = mindControlDuration;
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

    public void decreaseMindControlDuration(){
        this.mindControlDuration += -1;
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

    public int checkMercenaryMovement(enemyMovementState currentMovementState) {
        if(currentMovementState == this.normalMercenarystate) {
            return 1;
        }
        if (currentMovementState == this.allyMercenarystate) {
            return 2;
        }
        if (currentMovementState == this.randomMercenaryMovement) {
            return 3;
        }
        if (currentMovementState == this.scaredMercenaryMovement) {
            return 4;
        }
        return 0;
    }
}
