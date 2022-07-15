package dungeonmania.Entities.Player;


import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Invincibility;
import dungeonmania.Entities.collectableEntities.Key;
import dungeonmania.Entities.staticEntities.*;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends Entity {

    public Inventory inventory;
    public PlayerState state;
    public PlayerState normalState = new NormalState();
    public PlayerState invincibleState = new InvincibleState();
    public PlayerState invisibleState = new InvisibleState();
    public List<Collectable> potionQueue = new ArrayList<Collectable>();
    private int potionTimer = 0;

    public Player(String id, Position position) {
        super(id, "player", position, false);
        this.inventory = new Inventory();
        this.state = normalState;
    }

    public PlayerState getState() {
        return this.state;
    }

    // Uses the given potion
    public void usePotion(Collectable potion) {

        // Remove the potion from the inventory
        inventory.removeItem(potion.getId());

        // Set the length of the potion's effects
        potionTimer = potion.getPotionDuration();

        // Switch the player's state
        if (potion instanceof Invincibility) {
            state = invincibleState;
        } else if (potion instanceof Invisibility) {
            state = invisibleState;
        }

    }

    public void queuePotion(Collectable potion) {
        potionQueue.add(potion);
    }

    // potionTick() should be called every tick
    // Keeps track of potion use
    public void potionTick() {

        if (potionTimer > 0) {
            potionTimer--;

            // If the currently used potion has just worn off, use the next potion in the queue
            if (potionTimer == 0) {
                if (potionQueue.size() > 0) {
                    Collectable newPotion = potionQueue.get(0);
                    potionQueue.remove(newPotion);
                    newPotion.use();
                } else {
                    state = normalState;
                }
            }
        }

    }
    
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
                //TODO:
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


    public Inventory getInventory() {
        return this.inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


}
