package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.Entities.Entity;
import dungeonmania.Entities.staticEntities.Boulder;
import dungeonmania.Entities.staticEntities.Door;
import dungeonmania.Entities.staticEntities.Exit;
import dungeonmania.Entities.staticEntities.Portal;
import dungeonmania.Entities.staticEntities.Switch;
import dungeonmania.Entities.staticEntities.Wall;
import dungeonmania.Entities.staticEntities.zombieSpawner;
import dungeonmania.Goals.Goal;
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
    public static JSONArray extractGoalsJSON(String dungeonName) throws IllegalArgumentException{

        try {
            return new JSONObject(FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json")).getJSONArray("goal-condition");
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
    public static List<Goal> createGoalClasses(JSONArray goals) {
        return new ArrayList<Goal>();
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

    

}
