package org.adani.maze.solver.solvers;

import org.adani.maze.solver.model.HeuristicFunction;
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

    private static void log(String message) {
        System.out.println(message);
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
        Queue<Node> shortTestPath = getShortTestPath();
        return shortTestPath;
    }

    /**
     * Experemential ->
     * 1 - Build the map tree and then traverse the best path; issue encountered on large trees
     */
    private Queue<Node> getShortTestPath() {

        path.clear();
        openList.clear();
        closedList.clear();

        Node start = getMaze().nodeSequence.element();


        openList.add(start);
        log("Adding: " + start.toString() + " _TO OPEN_LIST");
        Queue<Node> generated = applySP(start, new Node(getMaze().goalX, getMaze().goalY, getMaze().matrix[getMaze().goalX][getMaze().goalY]));
        return generated;

    }

    private Queue<Node> applySP(Node start, Node goal) {


        while (!openList.isEmpty()) {
            Node head = openList.element();
            for (Node node : openList) {
                if (node.f < head.f) {
                    head = node;
                }
            }

            if (head.x == getMaze().goalX && head.y == getMaze().goalY) {
                return reconstructPath(head);
            }

            openList.remove(head);
            closedList.add(head);

            for (Node neighbour : head.neighbour) {
                boolean isBetterNode;

                if (closedList.contains(neighbour) || getMaze().matrix[neighbour.x][neighbour.y] == '1') {
                    continue;
                }


                int g = HeuristicFunction.MANHATTAN_DISTANCE.apply(neighbour, start);
                if (!openList.contains(neighbour)) {
                    openList.add(neighbour);
                    isBetterNode = true;
                } else isBetterNode = g < HeuristicFunction.MANHATTAN_DISTANCE.apply(head, start);

                if (isBetterNode) {
                    log("IS_BETTER: " + head.toString());

                    neighbour.parent = head;
                    neighbour.g = g;
                    neighbour.h = HeuristicFunction.MANHATTAN_DISTANCE.apply(neighbour, goal);
                    neighbour.f = neighbour.g + neighbour.h;
                }

                System.out.println(head.toString());
            }
        }

        return new LinkedList<>();
    }

    /*
        private Queue<Node> applySearch() {

            Node start = new Node(getMaze().startX, getMaze().startY, getMaze().matrix[getMaze().startX][getMaze().startY]);
            Node goal = new Node(getMaze().goalX, getMaze().goalY, getMaze().matrix[getMaze().goalX][getMaze().goalY]);

            while (!openList.isEmpty()) {
                Node head = openList.element();
                for (Node n : openList)
                {
                    if (n.f < head.f)
                    {
                        head = n;
                    }
                }

                if (head.x == getMaze().goalX
                        && head.y == getMaze().goalY) {
                    return reconstructPath(head);
                }

                openList.remove(head);
                closedList.add(head);


                /**
                 *
                 * Set the head here?
                 *
                 */
/*


            for (Node neighbour : head.neighbour) {
                boolean isBetterNode;
                if (closedList.contains(neighbour)
                        || getMaze().matrix[neighbour.x][neighbour.y] == '1') {
                    continue;
                }


                int g = HeuristicFunction.MANHATTAN_DISTANCE.apply(neighbour, start);
                if (!openList.contains(neighbour))
                {
                    openList.add(neighbour);
                    isBetterNode = true;
                }
                else if (g < HeuristicFunction.MANHATTAN_DISTANCE.apply(head, start))
                {
                    isBetterNode = true;
                }
                else
                {
                    isBetterNode = false;
                }

                if (isBetterNode) {
                    neighbour.parent = head;
                    neighbour.g = g;
                    neighbour.h = HeuristicFunction.MANHATTAN_DISTANCE.apply(neighbour, goal);
                    neighbour.f = neighbour.g + neighbour.h;
                }
            }
        }


        return null;
    }

*/
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
}
