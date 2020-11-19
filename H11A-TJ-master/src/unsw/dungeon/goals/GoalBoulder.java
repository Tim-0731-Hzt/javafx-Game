package unsw.dungeon.goals;

import unsw.dungeon.Entities.Dungeon;
import unsw.dungeon.Entities.Entity;
import unsw.dungeon.Entities.Switch;

/**
 * handling boulders goal
 * strategy pattern
 */
public class GoalBoulder implements GoalStrategy {

    @Override
    public boolean achieved(Dungeon dungeon) {
        boolean result = true;
        for (Entity e : dungeon.getEntities()) {
            if (e instanceof Switch) {
                result = result && ((Switch) e).isTriggered();
            }
        }
        return result;

    }

    @Override
    public void addGoal(GoalStrategy g) {
        return;
    }

    @Override
    public String toString() {
        return "Boulder";
    }

}