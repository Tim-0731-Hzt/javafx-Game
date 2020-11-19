package unsw.dungeon.Entities;

/**
 * a door class
 */
public class Door extends Entity {
  private int id;
  private boolean open;
  public Door(int x, int y,int id) {
    super(x, y);
    this.id = id;
    this.open = false;
  }

  public int getId() {
    return id;
  }

  public boolean isOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
  }
  
}