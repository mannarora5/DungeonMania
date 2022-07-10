package dungeonmania;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.Entities.*;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.staticEntities.Portal;
import dungeonmania.Goals.GoalComponent;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;


public class GameController {
    
    public List<Entity> entities;
    public List<GoalComponent> goals;

    public void newGame(String dungeonName, String config) throws IllegalArgumentException {

        JSONArray entityArray = JSONExtract.extractEntitiesJSON(dungeonName);
        JSONObject configsDict = JSONExtract.extractConfigJSON(config);
        //JSONArray goalsArray = JSONExtract.extractGoalsJSON(dungeonName);


        JSONExtract.setConfig(configsDict);

        setEntities(JSONExtract.createEntityClasses(entityArray));
        
        //setGoals(JSONExtract.createGoalClasses(goalsArray));

    }



    public void tickMovement(Direction movementDirection){
        
        findPlayer().movement(movementDirection, this);
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



    /// Getters and Setters///

    public Player findPlayer(){
        return entities.stream().filter(entity -> entity instanceof Player).map(entity -> (Player) entity).findFirst().orElse(null);
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


}
