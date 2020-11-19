package unsw.dungeon.Entities;

/**
 * a boulder class
 */
public class Boulder extends Entity {

    private Dungeon dungeon;

    public Boulder(int x, int y) {
        super(x, y);
    }

    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * checking if it is valid to move this boulder up
     * 
     * @return if it is valid to move this boulder up
     */
    public boolean moveUp() {

        int x = getX();
        int y = getY();
        int dst_x = x;
        int dst_y = y;
        // if the square above of this boulder is a wall or boulder
        // then this boulder cannot be pushed up
        if (dungeon.hasBlock(x, y - 1)) {
            // if the square on the left and right of this boulder both can be pushed or
            // both cannot be pushed, then this boulder cannot be pushed up
            if (dungeon.hasBlock(x - 1, y) == dungeon.hasBlock(x + 1, y)) {
                return false;
                // if the square on the left cannot be pushed, then this boulder will be
                // pushed to to the square on the right
            } else if (dungeon.hasBlock(x - 1, y)) {
                dst_x = x + 1;
                // if the square on the right cannot be pushed, then this boulder will be
                // pushed to to the square on the left
            } else if (dungeon.hasBlock(x + 1, y)) {
                dst_x = x - 1;
            }
            // normal push down
        } else {
            dst_y = y - 1;
        }
        
        updatePosition(x, y, dst_x, dst_y);

        return true;
    }

    /**
     * checking if it is valid to move this boulder down
     * 
     * @return if it is valid to move this boulder down
     */
    public boolean moveDown() {

        int x = getX();
        int y = getY();
        int dst_x = x;
        int dst_y = y;
        // if the below square of this boulder is a wall or boulder
        // then this boulder cannot be pushed down
        if (dungeon.hasBlock(x, y + 1)) {
            // if the square on the left and right of this boulder both can be pushed or
            // both cannot be pushed, then this boulder cannot be pushed down
            if (dungeon.hasBlock(x - 1, y) == dungeon.hasBlock(x + 1, y)) {
                return false;
                // if the square on the left cannot be pushed, then this boulder will be
                // pushed to to the square on the right
            } else if (dungeon.hasBlock(x - 1, y)) {
                dst_x = x + 1;
                // if the square on the right cannot be pushed, then this boulder will be
                // pushed to to the square on the right
            } else if (dungeon.hasBlock(x + 1, y)) {
                dst_x = x - 1;
            }
            // normal push down
        } else {
            dst_y = y + 1;
        }
        updatePosition(x, y, dst_x, dst_y);
        return true;

    }

    /**
     * checking if it is valid to move this boulder left
     * 
     * @return if it is valid to move this boulder left
     */
    public boolean moveLeft() {

        int x = getX();
        int y = getY();
        int dst_x = x;
        int dst_y = y;
        // if the left of this boulder is a wall or boulder
        // then this boulder cannot be pushed to the left
        if (dungeon.hasBlock(x - 1, y)) {
            // if the square above and below this boulder both can be pushed or
            // both cannot be pushed, then this boulder cannot be pushed to the left
            if (dungeon.hasBlock(x, y + 1) == dungeon.hasBlock(x, y - 1)) {
                return false;
                // if the square below cannot be pushed, then this boulder will be
                // pushed to to the square above
            } else if (dungeon.hasBlock(x, y + 1)) {
                dst_y = y - 1;
                // if the square above cannot be pushed, then this boulder will be
                // pushed to to the square below
            } else if (dungeon.hasBlock(x, y - 1)) {
                dst_y = y + 1;
            }
            // normal push to left
        } else {
            dst_x = x - 1;
        }
        updatePosition(x, y, dst_x, dst_y);
        return true;
    }

    /**
     * checking if it is valid to move this boulder right
     * 
     * @return if it is valid to move this boulder right
     */
    public boolean moveRight() {

        int x = getX();
        int y = getY();
        int dst_x = x;
        int dst_y = y;
        // if the right of this boulder is a wall or boulder
        // then this boulder cannot be pushed to the right
        if (dungeon.hasBlock(x + 1, y)) {
            // if the square above and below this boulder both can be pushed or
            // both cannot be pushed, then this boulder cannot be pushed to the left
            if (dungeon.hasBlock(x, y + 1) == dungeon.hasBlock(x, y - 1)) {
                return false;
                // if the square below cannot be pushed, then this boulder will be
                // pushed to to the square above
            } else if (dungeon.hasBlock(x, y + 1)) {
                dst_y = y - 1;
                // if the square above cannot be pushed, then this boulder will be
                // pushed to to the square below
            } else if (dungeon.hasBlock(x, y - 1)) {
                dst_y = y + 1;
            }
            // normal push to left
        } else {
            dst_x = x + 1;
        }
        updatePosition(x, y, dst_x, dst_y);
        return true;

    }

    public void updatePosition(int x, int y, int dst_x, int dst_y) {
        x().set(dst_x);
        y().set(dst_y);
        // check if the boulder is pushed to a switch
        Switch s = dungeon.getSwitch(dst_x, dst_y);
        if (s != null) {
            s.trigger();
        }
        // check if the boulder is pushed away from a switch
        Switch s2 = dungeon.getSwitch(x, y);
        if (s2 != null) {
            s2.untrigger();
        }

        // handling boulder going through a portal
        Portal p = dungeon.getPortal(dst_x, dst_y);
        if (p != null) {
            dungeon.throughPortal(p, this);
            // after teleported, check if there is a switch on current location
            Switch s3 = dungeon.getSwitch(getX(), getY());
            if (s3 != null) {
                s3.trigger();
            }    
        }

    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

}