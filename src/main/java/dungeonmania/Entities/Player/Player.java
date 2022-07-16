package dungeonmania.Entities.Player;


import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Key;
import dungeonmania.Entities.staticEntities.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Entity {
    public int enemiesDestroyed;
    public Inventory inventory;

    public static Integer playerHealth;
    public static Integer playerAttack;

    public Integer currentplayerHealth;

    
    public Player(String id,Position position) {
        super(id, "player", position, false);
        this.enemiesDestroyed = 0;
        this.inventory = new Inventory();
        this.currentplayerHealth = Player.playerHealth;
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
                //TODO:
                super.setPosition(nextPosition);
                return;
            }

        }

        for  (Entity entity : entities) {


            if (entity instanceof Collectable && !(entity instanceof Key)) {

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
    public Integer getCurrentplayerHealth() {
        return this.currentplayerHealth;
    }

    /**
     * Update current player health
     * @param currentplayerHealth
     */
    public void setCurrentplayerHealth(Integer currentplayerHealth) {
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
}
