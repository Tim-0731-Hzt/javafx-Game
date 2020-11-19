package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unsw.dungeon.Entities.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Timer;
import java.util.TimerTask;

public class ExtensionsTest {

    private Dungeon dungeon;
    private Player player;

    @BeforeEach
    void init() {
        this.dungeon = new Dungeon(10, 10);
        this.player = new Player(dungeon, 1, 1);
        dungeon.addEntity((player));
        dungeon.setPlayer(player);
    }

    @Test
    public void gnomeTest() {
        Gnome gnome = new Gnome(3, 4);
        dungeon.addEntity(gnome);
        player.setPosition(3, 5);
        player.moveUp();
        assertEquals(player.getLife(), 2);
    }

    @Test
    public void bubblyTest() {
        assertEquals(player.getLife(), 1);
        dungeon.addEntity(new Bubbly(3, 4, player));
        player.setPosition(3, 4);
        assertEquals(player.getLife(), 1);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                assertEquals(player.getLife(), 0);
                timer.cancel();
            }
        };
        // the effect of the poison will last for 20 seconds
        timer.schedule(task, 20000);
    }

}