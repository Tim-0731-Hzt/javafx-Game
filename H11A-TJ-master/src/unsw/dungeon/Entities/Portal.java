package unsw.dungeon.Entities;

/**
 * a portal calss
 */
public class Portal extends Entity{

  private int id;
  public Portal(int x, int y,int id) {
    super(x, y);
    this.id = id;
  }

  public int getId() {
    return id;
  }  
}