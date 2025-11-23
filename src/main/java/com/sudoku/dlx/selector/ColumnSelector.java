package com.sudoku.dlx.selector;

import com.sudoku.dlx.Column;

public interface ColumnSelector {
    Column choose(Column header);
}
