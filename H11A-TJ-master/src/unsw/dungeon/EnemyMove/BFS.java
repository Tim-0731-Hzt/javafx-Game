package unsw.dungeon.EnemyMove;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import unsw.dungeon.Entities.Dungeon;

/**
 * a calss that use BFS to find the sortest path from one point to another point
 */
public class BFS {

    private int width;
    private int height;
    private Dungeon dungeon;
    static int xNum[] = { -1, 0, 0, 1 };
    static int yNum[] = { 0, -1, 1, 0 };

    /**
     * use BFS to find the sortest path from one point to another point
     * @param src point of source
     * @param dest point of destination
     * @return the shortest path length from src to dst
     */
    public int getPath(Point src, Point dest) {

        boolean[][] visited = new boolean[width][height];
        visited[src.x][src.y] = true;

        Queue<queueNode> q = new LinkedList<>();

        // Distance of source cell is 0
        queueNode s = new queueNode(src, 0);
        // Enqueue source cell
        q.add(s); 

        // Do BFS
        while (!q.isEmpty()) {
            queueNode curr = q.peek();
            Point pt = curr.pt;

            // If reached the destination cell, return
            if (pt.x == dest.x && pt.y == dest.y)
                return curr.dist;

            // dequeue a cell in the queue and enqueue its adjacent cells
            q.remove();

            // for the 4 adjacent cells
            for (int i = 0; i < 4; i++) {
                int x = pt.x + xNum[i];
                int y = pt.y + yNum[i];
                // if adjacent cell can be visited and not visited yet, enqueue it.
                if (!this.dungeon.hasBlock(x, y) && !visited[x][y]) {
                    visited[x][y] = true;
                    queueNode Adjcell = new queueNode(new Point(x, y), curr.dist + 1);
                    q.add(Adjcell);
                }
            }
        }

        // Return -1 if destination cannot be reached
        return -1;
    }

    /**
     * for the 4 adjacency points, find which way gives the shortest path
     * @param src point of source
     * @param dest point of destination
     * @return the movement instruction of enemy
     */
    public String getMove(Point src, Point dest) {
        int min = -1;
        int minPos = 0;
        ArrayList<String> move = new ArrayList<String>(Arrays.asList("left", "up", "down", "right"));
        // for the 4 adjacency points, find which way gives the shortest path
        for (int i = 0; i < 4; i++) {
            int x = src.getX() + xNum[i];
            int y = src.getY() + yNum[i];
            if (x < 0 || x >= width || y < 0 || y >= height)
                continue;
            if (!this.dungeon.hasBlock(x, y)) {
                int result = getPath(new Point(x, y), dest);
                if (min == -1 || result < min) {
                    min = result;
                    minPos = i;
                }
            }
        }
        return move.get(minPos);
    }

    public BFS(int width, int height, Dungeon dungeon) {
        this.width = width;
        this.height = height;
        this.dungeon = dungeon;
    }
}