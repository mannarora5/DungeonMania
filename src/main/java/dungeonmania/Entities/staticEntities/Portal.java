package dungeonmania.Entities.staticEntities;

import dungeonmania.util.Position;

public class Portal extends Static {
    private String colour; 

    public Portal(String Id, Position position, String colour) {

        super(Id, "portal"+colour, position);
        this.colour = colour; 
    }
    
    /**
     * Get colour of portal
     * @return
     */
    public String getColour() {
        return this.colour;
    }

    /**
     * Update colour of portal
     * @param colour
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    /**
     * Get position of portal
     */
    public Position getPos(){
        return super.getPos();
    }

}
