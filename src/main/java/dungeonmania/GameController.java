package dungeonmania;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.Entities.*;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.enemyEntities.Enemy;
import dungeonmania.Entities.enemyEntities.SpiderSpawnner;
import dungeonmania.Entities.staticEntities.Portal;
import dungeonmania.Entities.staticEntities.zombieSpawner;
import dungeonmania.Goals.GoalComponent;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;


public class GameController {
    
    public List<Entity> entities;
    public List<GoalComponent> goals;
    public int ticks;
    public List<Position> playerPositions = new ArrayList<>();

    public void newGame(String dungeonName, String config) throws IllegalArgumentException {

        JSONArray entityArray = JSONExtract.extractEntitiesJSON(dungeonName);
        JSONObject configsDict = JSONExtract.extractConfigJSON(config);
        JSONObject goalsArray = JSONExtract.extractGoalsJSON(dungeonName);

        JSONExtract.setConfig(configsDict);
        setEntities(JSONExtract.createEntityClasses(entityArray));
        setGoals(JSONExtract.createGoalClasses(goalsArray));

        this.ticks = 0;
    }



    public void tickMovement(Direction movementDirection){
        
        playerPositions.add(findPlayer().getPosition());

        increasetick();
        
        findPlayer().movement(movementDirection, this);

        tickSpawn();

        tickEnemyMove();
    }

    
    public void tickSpawn() {

        // Spawn Zombie if zombies are allowed to spawn and it should spawn on this tick
        if (zombieSpawner.spawnRate != 0 && this.getTicks() % zombieSpawner.spawnRate == 0) {
            List<zombieSpawner> zombie_list = findZombieSpawner();
            for (zombieSpawner zombie_Spawner : zombie_list) {
                zombie_Spawner.spawn(this);
            }
        }

        // Spawn spider if spider is allowed to spawn and it should spawn on this tick
        if (SpiderSpawnner.spawnRate != 0 && this.getTicks() % SpiderSpawnner.spawnRate == 0){
            SpiderSpawnner.spawn(this);
        }

    }

    public void tickEnemyMove(){
        for (Entity entity: this.entities){
            if (entity instanceof Enemy){
                Enemy enemy = (Enemy) entity;
                enemy.move(this);
            }
        }
    }



    

    public List<EntityResponse> getEntityResponses(){
        List<EntityResponse> responses = new ArrayList<EntityResponse>();
        this.entities.forEach(entity -> {responses.add(entity.getEntityResponse());});
        return responses;
    }

    public final List<Entity> entitiesSamePosition(Position position) {
        return entities.stream().
            filter(Object -> Object.getPosition().equals(position)).
            collect(Collectors.toList());
    }

    public final List<Portal> portalsInGame() {
        return entities.stream().
            filter(entity -> entity instanceof Portal)
            .map(entity -> (Portal) entity).collect(Collectors.toList());
    }


    public void removeEntity(String Id){
        this.entities.removeIf(e ->e.getId() == Id);
    }

    /// Getters and Setters///

    public void increasetick(){
        this.ticks += 1;
    }

    public Player findPlayer(){
        return entities.stream().filter(entity -> entity instanceof Player).map(entity -> (Player) entity).findFirst().orElse(null);
    }

    public List<zombieSpawner> findZombieSpawner(){
        return entities.stream().filter(entity -> entity instanceof zombieSpawner).map(entity -> (zombieSpawner) entity).collect(Collectors.toList());
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<GoalComponent> getGoals() {
        return this.goals;
    }

    public void setGoals(List<GoalComponent> goals) {
        this.goals = goals;
    }

    public void addentity(Entity entity){
        this.entities.add(entity);
    }



    public int getTicks() {
        return this.ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }


    /**
     * Constructor for goal string
     * @param goals
     * @return
     */
    public String goalsString() {
        
        List<GoalComponent> goals = this.getGoals();

        String goalString = "";
        for (GoalComponent goal: goals) {
            if (!goal.goalcompleted(this)) {
                goalString = goalString + goal.toString();
            }
        }
        return goalString;
    }

    

}
