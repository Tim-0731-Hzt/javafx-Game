package unsw.dungeon.Entities;

import java.util.Timer;
import java.util.TimerTask;

/**
 * invincible potion class
 */
public class Invincibility extends Entity {

  private Player player;

  public Invincibility(int x, int y) {
    super(x, y);
  }
  
  /**
   * after picked up, the player will be in invinciblee state
   * 10 seconds later, the playeer will return to normal state
   */
  public void pickedUp() {
    player.setInvincible(true);
    Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
        player.setInvincible(false);
        timer.cancel();
			}
    };
    // the effect of the potion will last for 10 seconds
    timer.schedule(task, 10000);
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

}