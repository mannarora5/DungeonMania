package dungeonmania.Entities.staticEntities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.GameController;
import dungeonmania.JSONExtract;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.enemyEntities.EnemyObserver;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.util.Position;
import dungeonmania.util.PositonDistance;

public class zombieSpawner extends Static {

    public static Integer spawnRate;


    public zombieSpawner(String Id, Position position) {
        super(Id, "zombie_toast_spawner", position, true);
    }
    

    /**
     * Spawn the zomie
     * @param game
     */
    public void spawn( GameController game) {

        List<Entity> ListEntities = game.getEntities();
        List<Position> illegalPositions = new ArrayList<>();
        List<Position> cardinalpPositions = new ArrayList<>();

        cardinalpPositions.add(new Position(getX()  , getY()-1));
        cardinalpPositions.add(new Position(getX()-1  , getY()));
        cardinalpPositions.add(new Position(getX()  , getY()+1));
        cardinalpPositions.add(new Position(getX()+1  , getY()));
        
        for (Entity entity : ListEntities) {

            if (Position.isAdjacent(entity.getPosition(), getPos()) == true) {
                if ((entity instanceof Wall) || (entity instanceof Boulder) || (entity instanceof Door)) {
                    illegalPositions.add(entity.getPosition());
                }
            }
        
        }

        cardinalpPositions.removeAll(illegalPositions);

        if (cardinalpPositions.isEmpty()){
            return;
        }

        String zombie_id = JSONExtract.entities_created.toString();
        JSONExtract.increaseEntitiesCreates();

        ZombieToast zombie  = new ZombieToast(zombie_id ,cardinalpPositions.get(1));
        EnemyObserver observer = (EnemyObserver) zombie;
        game.findPlayer().enemyObservers.add(observer);
        game.addentity(zombie);

    }


    public void destroy(GameController game, Player player)throws InvalidActionException{

        int swordAmount = player.getInventory().quantity("sword");

        if (swordAmount == 0){
            throw new InvalidActionException("No weapon to destroy spawnner");
        }

        List<Position> cardinalPositions = PositonDistance.getCardinalPositions(this.getPosition());

        Position player_position = player.getPosition();

        for (Position p: cardinalPositions){
            if (p.equals(player_position)){
                game.entities.remove(this);
                return;
            }
        }

        throw new InvalidActionException("Not next to spawnner to destroy");
        
    }

    public static void setSpawnRate(Integer rate){
        zombieSpawner.spawnRate = rate;
    }    


}
