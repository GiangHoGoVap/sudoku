package com.sudoku.dlx.model;

public class HeuristicModel {

    private static final double MRV_WEIGHT = 1.0;           // Primary factor
    private static final double DEGREE_WEIGHT = 0.15;       // Tiebreaker for MRV
    private static final double DEPTH_BONUS = 0.05;         // Encourage diversity
    private static final double AVG_DEPTH = 10.0;

    public double predict(double[] features) {
        if (features == null || features.length < 2) {
            throw new IllegalArgumentException("Features must have at least 2 elements: [size, depth]");
        }

        double size = features[0];
        double depth = features[1];
        double mrvScore = -size * MRV_WEIGHT;
        double degreeScore = (size > 0) ? -(DEGREE_WEIGHT / (size + 1.0)) : 0;
        double depthAdjustment = 0;

        if (depth < AVG_DEPTH) {
            depthAdjustment = DEPTH_BONUS;
        } else if (depth > AVG_DEPTH * 2) {
            depthAdjustment = -DEPTH_BONUS * 0.5;
        }

        double score = mrvScore + degreeScore + depthAdjustment;
        return score;
    }
}

