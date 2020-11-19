package unsw.dungeon.Entities;

/**
 * a switch class
 */
public class Switch extends Entity {

    private boolean triggered;

    public Switch(int x, int y) {
        super(x, y);
        this.triggered = false;
    }

    public void trigger() {
        this.triggered = true;
    }

    public void untrigger() {
        this.triggered = false;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
  
}