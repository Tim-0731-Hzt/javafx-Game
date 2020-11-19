package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.goals.*;
import unsw.dungeon.Entities.*;

public class GoalTest {

    private Dungeon dungeon;
    private Player player;

    @BeforeEach
    void init() {
        this.dungeon = new Dungeon(10, 10);
        this.player = new Player(dungeon, 1, 1);
        dungeon.addEntity((player));
        dungeon.setPlayer(player);
        player.setTesting(true);
    }

    @Test
    public void GoalLoaderTest1() {
        Goal goal = new Goal(GetJsonObject.getGoal());
        System.out.println(GetJsonObject.getGoal().toString(2));
        assertEquals(goal.toString(), "(And Enemy, Treasure)");
        // this would be true since the dungeon has no enemies and no treasures
        assertEquals(goal.achieved(dungeon), true);
    }

    @Test
    public void GoalLoaderTest2() {

        Goal goal = new Goal(GetJsonObject.getComplexGoal());
        assertEquals(goal.toString(), "(And (Or Treasure, Boulder), Exit)");
        assertEquals(goal.achieved(dungeon), false);
    }

    @Test
    public void exitAchieve() {
        Goal goal = new Goal(GetJsonObject.getExitGoal());

        dungeon.addEntity(new Exit(9, 9));
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(7, 9);
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(9, 8);
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(3, 4);
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(9, 9);
        assertEquals(goal.achieved(dungeon), true);
        assertEquals(goal.achieved(dungeon), true);
    }

    @Test
    public void BoulderAchieve() {
        Goal goal = new Goal(GetJsonObject.getBoulderGoal());

        Boulder b1 = new Boulder(2, 3);
        Boulder b2 = new Boulder(8, 9);
        b1.setDungeon(dungeon);
        b2.setDungeon(dungeon);
        dungeon.addEntity(new Switch(9, 9));
        dungeon.addEntity(new Switch(2, 2));
        dungeon.addEntity(b1);
        dungeon.addEntity(b2);

        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(2, 4);
        player.moveUp();
        player.setPosition(7, 9);
        player.moveRight();
        assertEquals(goal.achieved(dungeon), true);
        assertEquals(goal.achieved(dungeon), true);
    }

    @Test
    public void EnemyAchieve() {
        Goal goal = new Goal(GetJsonObject.getEnemyGoal());

        dungeon.addEntity(new Enemy(8, 9, player));
        dungeon.addEntity(new Enemy(3, 3, player));
        dungeon.addEntity(new Sword(9, 3));
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(9, 3);
        for (int i = 0; i < 10; i++) {
            player.moveRight();
        }
        assertEquals(goal.achieved(dungeon), true);
        assertEquals(goal.achieved(dungeon), true);
    }

    @Test
    public void TreasureAchieve() {
        Goal goal = new Goal(GetJsonObject.getTreasureGoal());

        dungeon.addEntity(new Treasure(9, 9));
        dungeon.addEntity(new Treasure(2, 7));
        dungeon.addEntity(new Treasure(1, 5));
        dungeon.addEntity(new Treasure(7, 0));

        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(9, 9);
        player.setPosition(2, 7);
        player.setPosition(1, 5);
        player.setPosition(7, 0);
        assertEquals(goal.achieved(dungeon), true);
        assertEquals(goal.achieved(dungeon), true);

    }


    @Test
    public void playerDie() {
        Goal goal = new Goal(GetJsonObject.getGoal());
        dungeon.addEntity(new Enemy(8, 9, player));
        dungeon.addEntity(new Enemy(3, 3, player));
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(9, 3);
        for (int i = 0; i < 10; i++) {
            player.moveRight();
        }
        assertEquals(goal.achieved(dungeon), false);

    }

    @Test
    public void playerDie2() {
        Goal goal = new Goal(GetJsonObject.getComplexGoal());
        dungeon.addEntity(new Enemy(8, 9, player));
        dungeon.addEntity(new Enemy(3, 3, player));
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(9, 3);
        for (int i = 0; i < 10; i++) {
            player.moveRight();
        }
        assertEquals(goal.achieved(dungeon), false);

    }

    @Test
    public void achieveComplex() {
        Goal goal = new Goal(GetJsonObject.getComplexGoal());
        dungeon.addEntity(new Exit(8, 9));
        dungeon.addEntity(new Treasure(3, 3));
        dungeon.addEntity(new Treasure(4, 5));
        dungeon.addEntity(new Switch(6, 7));
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(8, 9);
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(3, 3);
        player.setPosition(4, 5);
        assertEquals(goal.achieved(dungeon), false);
        player.setPosition(8, 9);
        assertEquals(goal.achieved(dungeon), true);
    }
}
