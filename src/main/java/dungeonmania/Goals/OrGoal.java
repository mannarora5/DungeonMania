package dungeonmania.Goals;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.GameController;

public class OrGoal implements GoalComponent {
    public List<GoalComponent> goals = new ArrayList<>();

    
    public OrGoal(List<GoalComponent> goals) {
        this.goals = goals;
    }

    @Override
    public boolean goalcompleted(GameController game) {
        return this.getgoals().stream().anyMatch(g -> g.goalcompleted(game));
    }

    public List<GoalComponent> getgoals() {
        return goals;
    }

    public void addgoals(GoalComponent goal) {
        goals.add(goal);
    }

    @Override
    public String toString() {
        return "OR";
    }
}
