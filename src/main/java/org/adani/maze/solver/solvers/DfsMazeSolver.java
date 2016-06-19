package org.adani.maze.solver.solvers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.adani.maze.solver.model.Maze;
import org.adani.maze.solver.model.Node;

/**
 * DFS implementation.
 */
public final class DfsMazeSolver extends MazeSolver {

    public DfsMazeSolver(Maze maze) {
        super(maze);
    }

    @Override
    public boolean solve() {
        final Maze maze = getMaze();

        final Deque<Node> path = applyDfs(maze);
        if (!path.isEmpty()) {
            maze.state = Maze.MazeState.SOLVED; // FOUND
            maze.matrix[maze.startX][maze.startY] = MazeConstants.START_CHAR;
            maze.matrix[maze.goalX][maze.goalY] = MazeConstants.GOAL_CHAR;
            return true;
        }
        return false;
    }

    private Deque<Node> applyDfs(final Maze maze) {
        final Node start = new Node(maze.startX, maze.startY, maze.matrix[maze.startX][maze.startY]);
        final Node goal = new Node(maze.goalX, maze.goalY, maze.matrix[maze.goalX][maze.goalY]);
        final Deque<Node> visitedPath = new ArrayDeque<Node>();

        Node head = start;
        visitedPath.push(head);

        while (!head.equals(goal)) {
            Queue<Node> children  = expand(maze.matrix, head);

            // Loop until we find a child node that has't been visited.
            while (!children.isEmpty()) {
                final Node child = children.poll();

                if (!visitedPath.contains(child)) {
                    maze.matrix[child.x][child.y] = MazeConstants.PASSED_NODE_CHAR;
                    visitedPath.push(child);

                    if (child.equals(goal)) { 
                        return visitedPath;
                    }

                    head = child;
                    children  = expand(maze.matrix, head);
                    break;
                }
            }

            // visited all children from the current node
            if (allVisited(head, visitedPath)) {
                // backtracking to the start node to find a node who hasn't a visited node.
                while(!head.equals(start)) {
                    head = head.parent;
                    if (!allVisited(head, visitedPath)) {
                        // Found one so let get back to work.
                        break;
                    }
                }
                // We backtracked to the start and all chldren are visited so the maze is full of walls!
                if (head.equals(start) && allVisited(head, visitedPath)) {
                    visitedPath.clear();
                    return visitedPath;
                }
            }
        }
        return visitedPath;
    }

    private boolean allVisited(Node node, Deque<Node> visited) {
        for (int i = 0; i < node.neighbour.size(); i++) {
            if (!visited.contains(node.neighbour.get(i))) {
                return false;
            }
        }
        return true;
    }
    // TODO: considering pull this out and put it up to the MazeSolver class.
    // It is common method and is used in BreathFirstSolver too.
    private Queue<Node> expand(char[][] matrix, Node current) {

        Queue<Node> children = new LinkedList<>();
        Node node;
        if (canMove(matrix, current.x - 1, current.y)) {
            node = new Node(current.x - 1, current.y, matrix[current.x - 1][current.y]);
            setParent(current, children, node);
        }

        if (canMove(matrix, current.x + 1, current.y)) {
            node = new Node(current.x + 1, current.y, matrix[current.x + 1][current.y]);
            setParent(current, children, node);
        }

        if (canMove(matrix, current.x, current.y - 1)) {
            node = new Node(current.x, current.y - 1, matrix[current.x][current.y - 1]);
            setParent(current, children, node);
        }

        if (canMove(matrix, current.x, current.y + 1)) {
            node = new Node(current.x, current.y + 1, matrix[current.x][current.y + 1]);
            setParent(current, children, node);
        }
        // Set neighbours for the current node
        for(Iterator<Node> itr = children.iterator();itr.hasNext();)  {
            current.neighbour.add(itr.next());
        }
        return children;
    }

    private void setParent(Node current, Queue<Node> children, Node node) {
        node.parent = current;
        children.add(node);
    }

    public boolean canMove(char[][] grid, int x, int y) {
        return withInBounds(grid, x, y) && !isWall(grid, x, y);
    }

    private boolean withInBounds(char[][] grid, int x, int y) {
        return !(x < 0 || x >= grid.length || y < 0 || y >= grid.length);
    }

    private boolean isWall(char[][] grid, int x, int y) {
        return grid[x][y] == MazeConstants.WALL_CHAR;
    }
}
