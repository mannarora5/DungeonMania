package dungeonmania.Goals;

import dungeonmania.GameController;

public class TreasureGoal implements GoalComponent{

    public static int totalTreasure;

    public static void settotalTreasure(int totalTreasure) {
        TreasureGoal.totalTreasure = totalTreasure;
    }

    @Override
    public boolean goalcompleted(GameController game) {
        int collectedTreasure = game.findPlayer().getInventory().quantity();
        if (totalTreasure == collectedTreasure) {
            return true;
        }
        return false;
    }

    
}
