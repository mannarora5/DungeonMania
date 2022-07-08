package dungeonmania.Entities.staticEntities;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import dungeonmania.GameController;
import dungeonmania.util.Position;

public class Boulder extends Static{

    public Boulder(String Id, Position position) {
        super(Id,"boulder", position);
    }
    
    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
    }

    public boolean moveBoulder(Position direction, GameController game) {
        Position nextPosition = getPosition().translateBy(direction);
        

    }
}
