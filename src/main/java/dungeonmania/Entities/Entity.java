package dungeonmania.Entities;
import dungeonmania.util.Position;


import dungeonmania.response.models.EntityResponse;

public abstract class Entity {
    private String id;
    private String type;
    private Position position;
    private boolean isInterctable;

    

    public Entity(String id, String type, Position position, boolean isInterctable) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.isInterctable = isInterctable;
    }

    public EntityResponse getEntityResponse() {
        return new EntityResponse(this.id, this.type, this.position, this.isInterctable);
    }

    /**
     * Geter for Id of Entity
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for Id of entity  
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for type
     * @return
     */
    public String getType() {
        return this.type;
    }

    /**
     * Setter for type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for position
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Setter for position
     * @param position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Getter for is interactable
     * @return
     */
    public boolean isInterctable() {
        return isInterctable;
    }

    /**
     * Setter for is Interactable
     * @param isInterctable
     */
    public void setInterctable(boolean isInterctable) {
        this.isInterctable = isInterctable;
    }

    /**
     * Return position of Entitiy X grid value
     * @return
     */
    public int getX(){
        return this.position.getX();
    }
    
    /**
     * Return position of Entity Y Grid Value
     * @return
     */
    public int getY() {
        return this.position.getY();
    }
}
