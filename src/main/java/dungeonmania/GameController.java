package dungeonmania;

import java.util.List;

import dungeonmania.Entities.*;


public class GameController {
    
    public List<Entity> game_entities; 


    public void newGame(String dungeonName, String config) {
    
        JSONExtract.setConfig(config);

        this.game_entities = JSONExtract.extractMapEntites(dungeonName);

        



    }



}
