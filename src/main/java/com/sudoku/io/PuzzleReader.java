package com.sudoku.io;

import com.sudoku.model.Board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Sudoku puzzle reader with automatic detection of board size and square subgrid.
 * One puzzle per line.
 */
public class PuzzleReader implements AutoCloseable {

    private final String filePath;
    private BufferedReader reader;
    private boolean isOpen = false;

    /**
     * Constructor for automatic detection.
     */
    public PuzzleReader(String filePath) {
        this.filePath = filePath;
    }

    /** 
     * Open the reader for streaming puzzles 
     * Must be called before readOne()
     */
    public void open() throws IOException {
        if (isOpen) {
            throw new IllegalStateException("Reader is already open");
        }
        reader = new BufferedReader(new FileReader(filePath));
        isOpen = true;
    }

    /** Read all puzzles */
    public List<Board> readAll() throws IOException {
        return read(Integer.MAX_VALUE); // read all lines
    }

    /** Read first n puzzles */
    public List<Board> read(int n) throws IOException {
        List<Board> boards = new ArrayList<>();

        try (BufferedReader tempReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int count = 0;

            while ((line = tempReader.readLine()) != null && count < n) {
                line = line.trim();
                if (line.isEmpty()) continue;

                Board board = parsePuzzleLine(line);
                if (board != null) {
                    boards.add(board);
                    count++;
                }
            }
        }

        return boards;
    }

    /**
     * Read one puzzle from the stream.
     * Returns null when no more puzzles.
     * Must call open() first!
     */
    public Board readOne() throws IOException {
        if (!isOpen) {
            throw new IllegalStateException("Reader is not open. Call open() first.");
        }

        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            Board board = parsePuzzleLine(line);
            if (board != null) {
                return board;
            }
        }

        return null; // End of file
    }

    /** Close the reader */
    @Override
    public void close() throws IOException {
        if (isOpen && reader != null) {
            reader.close();
            isOpen = false;
        }
    }

    /** Parse a single line into a Board */
    private Board parsePuzzleLine(String line) {
        int len = line.length();

        // Auto-detect board size
        int detectedSize = autoDetectSize(len);
        if (detectedSize == -1) {
            System.err.println("Invalid puzzle length " + len);
            return null;
        }

        // Auto-detect square subgrid
        int boxSize = (int) Math.sqrt(detectedSize);
        if (boxSize * boxSize != detectedSize) {
            throw new IllegalStateException(
                "Cannot detect square subgrid for size " + detectedSize);
        }

        Board board = new Board(detectedSize, boxSize, boxSize);

        for (int i = 0; i < len; i++) {
            char ch = line.charAt(i);
            int row = i / detectedSize;
            int col = i % detectedSize;

            int value = charToValue(ch);
            if (value != 0) {
                board.setFixedValue(row, col, value);
            }
        }

        return board;
    }

    /** Auto-detect N when puzzle is N*N */
    private int autoDetectSize(int length) {
        int root = (int) Math.sqrt(length);
        return (root * root == length) ? root : -1;
    }

    /** Convert characters to integers */
    private int charToValue(char ch) {
        if (ch == '.' || ch == '0') return 0;
        if (ch >= '1' && ch <= '9') return ch - '0';
        if (ch >= 'A' && ch <= 'Z') return 10 + (ch - 'A');
        if (ch >= 'a' && ch <= 'z') return 10 + (ch - 'a');
        throw new IllegalArgumentException("Invalid puzzle character: '" + ch + "'");
    }
}