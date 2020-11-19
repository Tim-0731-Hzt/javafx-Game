package unsw.dungeon.goals;

import unsw.dungeon.Entities.Dungeon;

/**
 * the interface of goal using strategy pattern
 */
public interface GoalStrategy {
    public boolean achieved(Dungeon dungeon);
    public void addGoal(GoalStrategy g);
}