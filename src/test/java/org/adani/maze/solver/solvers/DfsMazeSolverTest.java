package org.adani.maze.solver.solvers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.adani.maze.solver.model.Maze;
import org.adani.maze.solver.model.MazeFactory;
import org.adani.maze.solver.model.Node;
import org.adani.maze.solver.utils.MazeBeautify;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class DfsMazeSolverTest {
    /**
     * Specify our test candidates as files
     * that will be pulled from test resources. T
     * <p>
     * Theories and JUnit allows to run same test on multiple
     * inputs
     */
    public static
    @DataPoints
    String[] candidates = {
        "/example.maze",
        "/input.maze"
        // Wont work ATM with these...Ignoring for now
//        "/small.maze",
//        "/medium_input.maze",
//        "/large_input.maze",
//        "/unsolved.maze"
    };

    @Test(timeout = 30000L)
    @Theory
    public void testSolve(String candidate) throws IOException {
        String mazeFileLocation = this.getClass().getResource(candidate).getFile();

        Maze maze = MazeFactory.newInstance(mazeFileLocation);
        
        DfsMazeSolver mazeSolver = new DfsMazeSolver(maze);
        
        mazeSolver.solve();
        MazeBeautify.printPretty(maze);
        System.out.println("\n_DONE_\n");

        assertTrue(maze.state == Maze.MazeState.SOLVED);
    }

    @Ignore // Only pass with first @DataPoints above.
    public void testGetAdjacentNeighbours (String candidate) throws IOException { 
        String mazeFileLocation = this.getClass().getResource(candidate).getFile();
        Maze maze = MazeFactory.newInstance(mazeFileLocation);
        
        final Node startNode = new Node(maze.startX, maze.startY, maze.matrix[maze.startX][maze.startY]);
        
        DfsMazeSolver mazeSolver = new DfsMazeSolver(maze);
        mazeSolver.setNeighbours(startNode, maze);
        assertEquals(2, startNode.neighbour.size());
        assertEquals('0', startNode.neighbour.get(0).value);
    }

}
