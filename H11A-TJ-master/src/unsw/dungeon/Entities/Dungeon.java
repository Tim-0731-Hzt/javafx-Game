package unsw.dungeon.Entities;

import java.util.ArrayList;
import java.util.List;

import unsw.dungeon.goals.Goal;

import javafx.scene.image.Image;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private int NoTreasures;
    private Goal goal;
    private Image openDoorImage;
    private Image playerWithSword;
    private String mapFilename;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.NoTreasures = 0;
        this.openDoorImage = null;
        this.playerWithSword = null;
        this.mapFilename = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPlayerTreasure() {
        return this.player.getTreasures();
    }

    public void addEntity(Entity entity) {
        if (entity instanceof Treasure) {
            this.NoTreasures += 1;
        }
        entities.add(entity);
    }

    /**
     * checking if moving to (x, y) in valid
     * 
     * @param x x-coordinates
     * @param y y-coordinates
     * @return if it is valid to travel to location x, y
     */
    public Boolean checkMove(int x, int y) {

        Door door = getDoor(x, y);
        if (door != null) {
            if (playerHasKey(door.getId())) {
                door.setOpen(true);
                player.keyUsed();
                try {
                    door.getView().setImage(openDoorImage);
                } catch (Exception exception) {

                }
            }
        }

        return (!(hasBorder(x, y) || hasClosedDoor(x, y) || hasWall(x, y)));
    }

    public Entity GetEntity(int x, int y) {
        Entity entity = null;
        for (Entity e : entities) {
            if (e == null)
                continue;
            if (e instanceof Player)
                continue;
            if (e.getX() == x && e.getY() == y) {
                entity = e;
                return entity;
            }
        }
        return entity;
    }

    public void removeEntity(Entity e) {
        if (e == null)
            System.out.println("null");
        try {
            e.getView().setImage(null);
        } catch (Exception exception) {

        }

        entities.remove(e);
    }

    public Portal getPortal(int id, int x, int y) {
        Portal portal = null;
        for (Entity e : entities) {
            if (e == null)
                continue;
            if (e instanceof Portal) {
                Portal p = (Portal) e;
                if (p.getId() == id && p.getX() != x && p.getY() != y) {
                    portal = p;
                    return portal;
                }
            }
        }
        return portal;
    }

    private boolean playerHasKey(int id) {
        Key key = player.getKey();
        if (key == null)
            return false;
        if (key.getId() == id)
            return true;
        return false;
    }

    public int getNoTreasures() {
        return NoTreasures;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public int getPlayerX() {
        return player.getX();
    }

    public int getPlayerY() {
        return player.getY();
    }

    /**
     * 
     * @return the x-coordinate of exit
     */
    public int getExitX() {
        for (Entity e : entities) {
            if (e instanceof Exit) {
                return e.getX();
            }
        }
        return -1;
    }

    /**
     * 
     * @return the y-coordinate of exit
     */
    public int getExitY() {
        for (Entity e : entities) {
            if (e instanceof Exit) {
                return e.getY();
            }
        }
        return -1;
    }

    /**
     * 
     * @param x
     * @param y
     * @return if there is a boulder located on x, y
     */
    public boolean hasBoulder(int x, int y) {
        for (Entity e : entities) {
            if (e == null)
                continue;
            if (e instanceof Boulder) {
                if (e.getX() == x && e.getY() == y) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param x
     * @param y
     * @return get the door entity located on x, y
     */
    public Door getDoor(int x, int y) {
        Door door = null;
        for (Entity e : entities) {
            if (e == null)
                continue;
            if (e instanceof Door) {
                Door d = (Door) e;
                if (d.getX() == x && d.getY() == y) {
                    door = d;
                    return door;
                }
            }
        }
        return door;
    }

    /**
     * 
     * @param x
     * @param y
     * @return get the boulder entity located on x, y
     */
    public Boulder getBoulder(int x, int y) {
        for (Entity e : entities) {
            if (e instanceof Boulder) {
                if (e.getX() == x && e.getY() == y) {
                    return ((Boulder) e);
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param x
     * @param y
     * @return get the switch entity located on x, y
     */
    public Switch getSwitch(int x, int y) {
        for (Entity e : entities) {
            if (e instanceof Switch) {
                if (e.getX() == x && e.getY() == y) {
                    return ((Switch) e);
                }
            }
        }
        return null;
    }

    /**
     * 
     * @param x
     * @param y
     * @return if there is a wall located on x,y
     */
    public boolean hasWall(int x, int y) {
        for (Entity e : entities) {
            if (e == null)
                continue;
            if (e.getX() == x && e.getY() == y) {
                if (e instanceof Wall) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param x
     * @param y
     * @return if there exist a closed door on x, y
     */
    public boolean hasClosedDoor(int x, int y) {
        for (Entity e : entities) {
            if (e == null)
                continue;
            if (e.getX() == x && e.getY() == y) {
                if (e instanceof Door) {
                    if (!((Door) e).isOpen())
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * checking if x, y is at or beyond the border of map
     */
    public boolean hasBorder(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    /**
     * 
     * @param x
     * @param y
     * @return if there exist an eneity that will stop the movement
     */
    public boolean hasBlock(int x, int y) {
        return hasBoulder(x, y) || hasWall(x, y) || hasClosedDoor(x, y) || hasBorder(x, y);
    }

    public boolean checkGoals() {
        if (goal.achieved(this)) {
            return true;
        }
        return false;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public boolean isPlayerAlive() {
        if (!player.isAlive()) {
            // System.out.println("game over !");
            return false;
        }
        return true;
    }

    public boolean isAllHostileDied() {
        return player.isAllHostileDied();
    }

    public Portal getPortal(int x, int y) {
        Portal p = null;
        for (Entity e : entities) {
            if (e instanceof Portal) {
                if (e.getX() == x && e.getY() == y) {
                    p = (Portal) e;
                }
            }
        }
        return p;
    }

    /**
     * handling the event of entity traveling through a portal
     * 
     * @param portal_a the src portal
     * @param entity   the entity that will traveel
     */
    public void throughPortal(Portal portal_a, Entity entity) {
        int id = portal_a.getId();
        Portal portal_b = getPortal(id, entity.getX(), entity.getY());
        // finding a valid move
        if (checkMove(portal_b.getX(), portal_b.getY() - 1)) {
            entity.x().set(portal_b.getX());
            entity.y().set(portal_b.getY() - 1);
        } else if (checkMove(portal_b.getX(), portal_b.getY() + 1)) {
            entity.x().set(portal_b.getX());
            entity.y().set(portal_b.getY() + 1);
        } else if (checkMove(portal_b.getX() + 1, portal_b.getY())) {
            entity.x().set(portal_b.getX() + 1);
            entity.y().set(portal_b.getY());
        } else if (checkMove(portal_b.getX() - 1, portal_b.getY())) {
            entity.x().set(portal_b.getX() - 1);
            entity.y().set(portal_b.getY());
        }

    }

    public Image getOpenDoorImage() {
        return openDoorImage;
    }

    public void setOpenDoorImage(Image image) {
        this.openDoorImage = image;
    }

    public Image getPlayerWithSword() {
        return playerWithSword;
    }

    public void setPlayerWithSword(Image playerWithSword) {
        this.playerWithSword = playerWithSword;
    }

    public String getMapFilename() {
        return mapFilename;
    }

    public void setMapFilename(String mapFilename) {
        this.mapFilename = mapFilename;
    }
}