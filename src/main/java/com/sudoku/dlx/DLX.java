package com.sudoku.dlx;

import java.util.ArrayList;
import java.util.List;

import com.sudoku.dlx.selector.ColumnSelector;
import com.sudoku.dlx.selector.SmallestColumnSelector;

public class DLX {
    private final Column header;
    private List<Node> solution;
    private ColumnSelector selector;

    public DLX(int cols) {
        this(cols, new SmallestColumnSelector());
    }

    public DLX(int cols, ColumnSelector selector) {
        this.selector = selector;
        header = new Column();
        header.L = header.R = header;

        Column prev = header;

        for (int i = 0; i < cols; i++) {
            Column col = new Column();

            // link horizontally
            col.L = prev;
            col.R = prev.R;
            prev.R.L = col;
            prev.R = col;

            // empty vertical list
            col.U = col.D = col;

            prev = col;
        }
    }

    public void addRow(int rowID, int[] colIndices) {
        Node first = null;

        for (int idx : colIndices) {
            Column col = columnAt(idx);

            Node node = new Node();
            node.C = col;
            node.rowID = rowID;

            // insert vertically
            node.U = col.U;
            node.D = col;
            col.U.D = node;
            col.U = node;
            col.size++;

            // link horizontally
            if (first == null) {
                first = node;
                node.L = node.R = node;
            } else {
                node.L = first.L;
                node.R = first;
                first.L.R = node;
                first.L = node;
            }
        }
    }

    private Column columnAt(int idx) {
        Column c = (Column) header.R;
        for (int i = 0; i < idx; i++) {
            c = (Column) c.R;
        }
        return c;
    }

    public List<Integer> solve() {
        solution = new ArrayList<>();
        boolean ok = search();
        if (!ok) return null;

        List<Integer> res = new ArrayList<>();
        for (Node n : solution) res.add(n.rowID);
        return res;
    }

    /* -----------------------
       DLX core
     ----------------------- */

    private boolean search() {

        // finished: all columns covered
        if (header.R == header) {
            return true;
        }

        Column c = selector.choose(header);
        cover(c);

        for (Node r = c.D; r != c; r = r.D) {

            solution.add(r);

            for (Node j = r.R; j != r; j = j.R) {
                cover(j.C);
            }

            if (search()) return true;

            // backtrack
            solution.remove(solution.size() - 1);

            for (Node j = r.L; j != r; j = j.L) {
                uncover(j.C);
            }
        }

        uncover(c);
        return false;
    }

    private void cover(Column c) {
        c.R.L = c.L;
        c.L.R = c.R;

        for (Node i = c.D; i != c; i = i.D) {
            for (Node j = i.R; j != i; j = j.R) {
                j.D.U = j.U;
                j.U.D = j.D;
                j.C.size--;
            }
        }
    }

    private void uncover(Column c) {
        for (Node i = c.U; i != c; i = i.U) {
            for (Node j = i.L; j != i; j = j.L) {
                j.C.size++;
                j.D.U = j;
                j.U.D = j;
            }
        }

        c.R.L = c;
        c.L.R = c;
    }
}
