package dungeonmania.Entities.collectableEntities;

import dungeonmania.util.Position;

public class Key extends Collectable{
    Integer digit;
    public Key(String Id, Position position, Integer digit) {
        super(Id, "key", position);
        this.digit = digit;
    }

    public Integer getDigit() {
        return this.digit;
    }

    public void setDigit(Integer digit) {
        this.digit = digit;
    }


}
