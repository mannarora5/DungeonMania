package dungeonmania.Battle;

import java.util.List;

import dungeonmania.Entities.enemyEntities.Enemy;

public class Battle {

    private Enemy enemy;
    private double initialPlayerHealth;
    private double initialEnemyHealth;
    private List<Round> rounds;
    
    public Battle(Enemy enemy, List<Round> rounds, double initialPlayerHealth, double initialEnemyHealth) {
        this.initialPlayerHealth = initialPlayerHealth;
        this.initialEnemyHealth = initialEnemyHealth;
        this.enemy = enemy;
        this.rounds = rounds;
    }
 

    public Enemy getEnemy() {
        return this.enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public double getInitialPlayerHealth() {
        return this.initialPlayerHealth;
    }

    public void setInitialPlayerHealth(double initialPlayerHealth) {
        this.initialPlayerHealth = initialPlayerHealth;
    }

    public double getInitialEnemyHealth() {
        return this.initialEnemyHealth;
    }

    public void setInitialEnemyHealth(double initialEnemyHealth) {
        this.initialEnemyHealth = initialEnemyHealth;
    }

    public List<Round> getRounds() {
        return this.rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    
    
}
