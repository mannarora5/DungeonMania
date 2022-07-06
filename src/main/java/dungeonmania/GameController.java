package dungeonmania;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.Entities.*;
import dungeonmania.Goals.Goal;


public class GameController {
    
    public List<Entity> entities;
    public List<Goal> goals;

    public void newGame(String dungeonName, String config) throws IllegalArgumentException {

        JSONArray entityArray = JSONExtract.extractEntitiesJSON(dungeonName);
        JSONObject configsDict = JSONExtract.extractConfigJSON(config);
        JSONArray goalsArray = JSONExtract.extractGoalsJSON(dungeonName);


        JSONExtract.setConfig(configsDict);

        setEntities(JSONExtract.createEntityClasses(entityArray));
        
        setGoals(JSONExtract.createGoalClasses(goalsArray));

    }




    /// Getters and Setters///

    public List<Entity> getEntities() {
        return this.entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Goal> getGoals() {
        return this.goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }




}
