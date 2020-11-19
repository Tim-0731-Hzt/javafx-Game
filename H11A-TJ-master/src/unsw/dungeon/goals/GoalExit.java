package unsw.dungeon.goals;

import unsw.dungeon.Entities.Dungeon;

/**
 * handling exit goal
 * strategy pattern
 */
public class GoalExit implements GoalStrategy {

    @Override
    public boolean achieved(Dungeon dungeon) {
        return ((dungeon.getPlayerX() == dungeon.getExitX()) && (dungeon.getPlayerY() == dungeon.getExitY()));
    }

    @Override
    public void addGoal(GoalStrategy g) {
        return;
    }

    @Override
    public String toString() {
        return "Exit";
    }

}