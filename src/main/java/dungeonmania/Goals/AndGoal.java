package dungeonmania.Goals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import dungeonmania.GameController;
import dungeonmania.Entities.staticEntities.Exit;

public class AndGoal implements GoalComponent {
    public List<GoalComponent> goals = new ArrayList<>();

    public AndGoal(List<GoalComponent> goals) {
        this.goals = goals;
    }

    
    @Override
    public boolean goalcompleted(GameController game) {
        for (GoalComponent goal : getgoals()) {
            if (goal.toString() == "exit") {
                GoalComponent goal2 = isNotExit();
                if (goal2.goalcompleted(game) == true && goal.goalcompleted(game) == true) {
                    return true; 
                } 
                else {
                    return false;
                }
            }
        }
        return getgoals().stream().allMatch(g -> g.goalcompleted(game));
    }

    public GoalComponent isNotExit() {
        for (GoalComponent goal : goals) {
            if (goal.toString() != "exit") {
               return goal; 
            }
        }
        return null;
    }

    public List<GoalComponent> getgoals() {
        return goals;
    }

    public void addgoals(GoalComponent goal) {
        goals.add(goal);
    }
    
    @Override
    public String toString() {
        return "AND";
    }
}
