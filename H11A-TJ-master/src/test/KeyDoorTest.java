package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Entities.*;

public class KeyDoorTest {
    
    @Test
    public void PlayerMoveTest() {
        DungeonMockLoader loader = new DungeonMockLoader(GetJsonObject.getAdvanced());
        Dungeon dungeon = loader.getDungeon();
        Player player = dungeon.getPlayer();
        Door door = dungeon.getDoor(7, 8);
        assertEquals(door.isOpen(), false);
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
        player.moveDown();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        player.moveUp();
        assertEquals(door.isOpen(), true);
    }

    private Dungeon dungeon;
    private Player player;
    private Door d1;
    private Door d2;
    private Key k1;
    private Key k2;

    @BeforeEach
    void init() {
        this.dungeon = new Dungeon(10, 10);
        this.player = new Player(dungeon, 1, 1);
        this.d1 = new Door(3, 3, 1);
        this.d2 = new Door(7, 6, 2);
        this.k1 = new Key(4, 5, 1);
        this.k2 = new Key(8, 9, 2);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        dungeon.addEntity(d1);
        dungeon.addEntity(k1);
        dungeon.addEntity(d2);
        dungeon.addEntity(k2);
    }

    @Test
    public void openDoor() {
        assertEquals(d1.isOpen(), false);
        player.setPosition(4, 5);
        player.setPosition(3, 3);
        assertEquals(d1.isOpen(), true);
    }

    @Test
    public void openWrongDoor() {
        assertEquals(d1.isOpen(), false);
        player.setPosition(4, 5);
        player.setPosition(7, 6);
        assertEquals(d1.isOpen(), false);
    }

    @Test
    public void GoThroughClosedDoor() {
        player.setPosition(3, 4);
        int x = player.getX();
        int y = player.getY();
        player.moveUp();
        assertEquals(player.getX(), x);
        assertEquals(player.getY(), y);
    }

    @Test
    public void GoThroughOpenedDoor() {
        player.setPosition(4, 5);
        player.setPosition(3, 4);
        int x = player.getX();
        int y = player.getY();
        player.moveUp();
        assertEquals(player.getX(), x);
        assertEquals(player.getY(), y - 1);
    }


    @Test
    public void keyUsed() {
        assertEquals(player.getKey(), null);
        player.setPosition(4, 5);
        assertEquals(player.getKey(), k1);
        player.setPosition(3, 3);
        assertEquals(player.getKey(), null);
    }

    @Test
    public void multipleKey() {
        assertEquals(d1.isOpen(), false);
        assertEquals(d2.isOpen(), false);
        player.setPosition(8, 9);
        player.setPosition(4, 5);
        player.setPosition(3, 3);
        assertEquals(d1.isOpen(), false);
        player.setPosition(7, 6);
        assertEquals(d2.isOpen(), true);
    }

    @Test
    public void multipleKey2() {
        assertEquals(d1.isOpen(), false);
        player.setPosition(8, 9);
        player.setPosition(4, 5);
        player.setPosition(3, 3);
        player.setPosition(7, 6);
        player.setPosition(4, 5);
        player.setPosition(3, 3);
        assertEquals(d1.isOpen(), true);
    }
}