package unsw.dungeon.Entities;

/**
 * a sword class
 */
public class Sword extends Entity{

  // the sword can only be used 5 times
  // after 5 times of hit, the sword will disappear
  int durability;
  public Sword(int x, int y) {
    super(x, y);
    this.durability = 5;
  }

  public int getDurability() {
    return durability;
  }

  public void setDurability(int durability) {
    this.durability = durability;
  }
  
}