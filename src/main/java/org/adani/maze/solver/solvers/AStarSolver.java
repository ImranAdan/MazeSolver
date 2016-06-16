package org.adani.maze.solver.solvers;

import org.adani.maze.solver.model.Maze;
import org.adani.maze.solver.model.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AStarSolver extends MazeSolver {

    private final Queue<Node> path;
    private final Queue<Node> openList;
    private final Queue<Node> closedList;

    public AStarSolver(Maze maze) {
        super(maze);
        path = new LinkedList<>();
        openList = new LinkedList<>();
        closedList = new LinkedList<>();
    }

    @Override
    public boolean solve() {

        Queue<Node> sp = applyAStarSearch();
        if (!sp.isEmpty()) {

            getMaze().state = Maze.MazeState.SOLVED;
            for (Node n : path)
                getMaze().matrix[n.x][n.y] = 'X';

            getMaze().matrix[getMaze().startX][getMaze().startY] = 'S';
            getMaze().matrix[getMaze().goalX][getMaze().goalY] = 'E';

            return true;

        }

        return false;
    }

    private Queue<Node> applyAStarSearch() {
        path.clear();
        openList.clear();
        closedList.clear();

        openList.add(new Node(getMaze().startX, getMaze().startY, getMaze().matrix[getMaze().startX][getMaze().startY]));
        Queue<Node> generated = applySearch();
        return generated;
    }

    private Queue<Node> applySearch() {

        Node start = new Node(getMaze().startX, getMaze().startY, getMaze().matrix[getMaze().startX][getMaze().startY]);
        Node goal = new Node(getMaze().goalX, getMaze().goalY, getMaze().matrix[getMaze().goalX][getMaze().goalY]);

        while (!openList.isEmpty()) {
            Node head = openList.element();
            for (Node n : openList) {
                if (n.f < head.f) {
                    head = n;
                }
            }

            if (head.x == getMaze().goalX && head.y == getMaze().goalY) // Goal Check
            {
                return reconstructPath(head);
            }

            openList.remove(head);
            closedList.add(head);

            for (Node neighbour : head.neighbour) {
                boolean isBetterNode;
                if (closedList.contains(neighbour) || getMaze().matrix[neighbour.x][neighbour.y] == '1') {
                    continue;
                }


                int neighbourDistanceFromStart = Heuristics.getManhattanDistance(neighbour, start);
                if (!openList.contains(neighbour)) {
                    openList.add(neighbour);
                    isBetterNode = true;
                } else if (neighbourDistanceFromStart < Heuristics.getManhattanDistance(neighbour, start)) {
                    isBetterNode = true;
                } else {
                    isBetterNode = false;
                }

                if (isBetterNode) {
                    neighbour.parent = head;
                    neighbour.g = neighbourDistanceFromStart;
                    neighbour.h = Heuristics.getManhattanDistance(neighbour, goal);
                    neighbour.f = neighbour.g + neighbour.h;
                }
            }
        }


        return null;
    }


    public Queue<Node> getOpenList() {
        return openList;
    }

    public Queue<Node> getClosedList() {
        return closedList;
    }


    private Queue<Node> reconstructPath(Node child) {
        while (child.parent != null) {
            path.add(child);
            child = child.parent;
        }

        reverseQueue();

        return path;
    }


    private void reverseQueue() {
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < path.size(); ++i) {
            stack.add(path.remove());
            path.add(stack.pop());
        }
    }

    private static class Heuristics {


        public static int getManhattanDistance(Node current, Node goal) {
            return (Math.abs(current.x - goal.x) + Math.abs(current.y - goal.y));
        }


    }
}
