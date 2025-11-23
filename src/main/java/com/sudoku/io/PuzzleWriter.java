package com.sudoku.io;

import com.sudoku.model.Board;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes Sudoku solutions to files.
 */
public class PuzzleWriter {
    private String filePath;

    public PuzzleWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes a single solved board to a file.
     */
    public void writeSolution(Board board) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(board.toString());
        }
    }

    /**
     * Writes multiple solved boards to a file.
     */
    public void writeSolutions(List<Board> boards) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < boards.size(); i++) {
                writer.write("Puzzle " + (i + 1) + ":\n");
                writer.write(boards.get(i).toString());
                writer.write("\n");
            }
        }
    }

    /**
     * Writes solutions as puzzle strings (81 characters per line).
     */
    public void writePuzzleStrings(List<String> solutions) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String solution : solutions) {
                writer.write(solution);
                writer.write("\n");
            }
        }
    }
}
