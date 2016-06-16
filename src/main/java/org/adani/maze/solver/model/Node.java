package org.adani.maze.solver.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class Node implements Comparable<Node> {

    public Node parent;

    public char value;

    public int x;
    public int y;

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


    //TODO:IMPLEMENT HASH CODE AND EQUALS AND COMPARABLE!!!

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}
