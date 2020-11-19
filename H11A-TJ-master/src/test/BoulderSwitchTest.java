package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Entities.*;

/**
 * testing boulder and switch behavior
 */
public class BoulderSwitchTest {

    private Dungeon dungeon;
    private Player player;

    @BeforeEach
    void init() {
        this.dungeon = new Dungeon(10, 10);
        this.player = new Player(dungeon, 1, 1);
        dungeon.addEntity((player));
        dungeon.setPlayer(player);
    }

    /**
     * testing trigger a switch
     */
    @Test
    public void triggerSwitch() {
        Boulder boulder = new Boulder(dungeon, 3, 4);
        Switch s = new Switch(3, 3);
        dungeon.addEntity(boulder);
        dungeon.addEntity(s);
        player.setPosition(3, 5);
        assertEquals(s.isTriggered(), false);
        player.moveUp();
        assertEquals(s.isTriggered(), true);
    }

    @Test
    public void untriggerSwitch() {
        Boulder boulder = new Boulder(dungeon, 3, 4);
        Switch s = new Switch(3, 3);
        dungeon.addEntity(boulder);
        dungeon.addEntity(s);
        player.setPosition(3, 5);
        assertEquals(s.isTriggered(), false);
        player.moveUp();
        assertEquals(s.isTriggered(), true);
        player.moveUp();
        assertEquals(s.isTriggered(), false);
        player.setPosition(4, 2);
        player.moveLeft();
        assertEquals(s.isTriggered(), false);

    }

