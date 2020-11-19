package unsw.dungeon.Entities;

import unsw.dungeon.EnemyMove.BFS;
import unsw.dungeon.EnemyMove.Point;


public class MovableEntity extends Entity{

    
    private Player player; // the current player
    private Dungeon dungeon; // the current dungeon
    private int play_x; // the x position of player
    private int play_y; // the y position of player
    private BFS bfs; // the bfs strategy
    private Point play; // the coordinates of player
    private Point enemy; // the coordinates of enemy
    private boolean alive; // if the enemy is alive
    private boolean attacking; // if the enemy is attaking

    public MovableEntity(int x, int y, Player p) {
        super(x, y);
        this.play_x = 0;
        this.play_y = 0;
        this.player = p;
        this.dungeon = p.getDungeon();
        bfs = new BFS(dungeon.getWidth(), dungeon.getHeight(), dungeon);
        play = new Point(player.getX(), player.getY());
        enemy = new Point(x, y);
        p.attach(this);
        this.alive = true;
        this.attacking = true;
    }

    /**
     * Observer pattern update change in player
     */
    public void update() {
        play_x = player.getX();
        play_y = player.getY();
        play.setX(play_x);
        play.setY(play_y);
        if (play.equals(enemy)) {
            player.collision(this);
            return;
        }
        // if the player is in invincible state
        // run away
        if (player.isInvincible()) {
            setAttacking(false);
            moveAwayPlayer();
            // otherwise keep attacking
        } else {
            setAttacking(true);
            moveToPlayer(bfs.getMove(enemy, play));
        }
        // if enemy collides with player
        // deal with collision
        if (play.equals(enemy)) {
            player.collision(this);
        }

    }

    /**
     * attacking player
     * 
     * @param s the instruction of attacking
     */
    public void moveToPlayer(String s) {
        int x = this.getX();
        int y = this.getY();
        switch (s) {
            case "up":
                y -= 1;
                break;
            case "down":
                y += 1;
                break;
            case "left":
                x -= 1;
                break;
            case "right":
                x += 1;
                break;
        }
        setPosition(x, y);
    }

    /**
     * the enemy will run away from player if the player is in invincible state
     */
    public void moveAwayPlayer() {
        int px = player.getX();
        int py = player.getY();
        int x = this.getX();
        int y = this.getY();
        if (x < px && dungeon.checkMove(x - 1, y)) {
            x -= 1;
        } else if (x > px && dungeon.checkMove(x + 1, y)) {
            x += 1;
        } else if (y < py && dungeon.checkMove(x, y - 1)) {
            y -= 1;
        } else if (y > py && dungeon.checkMove(x, y + 1)) {
            y += 1;
        }
        setPosition(x, y);
    }

    public void setPosition(int x, int y) {
        // handling moving through a portal
        Portal e = dungeon.getPortal(x, y);
        if (e != null) {
            dungeon.throughPortal(e, this);
            // normal move
        } else {
            x().set(x);
            y().set(y);
        }
        enemy.setX(this.getX());
        enemy.setY(this.getY());
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

}