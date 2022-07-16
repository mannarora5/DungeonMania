package dungeonmania;

import dungeonmania.Entities.collectableEntities.Bomb;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Invincibility;
import dungeonmania.Entities.collectableEntities.Invisibility;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import java.util.ArrayList;
import java.util.List;

public class DungeonManiaController {

    public GameController game;
    public String dungeonName;

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    /**
     * /dungeons
     */
    public static List<String> dungeons() {
        return FileLoader.listFileNamesInResourceDirectory("dungeons");
    }

    /**
     * /configs
     */
    public static List<String> configs() {
        return FileLoader.listFileNamesInResourceDirectory("configs");
    }

    /**
     * /game/new
     */
    public DungeonResponse newGame(String dungeonName, String configName) throws IllegalArgumentException {

        this.dungeonName = dungeonName;
        this.game = new GameController();
        this.game.newGame(dungeonName,configName);

        return getDungeonResponseModel();
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {

        List<EntityResponse> entityResponses= game.getEntityResponses();
        String goalString = game.goalsString();

        List<ItemResponse> itemResponses = this.game.findPlayer().getInventory().InfoItemResponses();
        List<String> buildalesList = this.game.findPlayer().getInventory().buildables();

        // Not implemented yet
        List<BattleResponse> battleResponses = new ArrayList<BattleResponse>();
        //Goals

        return new DungeonResponse("1", this.dungeonName, 
        
        entityResponses, itemResponses, battleResponses, buildalesList, goalString);
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {

        Collectable item = this.game.findPlayer().getInventory().getItem(itemUsedId);

        if (item == null){

            throw new InvalidActionException("Item not in inventory");

        } else if (item instanceof Bomb) {

        } else if (item instanceof Invincibility) {

        } else if (item instanceof Invisibility) {

        } else {
            throw new IllegalArgumentException("itemUsed is not a bomb, invincibility_potion, or an invisibility_potion");
        }

        return getDungeonResponseModel();
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        this.game.tickMovement(movementDirection);

        return getDungeonResponseModel();
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {

        if (buildable.equals("bow")) {
            this.game.buildBow();
        } else if (buildable.equals("shield")) {
            this.game.buildShield();
        } else {
            throw new IllegalArgumentException(buildable);
        }


        return getDungeonResponseModel();
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }


}
