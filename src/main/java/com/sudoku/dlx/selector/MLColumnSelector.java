package com.sudoku.dlx.selector;

import com.sudoku.dlx.Column;
import com.sudoku.dlx.features.ColumnFeatureExtractor;
import com.sudoku.dlx.model.HeuristicModel;

public class MLColumnSelector implements ColumnSelector {

    private final HeuristicModel model;

    public MLColumnSelector(HeuristicModel model) {
        this.model = model;
    }

    @Override
    public Column choose(Column header) {

        Column best = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        // iterate over all columns
        for (Column c = (Column) header.R; c != header; c = (Column) c.R) {

            double[] features = ColumnFeatureExtractor.extract(c);

            double score = model.predict(features);

            if (score > bestScore) {
                bestScore = score;
                best = c;
            }
        }

        return best;
    }
}

