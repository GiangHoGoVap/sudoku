package com.sudoku.solver;

import com.sudoku.dlx.DLX;
import com.sudoku.dlx.model.HeuristicModel;
import com.sudoku.dlx.selector.HeuristicColumnSelector;
import com.sudoku.model.Board;

import java.util.ArrayList;
import java.util.List;

public class DLXSudokuSolver {

    public boolean solve(Board board) {
        int N = board.getSize();

        int cols = 4 * N * N;
        DLX dlx = new DLX(cols, new HeuristicColumnSelector(new HeuristicModel()));

        // Maps rowID → (r, c, v)
        List<int[]> rowMap = new ArrayList<>();

        int rowID = 0;

        // Build exact cover matrix rows
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                int fixed = board.getCell(r, c).getValue();
                int box = board.getBoxIndex(r, c);

                for (int v = 1; v <= N; v++) {

                    // Skip incompatible values for preset cells
                    if (fixed != 0 && fixed != v) continue;

                    int cellConstraint = r * N + c;
                    int rowConstraint  = N * N + r * N + (v - 1);
                    int colConstraint  = 2 * N * N + c * N + (v - 1);
                    int boxConstraint  = 3 * N * N + box * N + (v - 1);

                    dlx.addRow(rowID, new int[]{
                        cellConstraint,
                        rowConstraint,
                        colConstraint,
                        boxConstraint
                    });

                    rowMap.add(new int[]{r, c, v});
                    rowID++;
                }
            }
        }

        List<Integer> solution = dlx.solve();
        if (solution == null) return false;

        // Place chosen (r,c,v)
        for (int id : solution) {
            int[] triple = rowMap.get(id);
            board.setValue(triple[0], triple[1], triple[2]);
        }

        return true;
    }
}
