package org.adani.maze.solver.model;

import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Node {

    public Node parent;

    public char value;
    public int x;
    public int y;
    // TODO: rename these variable to describe their purpose.
    public int f;
    public int g;
    public int h;

    public List<Node> neighbour;

    public Node(int x, int y, char value) {
        this.x = x;
        this.y = y;
        this.value = value;
        parent = null;
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
        return new HashCodeBuilder().append(x)
                .append(y)
                .append(value)
                .hashCode();
    }

}
