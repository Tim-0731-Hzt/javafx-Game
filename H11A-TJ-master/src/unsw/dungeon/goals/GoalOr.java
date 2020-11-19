package unsw.dungeon.goals;

import java.util.ArrayList;

import unsw.dungeon.Entities.Dungeon;

/**
 * handling OR goal
 * strategy pattern and composite pattern
 */
public class GoalOr implements GoalStrategy {

    private ArrayList<GoalStrategy> goals;

    @Override
    public boolean achieved(Dungeon dungeon) {
        boolean result = false;
        for (GoalStrategy g : goals) {
            result = result || g.achieved(dungeon);
        }
        return result;
    }

    @Override
    public void addGoal(GoalStrategy g) {
        goals.add(g);

    }

    @Override
    public String toString() {
        String s = "(Or ";
        for (GoalStrategy g : goals) {
            s += g.toString() + ", ";
        }
        s = s.substring(0, s.length() - 2);
        return s + ")";
    }

    public GoalOr() {
        this.goals = new ArrayList<GoalStrategy>();
    }

}