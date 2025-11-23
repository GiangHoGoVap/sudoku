package com.sudoku.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a single cell in a Sudoku-like puzzle.
 * This class is independent from board size logic.
 */
public class Cell {

    private int value;
    private boolean isFixed;
    private Set<Integer> possibilities;

    private final int row;
    private final int col;

    /**
     * Constructor for a Cell.
     *
     * @param row The row index of this cell.
     * @param col The column index of this cell.
     * @param maxValue The maximum allowed value (e.g., 9 for 9x9, 16 for 16x16, etc.).
     */
    public Cell(int row, int col, int maxValue) {
        this.row = row;
        this.col = col;
        this.value = 0;
        this.isFixed = false;

        // Initialize possibilities: 1..maxValue
        this.possibilities = new HashSet<>();
        for (int i = 1; i <= maxValue; i++) {
            possibilities.add(i);
        }
    }

    /** Get current value (0 if empty) */
    public int getValue() {
        return value;
    }

    /** Set value; if non-zero, cell becomes fixed and clears other possibilities */
    public void setValue(int value) {
        this.value = value;
        this.isFixed = (value != 0);

        if (value != 0) {
            possibilities.clear();
            possibilities.add(value);
        }
    }

    /** Set a fixed clue value at initialization */
    public void setFixedValue(int value) {
        setValue(value);
        this.isFixed = true;
    }

    /** Whether this is a fixed (given) cell */
    public boolean isFixed() {
        return isFixed;
    }

    /** Check if cell is empty */
    public boolean isEmpty() {
        return value == 0;
    }

    /** Returns a copy of possibilities */
    public Set<Integer> getPossibilities() {
        return new HashSet<>(possibilities);
    }

    /** Replace the possibility set */
    public void setPossibilities(Set<Integer> possibilities) {
        this.possibilities = new HashSet<>(possibilities);
    }

    /** Remove a value from possibilities */
    public boolean removePossibility(int num) {
        return possibilities.remove(num);
    }

    /** Add a value to possibilities (only allowed if not fixed) */
    public void addPossibility(int num) {
        if (!isFixed) {
            possibilities.add(num);
        }
    }

    /** Get row index */
    public int getRow() {
        return row;
    }

    /** Get column index */
    public int getCol() {
        return col;
    }

    /** Nice printing: "." for empty, value otherwise */
    @Override
    public String toString() {
        return isEmpty() ? "." : String.valueOf(value);
    }

    /** Deep clone */
    @Override
    public Cell clone() {
        Cell cloned = new Cell(row, col, possibilities.size());
        cloned.value = this.value;
        cloned.isFixed = this.isFixed;
        cloned.possibilities = new HashSet<>(this.possibilities);
        return cloned;
    }
}
