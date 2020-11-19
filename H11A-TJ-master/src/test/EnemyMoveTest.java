package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import unsw.dungeon.Entities.*;

public class EnemyMoveTest {
    DungeonMockLoader loader;
    Dungeon dungeon;
    Player player;
    Enemy enemy;

    @BeforeEach
    void init() {
        loader = new DungeonMockLoader(GetJsonObject.getAdvanced());
        dungeon = loader.getDungeon();
        player = dungeon.getPlayer();
        enemy = player.getEnemies().get(0);
        player.setTesting(true);
    }

    @Test
    public void killPlayer() {
        assertEquals(player.isAlive(), true);
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveLeft();
        player.moveLeft();
        player.moveRight();
        player.moveUp();
        assertEquals(player.isAlive(), false);
    }

    @Test
    public void enemyGoThroughPortal() {
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveDown();
        player.moveLeft();
        player.moveDown();
        player.moveDown();
        assertEquals(enemy.getX(), 13);
        assertEquals(enemy.getY(), 11);
    }

    @Test
    public void playerIncincible() {

        assertEquals(enemy.isAttacking(), true);
        player.setInvincible(true);
        player.moveDown();
        assertEquals(enemy.isAttacking(), false);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                assertEquals(enemy.isAttacking(), true);
                timer.cancel();
            }
        };
        // the effect of the potion will last for 10 seconds
        timer.schedule(task, 10000);
        assertEquals(enemy.isAttacking(), false);

    }

    @Test
    public void playerIncincible2() {
        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 1, 1);
        player.setTesting(true);
        dungeon.addEntity((player));
        dungeon.setPlayer(player);
        Enemy enemy = new Enemy(9, 6, player);
        player.setInvincible(true);
        player.moveDown();
        assertEquals(enemy.isAttacking(), false);
    }

    @Test
    public void playerIncincible3() {
        assertEquals(enemy.isAttacking(), true);
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();
        player.moveDown();

        // now the player is invincible
        assertEquals(enemy.isAlive(), true);
        assertEquals(enemy.isAttacking(), false);
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveUp();
        player.moveUp();
        player.moveUp();
        player.moveLeft();
        player.moveUp();
        player.moveLeft();
        player.moveUp();
        player.moveUp();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        player.moveLeft();
        // the player collide with enemy in invincible state
        assertEquals(enemy.isAlive(), false);
    }

}