    @Test
    public void pushTwoBoulderUp() {
        Boulder boulder = new Boulder(dungeon, 3, 4);
        Boulder boulder2 = new Boulder(dungeon, 3, 3);
        dungeon.addEntity(boulder);
        dungeon.addEntity(boulder2);
        player.setPosition(3, 5);
        player.moveUp();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 5);
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);
        assertEquals(boulder2.getX(), 3);
        assertEquals(boulder2.getY(), 3);
    }

    @Test
    public void pushTwoBoulderDown() {
        Boulder boulder = new Boulder(dungeon, 3, 4);
        Boulder boulder2 = new Boulder(dungeon, 3, 3);
        dungeon.addEntity(boulder);
        dungeon.addEntity(boulder2);
        player.setPosition(3, 2);
        player.moveDown();
        assertEquals(player.getX(), 3);
        assertEquals(player.getY(), 2);
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);
        assertEquals(boulder2.getX(), 3);
        assertEquals(boulder2.getY(), 3);
    }

    @Test
    public void pushTwoBoulderLeft() {
        Boulder boulder = new Boulder(dungeon, 3, 4);
        Boulder boulder2 = new Boulder(dungeon, 4, 4);
        dungeon.addEntity(boulder);
        dungeon.addEntity(boulder2);
        player.setPosition(5, 4);
        player.moveLeft();
        assertEquals(player.getX(), 5);
        assertEquals(player.getY(), 4);
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);
        assertEquals(boulder2.getX(), 4);
        assertEquals(boulder2.getY(), 4);
    }

    @Test
    public void pushTwoBoulderRight() {
        Boulder boulder = new Boulder(dungeon, 3, 4);
        Boulder boulder2 = new Boulder(dungeon, 4, 4);
        dungeon.addEntity(boulder);
        dungeon.addEntity(boulder2);
        player.setPosition(2, 4);
        player.moveRight();
        assertEquals(player.getX(), 2);
        assertEquals(player.getY(), 4);
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);
        assertEquals(boulder2.getX(), 4);
        assertEquals(boulder2.getY(), 4);
    }

    @Test
    public void pushUpCornered() {
        dungeon.addEntity(new Wall(3, 3));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(3, 5);
        player.moveUp();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);

    }

    @Test
    public void pushUpCornered2() {
        dungeon.addEntity(new Wall(3, 3));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(3, 5);
        dungeon.addEntity(new Wall(2, 4));
        dungeon.addEntity(new Wall(4, 4));
        player.moveUp();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);
    }

    @Test
    public void pushUpCornered3() {
        dungeon.addEntity(new Wall(6, 6));
        dungeon.addEntity(new Wall(5, 7));
        Boulder boulder2 = new Boulder(dungeon, 6, 7);
        dungeon.addEntity(boulder2);
        player.setPosition(6, 8);
        player.moveUp();
        assertEquals(boulder2.getX(), 7);
        assertEquals(boulder2.getY(), 7);

    }

    @Test
    public void pushUpCornered4() {
        dungeon.addEntity(new Wall(3, 3));
        dungeon.addEntity(new Wall(4, 4));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(3, 5);
        player.moveUp();
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 4);
    }


    @Test
    public void pushDownCornered() {
        dungeon.addEntity(new Wall(3, 5));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(3, 3);
        player.moveDown();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);

    }

    @Test
    public void pushDownCornered2() {
        dungeon.addEntity(new Wall(3, 5));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(3, 2);
        dungeon.addEntity(new Wall(2, 4));
        dungeon.addEntity(new Wall(4, 4));
        player.moveDown();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);
    }

    @Test
    public void pushDownCornered3() {
        dungeon.addEntity(new Wall(6, 8));
        dungeon.addEntity(new Wall(5, 7));
        Boulder boulder2 = new Boulder(dungeon, 6, 7);
        dungeon.addEntity(boulder2);
        player.setPosition(6, 6);
        player.moveDown();
        assertEquals(boulder2.getX(), 7);
        assertEquals(boulder2.getY(), 7);

    }

    @Test
    public void pushDownCornered4() {
        dungeon.addEntity(new Wall(3, 5));
        dungeon.addEntity(new Wall(4, 4));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(3, 3);
        player.moveDown();
        assertEquals(boulder.getX(), 2);
        assertEquals(boulder.getY(), 4);
    }

    @Test
    public void pushLeftCornered() {
        dungeon.addEntity(new Wall(2, 4));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(4, 4);
        player.moveLeft();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);

    }

    @Test
    public void pushLeftCornered2() {
        dungeon.addEntity(new Wall(2, 4));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(4, 4);
        dungeon.addEntity(new Wall(3, 3));
        dungeon.addEntity(new Wall(3, 5));
        player.moveLeft();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);
    }

    @Test
    public void pushLeftCornered3() {
        dungeon.addEntity(new Wall(6, 6));
        dungeon.addEntity(new Wall(5, 7));
        Boulder boulder2 = new Boulder(dungeon, 6, 7);
        dungeon.addEntity(boulder2);
        player.setPosition(7, 7);
        player.moveLeft();
        assertEquals(boulder2.getX(), 6);
        assertEquals(boulder2.getY(), 8);

    }

    @Test
    public void pushLeftCornered4() {
        dungeon.addEntity(new Wall(3, 3));
        dungeon.addEntity(new Wall(4, 4));
        Boulder boulder = new Boulder(dungeon, 4, 3);
        dungeon.addEntity(boulder);
        player.setPosition(5, 3);
        player.moveLeft();
        assertEquals(boulder.getX(), 4);
        assertEquals(boulder.getY(), 2);
    }


    
    @Test
    public void pushRightCornered() {
        dungeon.addEntity(new Wall(4, 4));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(2, 4);
        player.moveRight();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);

    }

    @Test
    public void pushRightCornered2() {
        dungeon.addEntity(new Wall(4, 4));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(2, 4);
        dungeon.addEntity(new Wall(3, 3));
        dungeon.addEntity(new Wall(3, 5));
        player.moveRight();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 4);
    }

    @Test
    public void pushRightCornered3() {
        dungeon.addEntity(new Wall(6, 6));
        dungeon.addEntity(new Wall(5, 7));
        Boulder boulder2 = new Boulder(dungeon, 5, 6);
        dungeon.addEntity(boulder2);
        player.setPosition(4, 6);
        player.moveRight();
        assertEquals(boulder2.getX(), 5);
        assertEquals(boulder2.getY(), 5);

    }

    @Test
    public void pushRightCornered4() {
        dungeon.addEntity(new Wall(3, 3));
        dungeon.addEntity(new Wall(4, 4));
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        player.setPosition(2, 4);
        player.moveRight();
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 5);
    }

    @Test
    public void upPortal() {{
        Boulder boulder = new Boulder(dungeon, 3, 4);
        dungeon.addEntity(boulder);
        dungeon.addEntity(new Portal(3, 3, 1));
        dungeon.addEntity(new Portal(4, 5, 1));
        boulder.moveUp();
        assertNotEquals(3, boulder.getX());
        assertNotEquals(3, boulder.getY());
    }}
}