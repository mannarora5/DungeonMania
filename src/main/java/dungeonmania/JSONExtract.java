package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import org.json.JSONObject;



import dungeonmania.Entities.Entity;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.buildableEntities.Bow;
import dungeonmania.Entities.buildableEntities.Shield;
import dungeonmania.Entities.collectableEntities.Arrow;
import dungeonmania.Entities.collectableEntities.Bomb;
import dungeonmania.Entities.collectableEntities.Invincibility;
import dungeonmania.Entities.collectableEntities.Invisibility;
import dungeonmania.Entities.collectableEntities.Key;
import dungeonmania.Entities.collectableEntities.Sword;
import dungeonmania.Entities.collectableEntities.Treasure;
import dungeonmania.Entities.collectableEntities.Wood;
import dungeonmania.Entities.enemyEntities.Mercenary;
import dungeonmania.Entities.enemyEntities.Spider;
import dungeonmania.Entities.enemyEntities.SpiderSpawnner;
import dungeonmania.Entities.enemyEntities.ZombieToast;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.Entities.staticEntities.Door;
import dungeonmania.Entities.staticEntities.Exit;
import dungeonmania.Entities.staticEntities.Portal;
import dungeonmania.Entities.staticEntities.Switch;
import dungeonmania.Entities.staticEntities.Wall;
import dungeonmania.Entities.staticEntities.zombieSpawner;
import dungeonmania.Goals.AndGoal;
import dungeonmania.Goals.BouldersGoal;
import dungeonmania.Goals.EnemyGoal;
import dungeonmania.Goals.ExitGoal;
import dungeonmania.Goals.GoalComponent;
import dungeonmania.Goals.OrGoal;
import dungeonmania.Goals.TreasureGoal;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;




public class JSONExtract {


    public static Integer entities_created = 0;
    public static Integer items_created = 0;
    
