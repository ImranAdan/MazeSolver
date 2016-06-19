package org.adani.maze.solver.model.fast;

import org.adani.maze.solver.model.Node;

import java.awt.*;
import java.util.Collection;
import java.util.Map;

/**
 * FastMaze is a Maze back with a HashMap
 * for quick access of nodes. The nodes are
 * accessed via the x+y location on a Map
 */
public class FastMaze {

    private final int height;
    private final int width;

    private final Node start;
    private final Node goal;
    private final Map<Point, Node> mazeMap;

    public FastMaze(int height, int width, Node start, Node goal, Map<Point, Node> mazeMap) {
        this.height = height;
        this.width = width;
        this.mazeMap = mazeMap;
        this.start = start;
        this.goal = goal;
    }

    /**
     * Get the node at the given point.
     *
     * @param p Point, which is the x&y co-ordinates
     * @return
     */
    public Node atLocation(Point p) {
        return mazeMap.get(p);
    }

    public Collection<Node> getAllNodes() {
        return mazeMap.values();
    }

    public Map<Point, Node> getMazeMap() {
        return mazeMap;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Node getStart() {
        return start;
    }

    public Node getGoal() {
        return goal;
    }
}
