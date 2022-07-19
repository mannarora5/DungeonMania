package dungeonmania.Entities.staticEntities;


import dungeonmania.util.Position;


public class Switch extends Static {

    private Boolean activated;

    public Switch(String Id, Position position) {
        super(Id, "switch", position);
        this.activated = false;
        
    }
    
    /**
     * Return whether switch is activated
     * @return
     */
    public Boolean isActivated() {
        return this.activated;
    }

    /**
     * Return whether switch is activated
     * @return
     */
    public Boolean getActivated() {
        return this.activated;
    }

    /**
     * Update whether switch is activated
     * @param activated
     */
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }


}
