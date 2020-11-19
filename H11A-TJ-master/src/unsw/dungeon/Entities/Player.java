package unsw.dungeon.Entities;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private List<Treasure> treasures;
    private Key key;
    private List<Enemy> enemies;
    private List<Hound> hounds;
    private boolean invincible;
    private int life;
    private Sword sword;
    private boolean testing;
    private Image playerWithSword;
    private Image playerImage;

    /**
     * Create a player positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        key = null;
        treasures = new ArrayList<>();
        enemies = new ArrayList<>();
        hounds = new ArrayList<>();
        this.invincible = false;
        this.life = 1;
        this.sword = null;
        this.testing = false;
        this.playerWithSword = null;
        this.playerImage = null;
    }

    /**
     * try to move the player up
     */
    public void moveUp() {
        int x = getX();
        int y = getY();
        if (y > 0 && dungeon.checkMove(x, y - 1)) {
            Boulder e = dungeon.getBoulder(x, y - 1);
            if (e != null) {
                if (e.moveUp()) {
                    y().set(y - 1);
                }
            } else {
                y().set(y - 1);
            }
        }
        Entity_grab();
        if (isTesting())
            notifyEnemies();
    }

    /**
     * try to move the player down
     */
    public void moveDown() {
        int x = getX();
        int y = getY();
        if (y > 0 && dungeon.checkMove(x, y + 1)) {
            Boulder e = dungeon.getBoulder(x, y + 1);
            if (e != null) {
                if (e.moveDown()) {
                    y().set(y + 1);
                }
            } else {
                y().set(y + 1);
            }
        }
        Entity_grab();
        if (isTesting())
            notifyEnemies();
    }

    /**
     * try to move the player left
     */
    public void moveLeft() {
        int x = getX();
        int y = getY();
        if (y > 0 && dungeon.checkMove(x - 1, y)) {
            Boulder e = dungeon.getBoulder(x - 1, y);
            if (e != null) {
                if (e.moveLeft()) {
                    x().set(x - 1);
                }
            } else {
                x().set(x - 1);
            }
        }
        Entity_grab();
        if (isTesting())
            notifyEnemies();
    }

    /**
     * try to move the player right
     */
    public void moveRight() {
        int x = getX();
        int y = getY();
        if (y > 0 && dungeon.checkMove(x + 1, y)) {
            Boulder e = dungeon.getBoulder(x + 1, y);
            if (e != null) {
                if (e.moveRight()) {
                    x().set(x + 1);
                }
            } else {
                x().set(x + 1);
            }
        }
        Entity_grab();
        if (isTesting())
            notifyEnemies();
    }

    /**
     * pick up entities on the floor where the player is located
     */
    public void Entity_grab() {
        Entity e = dungeon.GetEntity(getX(), getY());
        if (e == null)
            return;
        if (e instanceof Treasure) {
            treasures.add((Treasure) e);
            dungeon.removeEntity(e);
        } else if (e instanceof Key) {
            if (key == null) {
                key = ((Key) e);
                dungeon.removeEntity(e);
            }
        } else if (e instanceof Sword) {
            this.sword = (Sword) e;

            try {
                playerWithSword = dungeon.getPlayerWithSword();
                playerImage = this.getView().getImage();
                this.getView().setImage(playerWithSword);

            } catch (Exception exception) {

            }
            dungeon.removeEntity(e);
        } else if (e instanceof Invincibility) {
            this.invincible = true;
            ((Invincibility) e).setPlayer(this);
            ((Invincibility) e).pickedUp();
            dungeon.removeEntity(e);
        } else if (e instanceof Portal) {
            Portal portal_a = (Portal) e;
            dungeon.throughPortal(portal_a, this);
            Entity_grab();
        } else if (e instanceof Gnome) {
            this.life += 1;
            dungeon.removeEntity(e);
        } else if (e instanceof Bubbly) {
            Bubbly bubbly = (Bubbly) e;
            bubbly.activate();
            dungeon.removeEntity(e);
        }
    }

    public void keyUsed() {
        this.key = null;
    }

    public int getTreasures() {
        return this.treasures.size();
    }

    /**
     * observer pattern: attach an observer
     * 
     * @param movableEntity the observer to be attached
     */
    public void attach(MovableEntity movableEntity) {
        if (movableEntity instanceof Enemy) {
            enemies.add((Enemy) movableEntity);
        }
        if (movableEntity instanceof Hound) {
            hounds.add((Hound) movableEntity);
        }
        // enemy.update();
    }

    /**
     * observer pattern notify all
     */
    public void notifyEnemies() {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive()) {
                enemy.update();
            }
        }
    }

    public void notifyHounds() {
        for (Hound hound : hounds) {
            if (hound.isAlive()) {
                hound.update();
            }
        }
    }

    public boolean isAllEnemiesDied() {
        for (Enemy enemy : enemies) {
            if (enemy.isAlive())
                return false;
        }
        return true;
    }

    public boolean isAllHoundsDied() {
        for (Hound hound : hounds) {
            if (hound.isAlive())
                return false;
        }
        return true;
    }

    public boolean isAllHostileDied() {
        return isAllEnemiesDied() && isAllHoundsDied();
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public boolean hasSword() {
        if (this.sword != null) {
            if (this.sword.getDurability() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * handling the collition of eenemy with player
     * 
     * @param movableEntity
     */
    public void collision(MovableEntity movableEntity) {
        if (this.invincible || hasSword()) {
            killHostile(movableEntity);

        } else {
            killPlayer(movableEntity);
        }
    }

    /**
     * kill the given enemy
     * 
     * @param movableEntity
     */
    public void killHostile(MovableEntity movableEntity) {
        // removeEnemy(enemy);
        movableEntity.setAlive(false);
        dungeon.removeEntity(movableEntity);
        if (!isInvincible()) {
            sword.setDurability(sword.getDurability() - 1);
            if (sword.getDurability() == 0) {
                try {
                    this.getView().setImage(playerImage);
                } catch (Exception e) {

                }

            }
        }
    }

    public boolean isAlive() {
        if (getLife() > 0)
            return true;
        else
            return false;
    }

    /**
     * Try to kill the current player
     */
    public void killPlayer(MovableEntity movableEntity) {
        life -= 1;
        if (!isAlive())
            dungeon.removeEntity(this);
        else {
            movableEntity.setAlive(false);
            dungeon.removeEntity(movableEntity);
        }

    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public void setPosition(int x, int y) {
        if (dungeon.checkMove(x, y)) {
            this.x().set(x);
            this.y().set(y);
            Entity_grab();
            notifyEnemies();
        }
    }

    public Key getKey() {
        return key;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Sword getSword() {
        return sword;
    }

    public void setSword(Sword sword) {
        this.sword = sword;
    }

    public boolean isTesting() {
        return testing;
    }

    public void setTesting(boolean testing) {
        this.testing = testing;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void reduceLife() {
        this.life -= 1;
    }
}