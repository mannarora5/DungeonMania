package dungeonmania.Goals;

import dungeonmania.GameController;

public class TreasureGoal implements GoalComponent{

    public static int totalTreasure;

    public static void settotalTreasure(int totalTreasure) {
        TreasureGoal.totalTreasure = totalTreasure;
    }

    @Override
    public boolean goalcompleted(GameController game) {
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
