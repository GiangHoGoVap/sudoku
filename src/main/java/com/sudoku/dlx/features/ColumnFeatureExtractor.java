package com.sudoku.dlx.features;

import com.sudoku.dlx.Column;
import com.sudoku.dlx.Node;

public class ColumnFeatureExtractor {

    public static double[] extract(Column c) {

        int size = c.size;

        int depth = 0;
        for (Node n = c.D; n != c; n = n.D) depth++;

        return new double[]{size, depth};
    }
}

