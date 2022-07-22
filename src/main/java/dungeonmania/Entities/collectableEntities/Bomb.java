package dungeonmania.Entities.collectableEntities;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.GameController;
import dungeonmania.Entities.Entity;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.staticEntities.Switch;
import dungeonmania.util.Position;
import dungeonmania.util.PositonDistance;

public class Bomb extends Collectable{

    public static Integer radius;
    private Boolean placed;

    public Bomb(String Id, Position position) {
        super(Id, "bomb", position);
        this.placed = false;
    }

    public void place(Player player, GameController game) {

        this.setPlaced(true);

        player.getInventory().removeItem(this.getId());

        this.setPosition(player.getPosition());

        game.addentity(this);

    }

    public void explode(GameController game){

        if (!this.nextToActiveSwitch(game)){
            return;
        }

        List<Position> blastPositions = Bomb.getBlastPositions(this.getPosition(), Bomb.radius);
        List<String> entityRemoveIds = new ArrayList<String>();

        for (Position p: blastPositions){
            List<Entity> entitiesInP = game.entitiesSamePosition(p);
            for (Entity e: entitiesInP){
                if (!(e instanceof Player)){
                    entityRemoveIds.add(e.getId());
                }
            }
        }

        for (String id: entityRemoveIds){
            game.removeEntity(id);
        }

    }

    public static List<Position> getBlastPositions(Position position, Integer radius) {

        List<Position> positions = new ArrayList<Position>();

        int centreX = position.getX();
        int centreY = position.getY();

        int minX = centreX - Bomb.radius;
        int maxX = centreX + Bomb.radius;

        int minY = centreY - Bomb.radius;
        int maxY = centreY + Bomb.radius;

        for (int i = minX; i <= maxX; i++){

            for (int j = minY; j <= maxY; j++){
                positions.add(new Position(i, j));
            }
        }

        return positions;

    }

    public Boolean nextToActiveSwitch(GameController game){

        List<Position> cardinalPositions = PositonDistance.getCardinalPositions(this.getPosition());

        for (Position p: cardinalPositions){
            List<Entity> entitiesOnP = game.entitiesSamePosition(p);

            for (Entity e: entitiesOnP){
                if (e instanceof Switch){

                    Switch s = (Switch) e;
                    if (s.isActivated()){
                        return true;
                    }
                }
            }
        }

        return false;

    }



    public static void setRadius(Integer radius){
        Bomb.radius = radius;
    }


    public Boolean isPlaced() {
        return this.placed;
    }

    public Boolean getPlaced() {
        return this.placed;
    }

    public void setPlaced(Boolean placed) {
        this.placed = placed;
    }

}
