package dungeonmania.Goals;

import java.util.ArrayList;
import java.util.List;


import dungeonmania.GameController;


public class AndGoal implements GoalComponent {
    public List<GoalComponent> goals;

    public List<GoalComponent> getGoals() {
        return goals;
    }


    public void setGoals(List<GoalComponent> goals) {
        this.goals = goals;
    }


    public AndGoal() {
        this.goals = new ArrayList<>();
    }

    
    @Override
    public boolean goalcompleted(GameController game) {
        return getgoals().stream().allMatch(g -> g.goalcompleted(game));
    }

    public GoalComponent isNotExit() {
        for (GoalComponent goal : goals) {
            if (goal.toString() != ":exit") {
               return goal; 
            }
        }
        return null;
    }

    public List<GoalComponent> getgoals() {
        return goals;
    }

    public void addgoals(List<GoalComponent> goals) {
        goals.addAll(goals);
    }
     
    @Override
    public String toString() {
        String goalAND = "(";

        goalAND += goals.get(0).toString() + " && ";
        goalAND += goals.get(1).toString() + ")";

        return goalAND;
    }
    
}
