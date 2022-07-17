package dungeonmania.Battle;

import dungeonmania.Entities.Player.Player;
import dungeonmania.Entities.buildableEntities.Bow;
import dungeonmania.Entities.collectableEntities.Sword;
import dungeonmania.Entities.enemyEntities.Enemy;
import dungeonmania.Entities.enemyEntities.Mercenary;

public class BattleHelper {
    


    public static double getPlayerAttack(Player player) {

        Bow bow = player.getInventory().getBow();
        Sword sword = player.getInventory().getSword();
        int noAllies = player.noAlly;

        int bowBonus = 2;
        int swordBonus = Sword.swordAttack;
        int mercenaryBonus = Mercenary.mercenaryAttack * noAllies;

        if (bow == null){
            bowBonus = 1;
        }

        if (sword == null){
            swordBonus = 0;
        }

        return ((bowBonus * (Player.playerAttack + swordBonus)) / 5) + mercenaryBonus;

        
    }


    public static double getEnemyAttack(Enemy enemy){
        return enemy.getEnemyAttack() / 10;
    }   
}
