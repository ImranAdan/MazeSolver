package org.adani.maze.solver.solvers;

import org.adani.maze.solver.model.Maze;
import org.adani.maze.solver.model.Node;

import java.util.LinkedList;
import java.util.Queue;


/**
 * Solve the Maze by applying a Breadth First Search
 */
public class BreadthFirstSolver extends MazeSolver {

    public BreadthFirstSolver(Maze maze) {
        super(maze);
    }

    @Override
    public boolean solve() {

        final Maze maze = getMaze();

        Queue<Node> path = applyBFS(maze);

        if (path.isEmpty()) {

            maze.state = Maze.MazeState.SOLVED;
            for (Node n : path)
                maze.matrix[n.x][n.y] = 'X';

            maze.matrix[maze.startX][maze.startY] = 'S';
            maze.matrix[maze.goalX][maze.goalY] = 'E';
            return true;
        }

        return false;
    }

    private Queue<Node> applyBFS(Maze maze) {
        Queue<Node> agenda = new LinkedList<>();
        Node root = new Node(maze.startX, maze.startY, maze.matrix[maze.startX][maze.startY]);
        agenda.add(root);
        return applySearch(agenda, maze, maze.matrix);
    }

    private Queue<Node> applySearch(Queue<Node> agenda, Maze maze, char[][] matrix) {
        while (!agenda.isEmpty()) {

            Node head = agenda.remove();

            if (head.x == maze.goalX && head.y == maze.goalY) { // GOAL CHECK
                return agenda;
            } else {
                matrix[head.x][head.y] = 'X'; // VISITED CELL
                Queue<Node> children = expand(matrix, head);
                agenda.addAll(children);
            }
        }

        return agenda;
    }

    private Queue<Node> expand(char[][] matrix, Node agenda) {

        Queue<Node> children = new LinkedList<>();

        if (canMove(matrix, agenda.x - 1, agenda.y)) {
            children.add(new Node(agenda.x - 1, agenda.y, matrix[agenda.x - 1][agenda.y]));
        }

        if (canMove(matrix, agenda.x + 1, agenda.y)) {
            children.add(new Node(agenda.x + 1, agenda.y, matrix[agenda.x + 1][agenda.y]));
        }

        if (canMove(matrix, agenda.x, agenda.y - 1)) {
            children.add(new Node(agenda.x, agenda.y - 1, matrix[agenda.x][agenda.y - 1]));
        }

        if (canMove(matrix, agenda.x, agenda.y + 1)) {
            children.add(new Node(agenda.x, agenda.y + 1, matrix[agenda.x][agenda.y + 1]));
        }

        return children;
    }

    public boolean canMove(char[][] grid, int x, int y) {
        return !(x < 0 || x >= grid.length || y < 0 || y >= grid.length) && (grid[x][y] != '1');
    }
}
