package unsw.dungeon.goals;

import unsw.dungeon.Entities.Dungeon;

/**
 * handling treasures goal
 * strategy pattern
 */
public class GoalTreasure implements GoalStrategy{

    private boolean done;

    @Override
    public boolean achieved(Dungeon dungeon) {

        if (!done) {
            done = dungeon.getNoTreasures() == dungeon.getPlayerTreasure();
        }
        return done;
    }

    @Override
    public void addGoal(GoalStrategy g) {
        return;
    }

    @Override
    public String toString() {
        return "Treasure";
    }

    public GoalTreasure() {
        this.done = false;
    }
}