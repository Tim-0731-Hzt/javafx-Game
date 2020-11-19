package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.Entities.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONObject;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerTest {
  JSONObject jsonstring = GetJsonObject.getAdvanced();
  DungeonMockLoader loader = new DungeonMockLoader(jsonstring);
  Dungeon dungeon = loader.getDungeon();
  Player player = dungeon.getPlayer();

  @Test
  public void PlayerMoveTest() {
    // wall block test
    player.moveUp();
    assertEquals(player.x().getValue(), 1);
    assertEquals(player.y().getValue(), 1);
    player.moveLeft();
    assertEquals(player.x().getValue(), 1);
    assertEquals(player.y().getValue(), 1);
    // move test
    player.moveRight();
    assertEquals(player.x().getValue(), 2);
    assertEquals(player.y().getValue(), 1);
    player.moveDown();
    assertEquals(player.x().getValue(), 2);
    assertEquals(player.y().getValue(), 2);
  }

  @Test
  public void SwordgrabTest() {
    player.setPosition(5, 1);
    player.moveRight();
    assertEquals(player.hasSword(), true);
  }

  @Test
  public void InvincibilityTest() {
    player.setPosition(11, 9);
    player.moveDown();
    assertEquals(player.isInvincible(), true);
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
      public void run() {
        assertEquals(player.isInvincible(), false);
        timer.cancel();
      }
    };
    // the effect of the potion will last for 10 seconds
    timer.schedule(task, 10000);
  }

  @Test
  public void KeygrabTest() {
    player.setPosition(1, 7);
    player.moveRight();
    assertEquals(player.getKey().getId(), 0);
  }

  @Test
  public void TreasuregrabTest() {
    player.setPosition(7, 9);
    player.moveDown();
    int size = player.getTreasures();
    assertEquals(size, 1);
  }

  @Test
  public void PortalTest() {
    player.setPosition(1, 5);
    player.moveDown();
    // move to the point next to another portal
    assertEquals(player.x().getValue(), 13);
    assertEquals(player.y().getValue(), 11);
    int size = player.getTreasures();
    assertEquals(size, 1);
    player.moveUp();
    // move back
    assertEquals(player.x().getValue(), 1);
    assertEquals(player.y().getValue(), 5);
    player.setPosition(1, 6);
    Portal portal1 = dungeon.getPortal(0, 1, 6);
    Portal portal2 = dungeon.getPortal(0, 13, 10);
    assertEquals(portal1.getId(),portal2.getId());
    Portal p1 = new Portal(14, 8, 1);
    Portal p2 = new Portal(7, 7, 1);
    dungeon.addEntity(p1);
    dungeon.addEntity(p2);
    player.setPosition(7, 6);
    player.moveDown();
    assertNotEquals(player.x(), 13);
    assertNotEquals(player.y(), 8);
    assertNotEquals(player.x(), 14);
    assertNotEquals(player.y(), 9);
    System.out.println(player.x());
    System.out.println(player.y());
    assertEquals(player.x().getValue(), 14);
    assertEquals(player.y().getValue(), 7);
  }

  @Test
  public void BoulderTest() {
    Boulder boulder = new Boulder(1, 9);
    dungeon.addEntity(boulder);
    boulder.setDungeon(dungeon);
    player.setPosition(1, 8);
    player.moveDown();
    assertEquals(boulder.x().getValue(), 1);
    assertEquals(boulder.y().getValue(), 10);
    player.setPosition(1, 11);
    player.moveUp();
    assertEquals(boulder.x().getValue(), 1);
    assertEquals(boulder.y().getValue(), 9);
    player.setPosition(2, 9);
    player.moveLeft();
    assertEquals(boulder.x().getValue(), 1);
    assertEquals(boulder.y().getValue(), 9);
    Boulder boulder2 = new Boulder(2, 1);
    dungeon.addEntity(boulder2);
    boulder2.setDungeon(dungeon);
    player.setPosition(1, 1);
    player.moveRight();
    assertEquals(boulder2.x().getValue(), 3);
    assertEquals(boulder2.y().getValue(), 1);
  }

  @Test
  public void SwordDurableTest() {
    player.setTesting(true);
    player.setPosition(6, 1);
    assertEquals(player.hasSword(), true);
    Sword sword = player.getSword();
    Enemy enemy1 = new Enemy(7, 1, player);
    player.moveRight();
    assertEquals(enemy1.isAlive(), false);
    assertEquals(sword.getDurability(), 4);
    Enemy enemy2 = new Enemy(8, 1, player);
    player.moveRight();
    assertEquals(enemy2.isAlive(), false);
    assertEquals(sword.getDurability(), 3);
    Enemy enemy3 = new Enemy(9, 1, player);
    player.moveRight();
    assertEquals(enemy3.isAlive(), false);
    assertEquals(sword.getDurability(), 2);
    Enemy enemy4 = new Enemy(10, 1, player);
    player.moveRight();
    assertEquals(enemy4.isAlive(), false);
    assertEquals(sword.getDurability(), 1);
    Enemy enemy5 = new Enemy(11, 1, player);
    player.moveRight();
    assertEquals(enemy5.isAlive(), false);
    assertEquals(player.hasSword(), false);
  }

  @Test
  public void SwitchTest() {
    Switch Switch = new Switch(1, 10);
    assertEquals(Switch.isTriggered(), false);
    dungeon.addEntity(Switch);
    Boulder boulder = new Boulder(1, 9);
    dungeon.addEntity(boulder);
    boulder.setDungeon(dungeon);
    player.setPosition(1, 8);
    player.moveDown();
    assertEquals(boulder.x().getValue(), 1);
    assertEquals(boulder.y().getValue(), 10);
    assertEquals(Switch.isTriggered(), true);
  }
}