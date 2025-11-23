package com.sudoku;

import com.sudoku.io.PuzzleReader;
import com.sudoku.model.Board;
import com.sudoku.solver.DLXSudokuSolver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SudokuSolverApp {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Usage:");
            System.err.println("mvn exec:java -Dexec.mainClass=\"com.sudoku.SudokuSolverApp\" "
                    + "-Dexec.args=\"<input_file> <output_file>\"");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            System.out.println("Sudoku Solver");
            System.out.println("=====================\n");

            PuzzleReader reader = new PuzzleReader(inputFile);
            DLXSudokuSolver solver = new DLXSudokuSolver();

            // Ensure output directory exists
            File outFile = new File(outputFile);
            File parentDir = outFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            // IMPORTANT: Open the reader once before the loop
            reader.open();

            try (FileWriter writer = new FileWriter(outFile)) {

                int index = 1;
                Board puzzle;

                // Now readOne() will stream through the file line by line
                while ((puzzle = reader.readOne()) != null) {

                    writer.write("=== Puzzle " + index + " ===\n");
                    writer.write("Board Size: " + puzzle.getSize() + "x" + puzzle.getSize() + "\n\n");

                    // Print original
                    writer.write("Original Puzzle:\n");
                    writer.write(puzzle.toString() + "\n");

                    // Clone & solve
                    Board copy = puzzle.clone();
                    boolean solved = solver.solve(copy);

                    if (solved) {
                        writer.write("Solved Puzzle:\n");
                        writer.write(copy.toString() + "\n");
                        System.out.println("Puzzle " + index + " solved!");
                    } else {
                        writer.write("Solved Puzzle:\nNO SOLUTION FOUND\n\n");
                        System.out.println("Puzzle " + index + " has no solution!");
                    }

                    writer.write("\n----------------------------------------\n\n");

                    index++;
                    
                    // Optional: Print progress every 1000 puzzles
                    if (index % 1000 == 0) {
                        System.out.println("Processed " + index + " puzzles...");
                    }
                }

                System.out.println("\nFinished processing " + (index - 1) + " puzzles.");
                System.out.println("Results saved to: " + outFile.getAbsolutePath());
                
            } finally {
                // Close the reader
                reader.close();
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}