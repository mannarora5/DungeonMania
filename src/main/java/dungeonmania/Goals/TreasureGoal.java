package dungeonmania.Goals;

import java.io.Serializable;

import dungeonmania.GameController;

public class TreasureGoal implements GoalComponent, Serializable{

    private static int totalTreasure;

    public static void settotalTreasure(int totalTreasure) {
        TreasureGoal.totalTreasure = totalTreasure;
    }

    @Override
    public boolean checkgoalcompleted(GameController game) {
        int collectedTreasure = game.findPlayer().getInventory().quantity("treasure");
        int collectedSunStone = game.findPlayer().getInventory().quantity("sun_stone");
        if (totalTreasure == collectedTreasure + collectedSunStone) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return ":treasure";
    }
    
}
