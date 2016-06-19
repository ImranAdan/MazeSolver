package org.adani.maze.solver.model.fast;

import org.adani.maze.solver.model.Node;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FastMazeFactory {


    /**
     * Create a FastMaze instance. A fast
     * maze is a maze that is backed by a Map
     *
     * @param mazeConfigurationFilePath
     *  The location of the maze file
     * @return
     *  A fast maze
     * @throws IOException
     *  Throws an exception when there is an issue reading the
     *  file.
     * */
    public static FastMaze newFastMazeInstance(String mazeConfigurationFilePath) throws IOException {
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


        Map<Point, Node> mazeMap = toMatrix(height, width, lines.subList(3, lines.size()));


        return new FastMaze(height, width, mazeMap.get(new Point(startX, startY)), mazeMap.get(new Point(goalX, goalY)), mazeMap);
    }

    private static Map<Point, Node> toMatrix(int rows, int cols, List<String> lines) {

        Map<Point, Node> mazeMap = new HashMap<>();
        for (int x = 0; x < rows; ++x) {
            String[] chars = lines.get(x).trim().split(" ");
            for (int y = 0; y < cols; ++y) {
                Node currentNode = new Node(x, y, chars[y].charAt(0));
                List<Node> neighbours = new LinkedList<>();

                //NORTH
                if (x - 1 >= 0) {
                    neighbours.add(new Node(x - 1, y, chars[y].charAt(0)));
                }

                // EAST
                if (y + 1 < cols) {
                    neighbours.add(new Node(x, y + 1, chars[y].charAt(0)));
                }

                // SOUTH
                if (x + 1 < rows) {
                    neighbours.add(new Node(x + 1, y, chars[y].charAt(0)));
                }

                // WEST
                if (y - 1 >= 0) {
                    neighbours.add(new Node(x, y - 1, chars[y].charAt(0)));
                }

                currentNode.neighbour = neighbours;
                mazeMap.put(new Point(x, y), currentNode);
            }
        }
        return mazeMap;
    }

}
