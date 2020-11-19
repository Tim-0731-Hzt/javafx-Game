package unsw.dungeon.Entities;

import java.util.Timer;
import java.util.TimerTask;

public class Bubbly extends Entity {

    private Player player;

    public Bubbly(int x, int y, Player player) {
        super(x, y);
        this.player = player;
    }

    public void activate() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                player.reduceLife();
                timer.cancel();
            }
        };
        // the player will lose one life 20 seconds later
        timer.schedule(task, 20000);
        // for testing
        // timer.schedule(task, 5000);
    }

}