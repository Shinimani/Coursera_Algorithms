/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF w;
    private final WeightedQuickUnionUF ww;
    private final int n;
    private boolean[][] op;
    private int openCount;

    // creates n-by-n grid, with all sites initially blocked

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.n = n;
        w = new WeightedQuickUnionUF(n * n + 2);
        ww = new WeightedQuickUnionUF(n * n + 1);
        op = new boolean[n][n];
        openCount = 0;
        // for (int i = 0; i < n; i++) {
        //     w.union(n * n, i);
        //     w.union(n * n + 1, n * n - n + i);
        // }
    }

    private int helper(int row, int col) {
        return n * row + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) throw new IllegalArgumentException();
        row--;
        col--;
        if (op[row][col]) return;
        openCount++;
        op[row][col] = true;
        if (row == 0) w.union(helper(row, col), (n * n));
        if (row == 0) ww.union(helper(row, col), (n * n));

        if (row == n - 1) w.union(helper(row, col), (n * n) + 1);

        if (col < n - 1 && op[row][col + 1]) w.union(helper(row, col), n * row + col + 1);
        if (row > 0 && op[row - 1][col]) w.union(helper(row, col), n * (row - 1) + col);
        if (row < n - 1 && op[row + 1][col]) w.union(helper(row, col), n * (row + 1) + col);
        if (col > 0 && op[row][col - 1]) w.union(helper(row, col), n * row + col - 1);

        if (col < n - 1 && op[row][col + 1]) ww.union(helper(row, col), n * row + col + 1);
        if (row > 0 && op[row - 1][col]) ww.union(helper(row, col), n * (row - 1) + col);
        if (row < n - 1 && op[row + 1][col]) ww.union(helper(row, col), n * (row + 1) + col);
        if (col > 0 && op[row][col - 1]) ww.union(helper(row, col), n * row + col - 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) throw new IllegalArgumentException();
        if (openCount == 0) return false;
        row--;
        col--;
        return op[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) throw new IllegalArgumentException();
        if (openCount == 0) return false;
        row--;
        col--;
        // if (op[row][col] && row == 0) return true;
        return (op[row][col] && (ww.find(helper(row, col)) == ww.find(n * n)));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        return w.find(n * n) == w.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        // Percolation p = new Percolation(5);
        // // // p.open(1, 11);
        // // // p.open(0, 1);
        // p.open(5, 3);
        // p.open(1, 1);
        // // System.out.println(p.isFull(1, 1));
        // p.open(1, 4);
        // p.open(2, 4);
        // p.open(2, 2);
        // p.open(2, 3);
        // p.open(3, 3);
        // p.open(4, 3);
        // p.open(5, 5);
        // // p.open(2, 1);
        // // p.open(3, 1);
        // // p.open(4, 1);
        // // p.open(5, 1);
        // // System.out.println(p.isFull(5, 1));
        // // p.open(5, 2);
        // System.out.println(p.percolates());
        // System.out.println(p.isFull(5, 5));


    }

}
