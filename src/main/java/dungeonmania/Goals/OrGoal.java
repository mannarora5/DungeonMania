package dungeonmania.Goals;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.GameController;

public class OrGoal implements GoalComponent {
    private List<GoalComponent> goals = new ArrayList<>();

    
    public OrGoal(List<GoalComponent> goals) {
        this.goals = goals;
    }

    @Override
    public boolean checkgoalcompleted(GameController game) {
        return this.getgoals().stream().anyMatch(g -> g.checkgoalcompleted(game));
    }

    public List<GoalComponent> getgoals() {
        return this.goals;
    }

    public void addgoals(GoalComponent goal) {
        this.goals.add(goal);
    }

    @Override
    public String toString() {
        String goalOR = "(";

        goalOR += goals.get(0).toString() + " || ";
        goalOR += goals.get(1).toString() + ")";

        return goalOR;
    }
}
