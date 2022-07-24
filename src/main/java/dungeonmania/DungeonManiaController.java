package dungeonmania;

import dungeonmania.Entities.Entity;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.collectableEntities.Bomb;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Invincibility;
import dungeonmania.Entities.collectableEntities.Invisibility;
import dungeonmania.Entities.enemyEntities.Mercenary;
import dungeonmania.Entities.staticEntities.zombieSpawner;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.BattleResponse;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.response.models.ItemResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DungeonManiaController {

    private GameController game;
    private String dungeonName;

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

        Player player = this.game.findPlayer();

        List<EntityResponse> entityResponses= game.getEntityResponses();
        String goalString = game.goalsString();

        List<ItemResponse> itemResponses = player.getInventory().InfoItemResponses();
        List<String> buildalesList = player.getInventory().buildables();

        // Not implemented yet
        List<BattleResponse> battleResponses = player.getBattleResponses();
        
        return new DungeonResponse("1", this.dungeonName, 
        
        entityResponses, itemResponses, battleResponses, buildalesList, goalString);
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {

        Player player = this.game.findPlayer();
        Collectable item = player.getInventory().getItem(itemUsedId);


        if (item == null){

            throw new InvalidActionException("Item not in inventory");

        } else if (item instanceof Bomb) {

            Bomb bomb = (Bomb) item;
            bomb.place(player, this.game);
            this.game.tickItemUsed();

        } else if (item instanceof Invincibility || item instanceof Invisibility) {

            Collectable potion = (Collectable) item;

            player.queuePotion(potion);
            this.game.tickItemUsed();

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

        List<String> buildables = Arrays.asList("bow","shield","sceptre","midnight_armour");

        if (buildables.contains(buildable.toString())) {
            
            this.game.builditem(buildable);

        } else {
            throw new IllegalArgumentException(buildable);
        }


        return getDungeonResponseModel();
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {

        Entity entity = this.game.getEntity(entityId);

        if (entity == null) {

            throw new IllegalArgumentException("entityId is not a valid entity ID");

        } else if (entity instanceof Mercenary) {

            Mercenary mercenary = (Mercenary) entity;
            Player p = this.game.findPlayer();
            mercenary.bribe(this.game, p);

        } else if (entity instanceof zombieSpawner) {

            zombieSpawner spawner = (zombieSpawner) entity;
            spawner.destroy(this.game, this.game.findPlayer());

        } else {
            throw new IllegalArgumentException("entityId is not a valid entity ID to interact with");   
        }


        return getDungeonResponseModel();
    }

    /**
     * /game/save
     * @throws IOException
     */
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {

        // If name given already corresponds to the saved dungeon then update with current game

        String path = "./savedDungeons/" + name + ".ser";
        File file =  new File(path);

        if (file.exists()){
            file.delete();
        }

        try {
            file.createNewFile();

            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(this.game);

            objectStream.close();
            fileStream.close();

        } catch (IOException e) {

            e.printStackTrace();
            return getDungeonResponseModel();
        }

        return getDungeonResponseModel();
    }

    /**
     * /game/load
     * @throws IOException
     */
    public DungeonResponse loadGame(String name) throws IllegalArgumentException{


        String path = "./savedDungeons/" + name + ".ser";
        File file =  new File(path);

        if (!file.exists()){
            throw new IllegalArgumentException("Invalid game name");
        }

        try {


            FileInputStream fileStream = new FileInputStream(file);

            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            GameController gamObject = (GameController) objectStream.readObject();

            this.game = gamObject;
            this.dungeonName = name;

            objectStream.close();
            fileStream.close();


        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return getDungeonResponseModel();

        }  catch (IOException e) {

            e.printStackTrace();
            return getDungeonResponseModel();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            return getDungeonResponseModel();

        }

        return getDungeonResponseModel();
    }

    /**
     * /games/all
     */
    public List<String> allGames() {


        List<String> gameNames = new ArrayList<>();

        File saveFolder = new File("./savedDungeons");

        File[] files = saveFolder.listFiles();

        for (int i = 0; i < files.length; i++){
            String name = files[i].getName().replace(".ser", "");
            gameNames.add(name);
        }

        return gameNames;
    }

}
