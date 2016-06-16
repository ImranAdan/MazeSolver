package org.adani.maze.solver.utils;

import org.adani.maze.solver.model.Maze;

public class MazeBeautify {

    public static void printPretty(Maze m) {
        printPretty(m.rows, m.columns, m.matrix);
    }

    private static void printPretty(int rows, int cols, char[][] matrix) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; ++j)
                builder.append(matrix[i][j]).append(" ");
            builder.append("\n");
        }


        System.out.print(builder.toString().trim());
    }
}
