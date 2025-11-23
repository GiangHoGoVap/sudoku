package com.sudoku.dlx;

public class Column extends Node {
    public int size;

    Column() {
        this.C = this;  // column header points to itself
    }
}