    /**
     *
     * @param dungeonName name of dungeon 
     *
     * @return JSONObject with entity names from dungeon 
     *
     * @throws IllegalArgumentException if dungeon cannot be found
     */
    public static JSONArray extractEntitiesJSON(String dungeonName) throws IllegalArgumentException{

        try {
            return new JSONObject(FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json")).getJSONArray("entities");
        } catch (Exception e) {
            System.out.print(e);
            throw new IllegalArgumentException(dungeonName);
        }

    }

    /**
     *
     * @param dungeonName name of dungeon 
     *
     * @return JSONObject with goals from dungeon 
     *
     * @throws IllegalArgumentException if dungeon cannot be found
     */
    public static JSONObject extractGoalsJSON(String dungeonName) throws IllegalArgumentException{

        try {
            JSONObject dungeon = new JSONObject(FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json"));
            return dungeon.getJSONObject("goal-condition");
        } catch (Exception e) {
            throw new IllegalArgumentException(dungeonName);
        }
    }

    /**
     *
     * @param config name of configuration file 
     *
     * @return JSONObject with all configurations
     *
     * @throws IllegalArgumentException if file cannot be found
     */
    public static JSONObject extractConfigJSON(String config) throws IllegalArgumentException{

        try {
            return new JSONObject(FileLoader.loadResourceFile("/configs/" + config + ".json"));
        } catch (Exception e) {
            throw new IllegalArgumentException(config);
        }

    }




    ///////////////////////Functions below to be implemented when classes are being implemted////////////




    /**
     *
     * @param configs json objects with configurations 
     * @return void : sets the configurations from config file given
     *     
     */
    public static void setConfig(JSONObject configs) {

        // configs currently is like a dictionary
        // To get the value for a config use configs.get(<key>)
        // Example to set spider health and attact

        // Spider.setHealth(configs.get("spider_health"))
        // Spider.setAttach(configs.get("spider_attack"))

        // NOTE: that setHealth and setAttack needs to be static methods
        // belonging to that class and should set the static varibles for those classes to
        // minimise memory 

        // For classes with vars like health that changes during run time also need a current health var

        
        int zombie_spawn_rate = (Integer) configs.get("zombie_spawn_rate");
        zombieSpawner.setSpawnRate(zombie_spawn_rate);

        int totalEnemies = (Integer) configs.get("enemy_goal");
        EnemyGoal.settotalEnemies(totalEnemies);

        int totalTreasure = (Integer) configs.get("treasure_goal");
        TreasureGoal.settotalTreasure(totalTreasure);
        
        Integer swordAttack = (Integer) configs.get("sword_attack");
        Sword.setSwordAttack(swordAttack);

        Integer swordDuration= (Integer) configs.get("sword_durability");
        Sword.setSwordDuration(swordDuration);


        Integer invisDuration = (Integer) configs.get("invisibility_potion_duration");
        Invisibility.setPotionDuration(invisDuration);

        Integer invinDuration = (Integer) configs.get("invincibility_potion_duration");
        Invincibility.setPotionDuration(invinDuration);

        Integer spider_health = (Integer) configs.get("spider_health");
        Spider.setSpiderHealth(spider_health);

        Integer spider_attack = (Integer) configs.get("spider_attack");
        Spider.setSpiderAttack(spider_attack);

        Integer mercenary_attack = (Integer) configs.get("mercenary_attack");
        Mercenary.setMecenaryAttack(mercenary_attack);

        Integer mercenary_health = (Integer) configs.get("mercenary_health");
        Mercenary.setMecenaryHealth(mercenary_health);

        Integer zombie_attack = (Integer) configs.get("zombie_attack");
        ZombieToast.setZombieToastAttack(zombie_attack);

        Integer zombie_health = (Integer) configs.get("zombie_health");
        ZombieToast.setZombieToastHealth(zombie_health);

        Integer bribe_amount = (Integer) configs.get("bribe_amount");
        Mercenary.setBribe_amount(bribe_amount);

        Integer bribe_radius = (Integer) configs.get("bribe_radius");
        Mercenary.setBribe_radius(bribe_radius);

        Integer spider_spawn_rate = (Integer) configs.get("spider_spawn_rate");
        SpiderSpawnner.setSpawnRate(spider_spawn_rate);

        Integer bow_durability = (Integer) configs.get("bow_durability");
        Bow.setbowDuration(bow_durability);

        Integer shield_defence  = (Integer) configs.get("shield_defence");
        Shield.setSheildDefence(shield_defence);

        Integer shield_durability  = (Integer) configs.get("shield_durability");
        Shield.setShielduration(shield_durability);

        Integer player_attack  = (Integer) configs.get("player_attack");
        Player.setPlayerAttack(player_attack);

        Integer player_health  = (Integer) configs.get("player_health");
        Player.setPlayerHealth(player_health);

        Integer bomb_radius  = (Integer) configs.get("bomb_radius");
        Bomb.setRadius(bomb_radius);




    }

    /**
     *
     * @param entities as list of dicts
     *
     * @return List of entites from map as List<Entity>
     *
     */
    public static List<Entity> createEntityClasses(JSONArray entities) {
        
        
        List<Entity> entitiesList = new ArrayList<Entity>();

        Integer array_length = entities.length();


        for (int i = 0; i < array_length; i++) {

            JSONObject entityInfo = entities.getJSONObject(i);

            String entityType = entityInfo.get("type").toString();

            Integer posX = (int) entityInfo.get("x");
            Integer posY = (int) entityInfo.get("y");

            Position position = new Position(posX, posY);

            String Id = getEntities_created().toString();

            // Example of creating a class

            // if (entityType.equals("Wall")){
            //     entitiesList.add(new Wall(Id, position));

            //     setEntities_created(getEntities_created() + 1);
            // }

            if (entityType.equals("wall")){
                entitiesList.add(new Wall(Id, position));

            } else if (entityType.equals("zombie_toast_spawner")){
                entitiesList.add(new zombieSpawner(Id, position));
                
            } else if (entityType.equals("switch")){
                entitiesList.add(new Switch(Id, position));
    
            } else if (entityType.equals("exit")){
                entitiesList.add(new Exit(Id, position));
    
            } else if (entityType.equals("boulder")){
                entitiesList.add(new Boulder(Id, position));
    
            } else if (entityType.equals("door")){
                Integer key = (Integer) entityInfo.get("key");
                entitiesList.add(new Door(Id, position, key));
    
            } else if (entityType.equals("portal")){
                String colour = (String) entityInfo.get("colour");
                entitiesList.add(new Portal(Id, position, colour));
    
            } else if (entityType.equals("player")){
                entitiesList.add(new Player(Id, position));

            } else if (entityType.equals("arrow")){
                entitiesList.add(new Arrow(Id, position));

            } else if (entityType.equals("bomb")){
                entitiesList.add(new Bomb(Id, position));

            } else if (entityType.equals("invincibility_potion")){
                entitiesList.add(new Invincibility(Id, position));

            } else if (entityType.equals("invisibility_potion")){
                entitiesList.add(new Invisibility(Id, position));

            } else if (entityType.equals("key")){
                Integer key = (Integer) entityInfo.get("key");
                entitiesList.add(new Key(Id, position,key));
            } else if (entityType.equals("sword")){
                entitiesList.add(new Sword(Id, position));

            } else if (entityType.equals("treasure")){
                entitiesList.add(new Treasure(Id, position));

            } else if (entityType.equals("wood")){
                entitiesList.add(new Wood(Id, position));

            } else if (entityType.equals("zombie_toast")) {
                entitiesList.add(new ZombieToast(Id, position));

            } else if (entityType.equals("mercenary")) {
                entitiesList.add(new Mercenary(Id, position));

            } else if (entityType.equals("spider")) {
                entitiesList.add(new Spider(Id, position));
            }

            setEntities_created(getEntities_created() + 1);
        }

        return entitiesList;
    }


    /**
     *
     * @param goals as list of strings
     *
     * @return List of goals to be completed as List<Goal>
     *
     */
    public static List<GoalComponent> createGoalClasses(JSONObject goals) {
        //Create an List of Goals
        List<GoalComponent> listGoals = new ArrayList<>();

        if ("boulders".equals(goals.getString("goal"))) {
            listGoals.add(new BouldersGoal());

        } else if ("enemies".equals(goals.getString("goal"))) {
            listGoals.add(new EnemyGoal());

        } else if ("exit".equals(goals.getString("goal"))) {
            listGoals.add(new ExitGoal());

        } else if ("treasure".equals(goals.getString("goal"))) {
            listGoals.add(new TreasureGoal());

        } else if ("OR".equals(goals.getString("goal"))) {

            JSONArray subgoals = goals.getJSONArray("subgoals");
            OrGoal or = new OrGoal(new ArrayList<>());
            for (int i = 0; i < subgoals.length(); i++) {
                JSONObject subgoal = subgoals.getJSONObject(i);
                List<GoalComponent> subgoal_List =  createGoalClasses(subgoal);
                or.getgoals().addAll(subgoal_List);
                
            }
            listGoals.add(or);


        } else if ("AND".equals(goals.getString("goal"))) {
            JSONArray subgoals = goals.getJSONArray("subgoals");
            AndGoal and = new AndGoal();
            for (int i = 0; i < subgoals.length(); i++) {
                JSONObject subgoal = subgoals.getJSONObject(i);
                List<GoalComponent> subgoal_List =  createGoalClasses(subgoal);
                and.getgoals().addAll(subgoal_List);
            }
            listGoals.add(and);
            
        }

        return listGoals;
    }


    

    // Getters and Setters //


    public static Integer getEntities_created() {
        return JSONExtract.entities_created;
    }

    public static void setEntities_created(Integer entities_created) {
        JSONExtract.entities_created = entities_created;
    }

    public static Integer getItems_created() {
        return JSONExtract.items_created;
    }

    public static void setItems_created(Integer items_created) {
        JSONExtract.items_created = items_created;
    }

    public static void increaseEntitiesCreates(){
        JSONExtract.entities_created += 1;
    }
    

}
