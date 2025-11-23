package com.sudoku.model;

public class Board {

    private final int size;              // e.g., 9, 16, 25
    private final int maxValue;          // equals size (used by Cell)
    private final int boxRows;           // e.g., 3 for 9x9
    private final int boxCols;           // e.g., 3 for 9x9

    private final Cell[][] grid;

    /**
     * Creates a Sudoku board.
     *
     * @param size     The board dimension (e.g., 9 for 9x9, 16 for 16x16).
     * @param boxRows  Number of rows per box/subgrid.
     * @param boxCols  Number of columns per box/subgrid.
     *
     * Example for 9x9: size=9, boxRows=3, boxCols=3
     * Example for 6x6: size=6, boxRows=2, boxCols=3
     */
    public Board(int size, int boxRows, int boxCols) {
        this.size = size;
        this.maxValue = size;
        this.boxRows = boxRows;
        this.boxCols = boxCols;

        this.grid = new Cell[size][size];

        // initialize cells
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = new Cell(r, c, maxValue);
            }
        }
    }

    /** Get size (N) of board */
    public int getSize() {
        return size;
    }

    /** Return number of rows in a subgrid */
    public int getBoxRows() {
        return boxRows;
    }

    /** Return number of columns in a subgrid */
    public int getBoxCols() {
        return boxCols;
    }

    /** Access a cell */
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    /** Set a fixed value (a clue) */
    public void setFixedValue(int row, int col, int value) {
        grid[row][col].setFixedValue(value);
    }

    /** Set a non-fixed (solver-added) value */
    public void setValue(int row, int col, int value) {
        grid[row][col].setValue(value);
    }

    /** Compute box ID based on variable box size */
    public int getBoxIndex(int row, int col) {
        int boxRow = row / boxRows;
        int boxCol = col / boxCols;
        return boxRow * (size / boxCols) + boxCol;
    }

    /** Clone board deeply */
    public Board clone() {
        Board cloned = new Board(size, boxRows, boxCols);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cloned.grid[r][c] = this.grid[r][c].clone();
            }
        }

        return cloned;
    }

    /** Printable form */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                sb.append(grid[r][c].toString()).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
