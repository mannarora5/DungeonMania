package dungeonmania;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.Entities.Entity;
import dungeonmania.Goals.Goal;
import dungeonmania.util.FileLoader;


public class JSONExtract {
    
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

    }

    /**
     *
     * @param entities as list  
     *
     * @return List of entites from map as List<Entity>
     *
     */
    public static List<Entity> createEntityClasses(JSONObject entities) {
        
        return new ArrayList<Entity>();
    }


    /**
     *
     * @param goals as list of strings
     *
     * @return List of goals to be completed as List<Goal>
     *
     */
    public static List<Goal> createGoalClasses(JSONObject goals) {
        return new ArrayList<Goal>();
    }



}
