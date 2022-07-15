package dungeonmania.Entities.staticEntities;

import dungeonmania.util.Position;

public class Portal extends Static {
    public String colour; 

    public Portal(String Id, Position position, String colour) {

        super(Id, "portal", position);
        this.colour = colour; 
    }
    

    public String getColour() {
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    
    public Position getPos(){
        return super.getPos();
    }

}