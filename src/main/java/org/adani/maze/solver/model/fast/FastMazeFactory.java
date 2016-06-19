package org.adani.maze.solver.model.fast;

import org.adani.maze.solver.model.Maze;
import org.adani.maze.solver.model.Node;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FastMazeFactory {


    /**
     * TODO: Implement fast maze
     *
     * @param mazeConfigurationFilePath
     * @return
     * @throws IOException
     */
    public static Maze newFastInstance(String mazeConfigurationFilePath) throws IOException {

        Map<Point, Node> mazeMap = toMatrix(rows, cols, lines);
        return new FastMaze(height, width, start, goal, mazeMap);
    }

    private static Map<Point, Node> toMatrix(int rows, int cols, List<String> lines) {

        Map<Point, Node> mazeMap = new HashMap<>();

        for (int x = 0; x < rows; ++x) {
            String[] chars = lines.get(x).trim().split(" ");
            for (int y = 0; y < cols; ++y) {
                mazeMap.put(new Point(x, y), new Node(x, y, chars[y].charAt(0)));
            }
        }
        return mazeMap;
    }

}
