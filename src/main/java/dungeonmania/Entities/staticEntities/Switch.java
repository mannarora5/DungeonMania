package dungeonmania.Entities.staticEntities;


import dungeonmania.util.Position;


public class Switch extends Static {

    private Boolean activated;

    public Switch(String Id, Position position) {
        super(Id, "switch", position);
        this.activated = false;
        
    }
    
    public Boolean isActivated() {
        return this.activated;
    }

    public Boolean getActivated() {
        return this.activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }


}
