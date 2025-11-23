package com.sudoku.dlx;

public class Node {
    public Node L, R, U, D;
    Column C;
    int rowID;   // identifies Sudoku placement (r,c,v)
}