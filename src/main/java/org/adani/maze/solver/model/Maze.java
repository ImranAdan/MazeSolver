package org.adani.maze.solver.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Maze {

    public final int rows;
    public final int columns;

    public final int startX;
    public final int startY;

    public final int goalX;
    public final int goalY;

    public final char[][] matrix;

    public MazeState state = MazeState.UNSOLVED;;

    public Maze(int rows, int columns, int startX, int startY, int goalX, int goalY, char[][] matrix) {
        this.rows = rows;
        this.columns = columns;
        this.startX = startX;
        this.startY = startY;
        this.goalX = goalX;
        this.goalY = goalY;
        this.matrix = matrix;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public enum MazeState {

        SOLVED("[MAZE SOLVED]"),
        UNSOLVED("[MAZE UNSOLVED] - NO SOLUTION FOR THIS MAZE");

        private final String state;

        MazeState(String state) {
            this.state = state.toLowerCase();
        }

        @Override
        public String toString() {
            return "[state=" + state + "]";
        }
    }
}
