package dungeonmania.Entities.staticEntities;


import dungeonmania.util.Position;


public class Switch extends Static {

    private Boolean activated;

    public Switch(String Id, String type, Position position) {
        super(Id, type, position);
        this.activated = false;
        
    }
    
    // /**
    //  * Check if boulder has made floor switch triggered
    //  * @param game
    //  * @return
    //  */
    // public boolean switchActivated(GameController game) {
    //     List<Entity> entities = game.entitiesSamePosition(this.getPosition());
    //     return entities.stream().anyMatch(Entity -> (Entity instanceof Boulder));
    // }


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
