package unsw.dungeon.goals;

import unsw.dungeon.Entities.Dungeon;

/**
 * handling enemies goal
 * strategy pattern
 */
public class GoalEnemy implements GoalStrategy{

    private boolean done;

    @Override
    public boolean achieved(Dungeon dungeon) {
        if (!done) {
            done = dungeon.isAllHostileDied();
        }        
        return done;
    }

    @Override
    public void addGoal(GoalStrategy g) {
        return;
    }

    @Override
    public String toString() {
        return "Enemy";
    }

    public GoalEnemy() {
        this.done = false;
    }

}