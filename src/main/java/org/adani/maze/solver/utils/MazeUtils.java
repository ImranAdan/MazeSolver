package org.adani.maze.solver.utils;

import java.util.List;

/**
 * Class with static helper methods. Please add
 * any relevant functions that can be reused here.
 */
public class MazeUtils {


    /**
     * Given rows, columns and a list of string lines representing
     * the maze, convert it into a 2D character array.
     *
     * @param numberOfRows    The number of rows associated with the Maze. Height Measure.
     * @param numberOfColumns The number of columns associated with the Maze. Width Measure.
     * @param lines
     * @return A 2 Dimensional array (Matrix) that represents the maze.
     */
    public static char[][] getAs2DCharArrayRepresentation(int numberOfRows, int numberOfColumns, List<String> lines) {
        char[][] grid = new char[numberOfRows][numberOfColumns];
        for (int x = 0; x < numberOfRows; ++x) {
            String[] chars = lines.get(x).trim().split(" ");
            for (int y = 0; y < numberOfColumns; ++y) {
                grid[x][y] = chars[y].charAt(0);
            }
        }
        return grid;
    }

}
