package com.sudoku.dlx.selector;

import com.sudoku.dlx.Column;

public class SmallestColumnSelector implements ColumnSelector {

    @Override
    public Column choose(Column header) {
        Column best = null;
        int min = Integer.MAX_VALUE;

        for (Column c = (Column) header.R; c != header; c = (Column) c.R) {
            if (c.size < min) {
                min = c.size;
                best = c;
            }
        }
        return best;
    }
}

