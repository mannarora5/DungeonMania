package dungeonmania.Battle;

import java.util.List;

import dungeonmania.Entities.collectableEntities.Collectable;

public class Round {

    private double deltaPlayerHealth;
    private double deltaEnemyHealth;
    private List<Collectable> weaponryUsed;

    public Round(double deltaPlayerHealth, double deltaEnemyHealth, List<Collectable> weaponryUsed)
    {
        this.deltaPlayerHealth = deltaPlayerHealth;
        this.deltaEnemyHealth = deltaEnemyHealth;
        this.weaponryUsed = weaponryUsed;
    }


    public double getDeltaPlayerHealth() {
        return this.deltaPlayerHealth;
    }

    public void setDeltaPlayerHealth(double deltaPlayerHealth) {
        this.deltaPlayerHealth = deltaPlayerHealth;
    }

    public double getDeltaEnemyHealth() {
        return this.deltaEnemyHealth;
    }

    public void setDeltaEnemyHealth(double deltaEnemyHealth) {
        this.deltaEnemyHealth = deltaEnemyHealth;
    }

    public List<Collectable> getWeaponryUsed() {
        return this.weaponryUsed;
    }

    public void setWeaponryUsed(List<Collectable> weaponryUsed) {
        this.weaponryUsed = weaponryUsed;
    }


}
