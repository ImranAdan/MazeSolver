package org.adani.maze.solver.solvers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.adani.maze.solver.model.Maze;
import org.adani.maze.solver.model.Node;

public final class DfsMazeSolver extends MazeSolver {
    private static char WALL = '1';

    public DfsMazeSolver(Maze maze) {
        super(maze);
    }
    // north, south, west, est neighbours
    // swapping these affects the branch we dfs to i.e left first or right first or middle
    // current order is in pre-order i.e. left first.
    private int[][] adjs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 

    @Override
    public boolean solve() {
        final Maze maze = getMaze();
        final Node start = new Node(maze.startX, maze.startY, maze.matrix[maze.startX][maze.startY]);
        final Node goal = new Node(maze.goalX, maze.goalY, maze.matrix[maze.goalX][maze.goalY]);

        final Deque<Node> visited = new ArrayDeque<Node>();

        Node head = start;
        visited.push(head);
        setNeighbours(head, maze);

        while (!head.equals(goal)) {
            Node child = null;
            // Loop until we find a child node that has't been visited.
            while (!head.neighbour.isEmpty()) {
                child = head.neighbour.remove(0);

                if (!visited.contains(child)) {
                    maze.matrix[child.x][child.y] = 'X';
                    visited.push(child);
                    if (child.equals(goal)) { 
                        maze.state = Maze.MazeState.SOLVED; // FOUND
                        maze.matrix[maze.startX][maze.startY] = 'S';
                        maze.matrix[maze.goalX][maze.goalY] = 'E';
                        return true;
                    }

                    this.setNeighbours(child, maze);
                    head = child;
                    break;
                }

            }

            // visited all children from the current node
            if (allVisited(head, visited)) {
                // backtracking to the start node to find a node who hasn't a visited node.
                while(!head.equals(start)) {
                    head = head.parent;
                    if (!allVisited(head, visited)) {
                        // Found one so let get back to work.
                        break;
                    }
                }
                // We backtracked to the start and all chldren are visited so the maze is full of walls!
                if (head.equals(start) && allVisited(head, visited)) {
                    return false;
                }
            }
        }
        return false;
    }

    private boolean allVisited(Node node, Deque<Node> visited) {
        for (int i =0; i< node.neighbour.size(); i++) {
            if (!visited.contains(node.neighbour.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * This set the parent of the current node and all of its children node from the maze.
     */
    void setNeighbours(Node current, Maze maze) {
        final char[][] matrix = maze.matrix;
        final List<Node> neighbours = new LinkedList<>();
        for (int i = 0; i< adjs.length; i++) {
            //User and abuse exception. We dont care to check if a node is at the edge.
            try {
 
                final Node n = new Node(
                            current.x + adjs[i][0],
                            current.y + adjs[i][1],
                            matrix[current.x + adjs[i][0]][current.y + adjs[i][1]]);
                
                if (n.value != WALL) {
                    n.parent = current;
                    neighbours.add(n);
                }
            }
            catch (ArrayIndexOutOfBoundsException ex) {}
        }
        current.neighbour = neighbours;
    }
}
