package org.adani.maze.solver.model.fast;

import org.adani.maze.solver.model.Node;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.awt.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Theories.class)
public class FastMazeFactoryTest {

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
            "/example.maze"
    };

    @Theory
    @Test
    public void testNewFastMazeInstance(String candidate) throws Exception {
        String mazeFileLocation = this.getClass().getResource(candidate).getFile().substring(1);
        final FastMaze fastMaze = FastMazeFactory.newFastMazeInstance(mazeFileLocation);

        Node start = fastMaze.getStart();
        final Node node = fastMaze.getMazeMap().get(new Point(start.x, start.y));

        assertThat(node, is(equalTo(start)));
    }


}