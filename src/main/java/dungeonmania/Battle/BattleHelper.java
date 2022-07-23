package dungeonmania.Battle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.GameController;
import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.Player.PlayerState.InvincibleState;
import dungeonmania.Entities.Player.PlayerState.PlayerState;
import dungeonmania.Entities.buildableEntities.Bow;
import dungeonmania.Entities.buildableEntities.MidnightArmour;
import dungeonmania.Entities.buildableEntities.Shield;
import dungeonmania.Entities.collectableEntities.Collectable;
import dungeonmania.Entities.collectableEntities.Sword;
import dungeonmania.Entities.enemyEntities.Enemy;
import dungeonmania.Entities.enemyEntities.Mercenary;

public class BattleHelper {
    


    public static void battle(GameController game, Player player){

        List<Enemy> enemies = game.entitiesSamePosition(player.getPosition()).stream().filter(e -> (e instanceof Enemy)).
                            map(e -> (Enemy) e).
                            collect(Collectors.toList());
    

        if (enemies.isEmpty()){
            return;
        }

        PlayerState playerState = player.getState();

        List<Collectable> weapons = new ArrayList<Collectable>();
        List<String> enmiesToRemvoe = new ArrayList<String>();

        //
        Sword sword = player.getInventory().getSword();
        Bow bow = player.getInventory().getBow();

        if (sword != null){
            weapons.add(sword);
        }

        if (bow != null){
            weapons.add(bow);
        }

        List<Battle> battles = player.getBattles();

        for (Enemy enemy: enemies){

            boolean battleFound = false;

            for(Battle battle: battles){
                
                // Battle commenced before so new round
                if (battle.getEnemy().getId() == enemy.getId()) {

                    if (playerState instanceof InvincibleState) {

                            enmiesToRemvoe.add(enemy.getId());

                    } else {

                        double deltaEnemyHealth = -1 * BattleHelper.getPlayerAttack(player);
                        double deltaPlayerHealth = -1 * BattleHelper.getEnemyAttack(enemy, player);

                        enemy.setEnemyHealth(enemy.getEnemyHealth() + deltaEnemyHealth);

                        player.setCurrentplayerHealth(player.getCurrentplayerHealth() + deltaPlayerHealth);

                        battle.getRounds().add(new Round(deltaPlayerHealth, deltaEnemyHealth, weapons));

                        if (player.getCurrentplayerHealth() <= 0){
                            enmiesToRemvoe.add(player.getId());
                            BattleHelper.removeEntities(game, enmiesToRemvoe);
                            return;
                        }

                        if (enemy.getEnemyHealth() <= 0){
                            enmiesToRemvoe.add(enemy.getId());
                        }
                    }
                } 

            }

            if (!battleFound){
                // New battle and new round


                if (playerState instanceof InvincibleState) {

                    enmiesToRemvoe.add(enemy.getId());


                } else {
                    double deltaEnemyHealth =  -1 *BattleHelper.getPlayerAttack(player);
                    double deltaPlayerHealth = -1 *BattleHelper.getEnemyAttack(enemy, player);

                    
                    enemy.setEnemyHealth(enemy.getEnemyHealth() + deltaEnemyHealth);
                    player.setCurrentplayerHealth(player.getCurrentplayerHealth() + deltaPlayerHealth);

                    Round round1 = new Round(deltaPlayerHealth, deltaEnemyHealth, weapons);

                    List<Round> rounds = new ArrayList<Round>();

                    rounds.add(round1);

                    player.addBattle(new Battle(enemy, rounds, 
                                    player.getCurrentplayerHealth(), enemy.getEnemyHealth()));


                    if (player.getCurrentplayerHealth() <= 0){
                        enmiesToRemvoe.add(player.getId());
                        BattleHelper.removeEntities(game, enmiesToRemvoe);
                        return;
                    }

                    if (enemy.getEnemyHealth() <= 0){
                        enmiesToRemvoe.add(enemy.getId());
                    }
                }
                
            }
        }

        BattleHelper.removeEntities(game, enmiesToRemvoe);

    }

    public static void removeEntities(GameController game, List<String> enmiesToRemvoe){

        int enemiesRemoved = 0;
        for (String id: enmiesToRemvoe){
            game.removeEntity(id);
            enemiesRemoved += 1;
        }

        Player player = game.findPlayer();
        player.setEnemiesDestroyed(player.getEnemiesDestroyed() + enemiesRemoved);

    }

    public static double getPlayerAttack(Player player) {

        Bow bow = player.getInventory().getBow();
        Sword sword = player.getInventory().getSword();
        MidnightArmour armour = player.getInventory().getArmour();

        int noAllies = player.getNoAlly();

        int bowBonus = 2;
        int swordBonus = Sword.swordAttack;
        int mercenaryBonus = Mercenary.mercenaryAttack * noAllies;
        double armourAttack = MidnightArmour.armourAttack;


        if (bow == null){
            bowBonus = 1;

        } else {
            bow.setCurrentBowDuration(bow.getCurrentBowDuration() - 1);
            if (bow.getCurrentBowDuration() == 0){
                player.getInventory().removeItem(bow.getId());
            }
        }

        if (sword == null){
            swordBonus = 0;
        } else {

            sword.setCurrentSwordDuration(sword.getCurrentSwordDuration() - 1);

            if (sword.getCurrentSwordDuration() == 0){
                player.getInventory().removeItem(sword.getId());
            }
        }

        if (armour == null) {
            armourAttack = 0;
        }

        double div = (double) (bowBonus * (Player.playerAttack.intValue() + swordBonus + armourAttack)) / 5.0;

        return  ( div + mercenaryBonus);

    }


    public static double getEnemyAttack(Enemy enemy, Player player){

        Shield shield = player.getInventory().getShield();
        MidnightArmour armour = player.getInventory().getArmour();

        double shieldDefence = Shield.shieldDefence;
        double armourDefence = MidnightArmour.armourDefence;

        if (shield == null){
            shieldDefence = 0;
        } else {
            shield.setCurrentShieldDuration(shield.getCurrentShieldDuration() - 1);
            if (shield.getCurrentShieldDuration() == 0){
                player.getInventory().removeItem(shield.getId());
            }
        }

        if (armour == null) {
            armourDefence = 0;
        }


        return (enemy.getEnemyAttack() - shieldDefence - armourDefence) / 10;
    }   
}
