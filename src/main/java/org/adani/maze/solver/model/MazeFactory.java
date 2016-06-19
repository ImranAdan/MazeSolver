package org.adani.maze.solver.model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MazeFactory {

    /**
     * Create a new maze given a maze configuration File.
     * The created maze is configured with the following attributes:
     * <p>
     * <pre>
     *      1 - Height & Width, specified as Rows and Columns
     *      2 - Start & End, Specified as StartX and StartY
     *      3 - Goal, Specified as GoalX and GoalY
     *      4 - The Matrix, specified as the rest of the input.
     *  </pre>
     *
     * @param mazeConfigurationFilePath The location of the maze configuration file/
     * @return Maze that is based on the configuration passed.
     * @throws IOException When there is a failure to create a maze given the configuration file.
     */ //TODO: REFACTOR
    public static Maze newInstance(String mazeConfigurationFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(mazeConfigurationFilePath), Charset.defaultCharset());

        String[] goalMeta = lines.get(2).split(" ");
        String[] widthHeightMeta = lines.get(0).split(" ");
        String[] startEndMeta = lines.get(1).split(" ");

        int width = Integer.valueOf(widthHeightMeta[0]);
        int height = Integer.valueOf(widthHeightMeta[1]);
        int startX = Integer.valueOf(startEndMeta[0]);
        int startY = Integer.valueOf(startEndMeta[1]);
        int goalX = Integer.valueOf(goalMeta[0]);
        int goalY = Integer.valueOf(goalMeta[1]);

        List<String> mazeLines = lines.subList(3, lines.size());

        char[][] matrix = linesToMatrix(height, width, mazeLines);

        Queue<Node> nodeSequence = getSequence(height, width, matrix);
        return new Maze(width, height, startX, startY, goalX, goalY, matrix, nodeSequence);

    }

    private static Queue<Node> getSequence(int rows, int cols, char[][] charArray) {

        Queue<Node> mazeNodeSequence = new LinkedList<>();

        for (int x = 0; x < rows; ++x) {
            for (int y = 0; y < cols; ++y) {

                Node currentNode = new Node(x, y, charArray[x][y]);
                List<Node> neighbours = new LinkedList<>();

                //NORTH
                if (x - 1 >= 0) {
                    neighbours.add(new Node(x - 1, y, charArray[x - 1][y]));
                }

                // EAST
                if (y + 1 < cols) {
                    neighbours.add(new Node(x, y + 1, charArray[x][y + 1]));
                }

                // SOUTH
                if (x + 1 < rows) {
                    neighbours.add(new Node(x + 1, y, charArray[x + 1][y]));
                }

                // WEST
                if (y - 1 >= 0) {
                    neighbours.add(new Node(x, y - 1, charArray[x][y - 1]));
                }

                currentNode.neighbour = neighbours;
                mazeNodeSequence.add(currentNode);
            }
        }
        return mazeNodeSequence;
    }

    private static char[][] linesToMatrix(int rows, int cols, List<String> lines) {
        char[][] grid = new char[rows][cols];
        for (int x = 0; x < rows; ++x) {
            String[] chars = lines.get(x).trim().split(" ");
            for (int y = 0; y < cols; ++y) {
                grid[x][y] = chars[y].charAt(0);
            }
        }
        return grid;
    }
}
