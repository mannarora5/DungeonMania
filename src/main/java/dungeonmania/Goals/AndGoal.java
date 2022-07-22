package dungeonmania.Goals;

import java.util.ArrayList;
import java.util.List;


import dungeonmania.GameController;


public class AndGoal implements GoalComponent {
    private List<GoalComponent> goals;


    private GoalComponent g1;
    private GoalComponent g2;

    private String goalString;


    public AndGoal() {
        this.goals = new ArrayList<GoalComponent>();
        this.goalString = "";
    }

    @Override
    public boolean checkgoalcompleted(GameController game) {

        int exitIndex =  getExitIndex();

        if (exitIndex == 3) {

            // No exit goal

            if (this.g1.checkgoalcompleted(game) && this.g2.checkgoalcompleted(game)){
                this.goalString = "";

            } else if (!this.g1.checkgoalcompleted(game) && this.g2.checkgoalcompleted(game)){
                this.goalString = this.g1.toString();

            } else if (this.g1.checkgoalcompleted(game) && !this.g2.checkgoalcompleted(game)){
                this.goalString = this.g2.toString();

            } else {
                this.goalString = "";
                this.goalString += this.g1.toString() + " && ";
                this.goalString += this.g2.toString();
            }

        } else if (exitIndex == 1) {
            // g1 is exit 

            if (this.g2.checkgoalcompleted(game) && this.g1.checkgoalcompleted(game)){
                this.goalString = "";

            } else if (!this.g1.checkgoalcompleted(game) && this.g2.checkgoalcompleted(game)){
                this.goalString = this.g1.toString();

            } else {
                this.goalString = "";
                this.goalString += this.g1.toString() + " && ";
                this.goalString += this.g2.toString();
            }


        } else {
            // g2 is exit 

            if (this.g1.checkgoalcompleted(game) && this.g2.checkgoalcompleted(game)){
                this.goalString = "";

            } else if (!this.g2.checkgoalcompleted(game) && this.g1.checkgoalcompleted(game)){
                this.goalString = "("+this.g2.toString()+")";

            } else {
                this.goalString = "";
                this.goalString += this.g1.toString() + " && ";
                this.goalString += this.g2.toString();
            }


        }

        return false;

    }

    public int getExitIndex(){

        if (this.g1 instanceof ExitGoal){
            return 1;
        } else if (this.g2 instanceof ExitGoal) {
            return 2;
        } else {
            return 3;
        }
    }
     
    @Override
    public String toString() {

        if (this.goalString == null){
            return "null";
        } else {
            return this.goalString;
        }

    }

    public void addgoals(List<GoalComponent> goals) {

        this.goals.addAll(goals);

        this.g1 = this.goals.get(0);
        this.g2 = this.goals.get(1);

        this.goalString += this.g1.toString() + " && ";
        this.goalString += this.g2.toString();

    }

    public List<GoalComponent> getgoals() {
        return this.goals;
    }

    public GoalComponent getG1() {
        return this.g1;
    }

    public void setG1(GoalComponent g1) {
        this.g1 = g1;
    }

    public GoalComponent getG2() {
        return this.g2;
    }

    public void setG2(GoalComponent g2) {
        this.g2 = g2;
    }


    public String getGoalString() {
        return this.goalString;
    }

    public void setGoalString(String goalString) {
        this.goalString = goalString;
    }

    public List<GoalComponent> getGoals() {
        return goals;
    }


    public void setGoals(List<GoalComponent> goals) {
        this.goals = goals;
    }
    
}
