package dungeonmania;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.Battle.BattleHelper;
import dungeonmania.Entities.*;
import dungeonmania.Entities.Player.Inventory;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.buildableEntities.Bow;
import dungeonmania.Entities.buildableEntities.MidnightArmour;
import dungeonmania.Entities.buildableEntities.Sceptre;
import dungeonmania.Entities.buildableEntities.Shield;
import dungeonmania.Entities.collectableEntities.Bomb;
import dungeonmania.Entities.enemyEntities.Enemy;
import dungeonmania.Entities.enemyEntities.EnemyObserver;
import dungeonmania.Entities.enemyEntities.SpiderSpawnner;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.Entities.staticEntities.Portal;
import dungeonmania.Entities.staticEntities.zombieSpawner;
import dungeonmania.Goals.GoalComponent;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;


public class GameController {
    
    private List<Entity> entities;
    private List<GoalComponent> goals;
    private int ticks;
    private List<Position> playerPositions = new ArrayList<>();

    public void newGame(String dungeonName, String config) throws IllegalArgumentException {

        JSONArray entityArray = JSONExtract.extractEntitiesJSON(dungeonName);
        JSONObject configsDict = JSONExtract.extractConfigJSON(config);
        JSONObject goalsArray = JSONExtract.extractGoalsJSON(dungeonName);

        JSONExtract.setConfig(configsDict);
        setEntities(JSONExtract.createEntityClasses(entityArray));
        setGoals(JSONExtract.createGoalClasses(goalsArray));



        // Add all enemies created on loading to player observer list

        List<EnemyObserver>  enemies = new ArrayList<EnemyObserver>();

        for (Entity entity: this.entities) {
            if (entity instanceof Enemy){
                EnemyObserver enemyObserver = (EnemyObserver) entity;
                enemies.add(enemyObserver);
            }
        }

        findPlayer().getEnemyObservers().addAll(enemies);

        this.ticks = 0;
    }



    public void tickMovement(Direction movementDirection){
        
        Player player = this.findPlayer();

        playerPositions.add(player.getPosition());

        increasetick();
        
        tickSpawn();

        player.potionTick();
        player.movement(movementDirection, this);

        tickBombExplode();


        tickEnemyMove();

        tickBattle();
    }

    public void tickItemUsed(){
        
        increasetick();
        tickSpawn();

        findPlayer().potionTick();

        tickBombExplode();
        
        tickEnemyMove();

        tickBattle();
    }

    public void tickBattle(){

        Player player = findPlayer();

        if (player == null){
            return;
        }

        List<Enemy> enemies = this.entitiesSamePosition(player.getPosition()).stream().filter(e -> (e instanceof Enemy)).
                            map(e -> (Enemy) e).
                            collect(Collectors.toList());;

        Boolean sentinal = true;

        int counter = 30;

        while (sentinal && counter >= 0) {

            BattleHelper.battle(this, player);


            enemies = this.entitiesSamePosition(player.getPosition()).stream().filter(e -> (e instanceof Enemy)).
                map(e -> (Enemy) e).
                collect(Collectors.toList());

            if (enemies.isEmpty()){
                sentinal = false;
            }

            counter += -1;

        }

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

    public void tickBombExplode(){
        List<Bomb> bombs = entities.stream().
                            filter(e -> e instanceof Bomb).
                            map(e -> (Bomb) e).
                            collect(Collectors.toList());

        for (Bomb bomb: bombs){
            bomb.explode(this);
        }
    }


    public void builditem(String buildable) throws InvalidActionException{

        Inventory inventory = findPlayer().getInventory();

        if (buildable.equals("bow")) {

            Bow.buildBow(inventory);
            
        } else if (buildable.equals("shield")) {

            Shield.buildShield(inventory);

        } else if (buildable.equals("sceptre")){ 

            Sceptre.buildSceptre(inventory);

        } else if (buildable.equals("midnight_armour")) {

            for (Entity e : this.entities){
                if (e instanceof ZombieToast){
                    throw new InvalidActionException("Zombies present cannot build armour");
                }
            }

            MidnightArmour.buildArmour(inventory);
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
        return entities.stream().filter(entity -> entity instanceof Player).
                map(entity -> (Player) entity).findFirst().orElse(null);
    }

    public List<zombieSpawner> findZombieSpawner(){
        return entities.stream().filter(entity -> entity instanceof zombieSpawner).
                map(entity -> (zombieSpawner) entity).collect(Collectors.toList());
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

    public Entity getEntity(String Id) {
        return this.entities.stream().filter(e -> (e.getId().equals(Id))).findFirst().orElse(null);
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
            if (!goal.checkgoalcompleted(this)) {
                goalString = goalString + goal.toString();
            }
        }
        return goalString;
    }


    public List<Position> getPlayerPositions() {
        return this.playerPositions;
    }

    public void setPlayerPositions(List<Position> playerPositions) {
        this.playerPositions = playerPositions;
    }
    

}
