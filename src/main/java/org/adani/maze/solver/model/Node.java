package org.adani.maze.solver.model;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public Node parent;
    public char value;
    public int x;
    public int y;

    /**
     * The following properties used select the next best node expand. Informed search
     * expands with respect to an estimate of the cost (total weight) still to go to the goal node.
     * Specifically, A* selects the path that minimizes the function:
     * <p>
     * <pre>
     *      f(n)=g(n)+h(n); where n is the last node on the path
     *      g(n) is the cost of the path from the start node to n
     *      h(n) is a heuristic that estimates the cost of the cheapest path from n to the goal.
     *  </pre>
     * <p>
     * It is common to use a distance measure such as Manhattan or Euclidean to calculate how close we are to a goal.
     */

    public int f, g, h;
    public List<Node> neighbour;

    public Node(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
        parent = null;
        neighbour = new ArrayList<>();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public boolean equals(final Object obj) {

        if ((obj == null) || !(obj instanceof Node))
            return false;

        final Node other = (Node) obj;

        /**
         * The node equality is determined by the notion that they are at the same location and hold the same values.
         * Properties that are associated with Heuristics
         * related attributes are not considered during equality testing.
         */


        return (this.x == other.x && this.y == other.y && this.value == other.value);

    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(x).append(y).append(value).hashCode();
    }

}
