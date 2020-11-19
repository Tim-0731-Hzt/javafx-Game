package unsw.dungeon.EnemyMove;

public class queueNode {
    Point pt;
    int dist;

    public queueNode(Point pt, int dist) {
        this.pt = pt;
        this.dist = dist;
    }
}