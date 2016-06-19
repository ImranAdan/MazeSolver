package org.adani.maze.solver.model;

import java.util.function.BiFunction;

/**
 * Enum type for Heuristics functions.
 * The enum must accept a function that takes
 * into account the current node and the goal node and
 * computes a heuristic indicating how costly the current
 * node is.
 */
public enum HeuristicFunction {

    /**
     * <pre> Manhattan Distance <pre/>
     * Calculates the manhattan distance between two nodes.
     */
    MANHATTAN_DISTANCE((Node current, Node goal) -> (Math.abs(current.x - goal.x) + Math.abs(current.y - goal.y)));


    BiFunction<Node, Node, Integer> heuristicsFunc;

    HeuristicFunction(BiFunction<Node, Node, Integer> heuristicsFunc) {
        this.heuristicsFunc = heuristicsFunc;
    }


    /**
     * Apply the heuristics measure.
     *
     * @param current Current node; usually the current node in the path.
     * @param goal    Goal Node
     * @return An integer indicating how close we are from the goal given the current node;
     * the integer is calculated according to the selected Heuristics.
     */
    public int apply(Node current, Node goal) {
        return heuristicsFunc.apply(current, goal);
    }
}

