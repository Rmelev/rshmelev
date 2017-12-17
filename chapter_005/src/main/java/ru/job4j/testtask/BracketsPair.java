package ru.job4j.testtask;

import java.util.ArrayList;

/**
 * class of bracket pair.
 */
public class BracketsPair implements Comparable<BracketsPair> {
    /**
     * type of bracket pair.
     */
    private String pairType;
    /**
     * position of open and close brackets.
     */
    private ArrayList<Integer> positions = new ArrayList<>(2);

    /**
     * Constructor.
     * @param pairType - pair type.
     * @param a - open bracket position.
     * @param b - close bracket position.
     */
    public BracketsPair(String pairType, int a, int b) {
        this.pairType = pairType;
        this.positions.add(a);
        this.positions.add(b);
    }

    /**
     * @return string representation of pair.
     */
    @Override
    public String toString() {
        return pairType + " " + positions;
    }

    /**
     * Compare pairs in order of appearance.
     * @param o - pair foe  compare.
     * @return - a negative integer, zero, or a positive integer
     * as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(BracketsPair o) {
        return this.positions.get(0).compareTo(o.positions.get(0));
    }
}
