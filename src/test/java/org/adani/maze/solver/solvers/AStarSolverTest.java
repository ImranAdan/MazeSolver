package org.adani.maze.solver.solvers;

import org.adani.maze.solver.model.Maze;
import org.adani.maze.solver.model.MazeFactory;
import org.adani.maze.solver.utils.MazeBeautify;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

@RunWith(Theories.class)
public class AStarSolverTest {

    public static
    @DataPoints
    String[] candidates = {
            "/example.maze"
    };

    @Test//(timeout = 30000L)
    @Theory
    public void testSolve(String candidate) throws IOException {

        String mazeFileLocation = this.getClass().getResource(candidate).getFile();

        Maze maze = MazeFactory.newInstance(mazeFileLocation);

        MazeSolver mazeSolver = new AStarSolver(maze);
        mazeSolver.solve();

        MazeBeautify.printPretty(maze);
        System.out.println("\n_DONE_\n");

        assertTrue(maze.state == Maze.MazeState.SOLVED);
    }
}