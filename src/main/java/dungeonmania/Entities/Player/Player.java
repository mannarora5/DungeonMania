package dungeonmania.Entities.Player;


import java.util.ArrayList;
import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Battle.Battle;
import dungeonmania.Battle.Round;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.Player.PlayerState.InvincibleState;
import dungeonmania.Entities.Player.PlayerState.InvisibleState;
import dungeonmania.Entities.Player.PlayerState.NormalState;
import dungeonmania.Entities.Player.PlayerState.PlayerState;
import dungeonmania.Entities.collectableEntities.Bomb;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Invincibility;
import dungeonmania.Entities.collectableEntities.Invisibility;
import dungeonmania.Entities.collectableEntities.Key;
import dungeonmania.Entities.enemyEntities.EnemyObserver;
import dungeonmania.Entities.staticEntities.*;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.response.models.RoundResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Entity implements PlayerStateSubject{

    public Inventory inventory;

    public int enemiesDestroyed;

    public int noAlly;

    public static Integer playerHealth;
    public static Integer playerAttack;

    public double currentplayerHealth;

    public PlayerState state;
    public PlayerState normalState = new NormalState();
    public PlayerState invincibleState = new InvincibleState();
    public PlayerState invisibleState = new InvisibleState();

    public List<Collectable> potionQueue;

    public List<EnemyObserver> enemyObservers;
    
    private int potionTimer;

    public List<Battle> battles;

    
    public Player(String id,Position position) {

        super(id, "player", position, false);
        this.enemiesDestroyed = 0;
        this.inventory = new Inventory();
        this.state = normalState;
        this.potionTimer = -10;
        this.currentplayerHealth = Player.playerHealth;
        this.potionQueue = new ArrayList<Collectable>();
        this.enemyObservers = new ArrayList<EnemyObserver>();
        this.noAlly = 0;
        this.battles = new ArrayList<Battle>();

    }

    // potionTick() should be called every tick
    // Keeps track of potion use
    public void potionTick() {

        this.potionTimer = this.potionTimer - 1;

        // If the currently used potion has just worn off, use the next potion in the queue
        if (this.potionTimer <= 0) {

            if (this.potionQueue.size() > 0) {
                
                Collectable newPotion = this.potionQueue.get(0);
                this.usePotion(newPotion);
                this.potionQueue.remove(newPotion);

            } else {
                this.potionTimer = -10;
                this.changeSate(this.getNormalState());
            }


        }


    }

    // Uses the given potion
    public void usePotion(Collectable potion) {


        // Switch the player's state and set duration

        if (potion instanceof Invincibility) {

            this.setPotionTimer(Invincibility.potionDuration.intValue());

            this.changeSate(this.getInvincibleState());

        } else if (potion instanceof Invisibility) {

            this.setPotionTimer(Invisibility.potionDuration.intValue());

            this.changeSate(this.getInvisibleState());
        }

    }

    public void queuePotion(Collectable potion) {

        // Remove the potion from the inventory
        this.inventory.removeItem(potion.getId());
        this.potionQueue.add(potion);

    }



    @Override
    public void attach(EnemyObserver enemy) {
        this.enemyObservers.add(enemy);
    }

    @Override
	public void detach(EnemyObserver enemy){
        this.enemyObservers.remove(enemy);

    }

    @Override
	public void notifyObservers(){
        for (EnemyObserver enemyObserver: this.enemyObservers){
            enemyObserver.updateMovement(this);
        }
    }

    @Override
    public PlayerState getState() {
        return this.state;
    }


    public void changeSate(PlayerState state){
        // Notify enemies
        this.setState(state);
        this.notifyObservers();
    }
    
    /**
     * Move player in game
     * @param direction
     * @param game
     */
    public void movement(Direction direction, GameController game) {
        //Get the nextPosition
        Position nextPosition = super.getPosition().translateBy(direction.getOffset());

        //Get a list of all Entities in that position
        List<Entity> entities = game.entitiesSamePosition(nextPosition);
        //Loop through entities and see if player can move to nextPosition

        if (entities.isEmpty()){
            super.setPosition(nextPosition);
            return;
        }

        for (Entity entity: entities) {

            if (!(entity instanceof Static)) {
                break;
            }

            if (entity instanceof Wall) {
                return;

            } else if (entity instanceof Boulder) {

                Boulder b = (Boulder) entity;
                boolean boulderMoved = b.move(direction,game);
                if (boulderMoved) {
                    super.setPosition(nextPosition);
                }
            
            } else if (entity instanceof Switch ){

                Switch s = (Switch) entity;
                if(!s.isActivated()){
                    super.setPosition(nextPosition);
                    return;
                }

            } else if (entity instanceof Exit) {
                super.setPosition(nextPosition);
                return;

            } else if (entity instanceof Portal) {

                Portal curPortal = (Portal) entity;
                List<Portal> portals = game.portalsInGame();

                for(Portal p: portals){
                    if(p.getPos() != curPortal.getPos() && p.getColour().equals(curPortal.getColour())){
                        super.setPosition(p.getPos());
                    }
                }
                game.tickMovement(direction);
                return;

            } else if (entity instanceof Door) {

                if (entity.getType() == "door_open"){
                    super.setPosition(nextPosition);
                    return;
                }
                Door d = (Door) entity;
                Boolean doorOpened = d.openDoor(this.inventory);
                if (doorOpened){
                    super.setPosition(nextPosition);
                } 
                return;

            } if (entity instanceof zombieSpawner) {
                super.setPosition(nextPosition);
                return;
            }

        }

        for  (Entity entity : entities) {


            if (entity instanceof Collectable && !(entity instanceof Key)) {

                if (entity instanceof Bomb) {
                    Bomb bomb = (Bomb) entity;
                    if (bomb.isPlaced()){
                        return;
                    }
                }

                Collectable item = (Collectable) entity;
                this.inventory.addItem(item);
                game.removeEntity(entity.getId());
                super.setPosition(nextPosition);

            } else if (entity instanceof Key){
                
                if (!this.inventory.hasKey()){

                    Collectable item = (Collectable) entity;
                    this.inventory.addItem(item);
                    game.removeEntity(entity.getId());
                    super.setPosition(nextPosition);
                } 

                super.setPosition(nextPosition);

            }


        }

    }


    public List<BattleResponse> getBattleResponses(){

        List<BattleResponse> responses = new ArrayList<BattleResponse>();

        for (Battle battle: this.getBattles()){

            List<RoundResponse> roundResponses = new ArrayList<RoundResponse>();

            for (Round round: battle.rounds) {

                List<ItemResponse> weaponryUsed  = new ArrayList<ItemResponse>();

                for (Collectable c: round.getWeaponryUsed()){

                    weaponryUsed.add(new ItemResponse(c.getId(), c.getType()));

                }

                roundResponses.add(new RoundResponse(round.getDeltaPlayerHealth(), round.getDeltaEnemyHealth(), weaponryUsed));

            }

            responses.add(new BattleResponse(battle.enemy.getType(), roundResponses, battle.initialPlayerHealth, battle.initialEnemyHealth));

        }


        return responses;
    }

    /**
     * Get the inventory
     * @return
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Update inventory
     * @param inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Get the enemies destroyed
     * @return
     */
    public int getEnemiesDestroyed() {
        return this.enemiesDestroyed;
    }

    /**
     * Set state of player
     * @param state
     */
    public void setState(PlayerState state) {
        this.state = state;
    }

    /**
     * Get normal state of player
     * @return
     */
    public PlayerState getNormalState() {
        return this.normalState;
    }

    /**
     * Update normal state 
     * @param normalState
     */
    public void setNormalState(PlayerState normalState) {
        this.normalState = normalState;
    }

    /**
     * Return invincible stable
     * @return
     */
    public PlayerState getInvincibleState() {
        return this.invincibleState;
    }

    /**
     * Update invincible state
     * @param invincibleState
     */
    public void setInvincibleState(PlayerState invincibleState) {
        this.invincibleState = invincibleState;
    }

    /**
     * Return invisible state
     * @return
     */
    public PlayerState getInvisibleState() {
        return this.invisibleState;
    }

    /**
     * Update invisible state
     * @param invisibleState
     */
    public void setInvisibleState(PlayerState invisibleState) {
        this.invisibleState = invisibleState;
    }

    /**
     * Return potion queue
     * @return
     */
    public List<Collectable> getPotionQueue() {
        return this.potionQueue;
    }

    /**
     * Update potion queue
     * @param potionQueue
     */
    public void setPotionQueue(List<Collectable> potionQueue) {
        this.potionQueue = potionQueue;
    }

    /**
     * Return potion timer
     * @return
     */
    public int getPotionTimer() {
        return this.potionTimer;
    }

    /**
     * Update potion timer
     * @param potionTimer
     */
    public void setPotionTimer(int potionTimer) {
        this.potionTimer = potionTimer;
    }

    /**
     * Updated enemies destroyed
     * @param enemiesDestroyed
     */
    public void setEnemiesDestroyed(int enemiesDestroyed) {
        this.enemiesDestroyed = enemiesDestroyed;
    }

    /**
     * Get current player health
     * @return
     */
    public double getCurrentplayerHealth() {
        return this.currentplayerHealth;
    }

    /**
     * Update current player health
     * @param currentplayerHealth
     */
    public void setCurrentplayerHealth(Double currentplayerHealth) {
        this.currentplayerHealth = currentplayerHealth;
    }

    /**
     * Update player attack damage
     * @param attack
     */
    public static void setPlayerAttack(Integer attack){
        Player.playerAttack = attack;
    }

    /**
     * Update player health
     * @param health
     */
    public static void setPlayerHealth(Integer health){
        Player.playerHealth = health;
    }


    public void increaseAlly(){
        this.noAlly += 1;
    }



    public int getNoAlly() {
        return this.noAlly;
    }

    public void setNoAlly(int noAlly) {
        this.noAlly = noAlly;
    }

    public List<EnemyObserver> getEnemyObservers() {
        return this.enemyObservers;
    }

    public void setEnemyObservers(List<EnemyObserver> enemyObservers) {
        this.enemyObservers = enemyObservers;
    }


    public List<Battle> getBattles() {
        return this.battles;
    }

    public void setBattles(List<Battle> battles) {
        this.battles = battles;
    }

    public void addBattle(Battle battle){
        this.battles.add(battle);
    }


}
