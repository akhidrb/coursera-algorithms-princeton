package src.main.java.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int len;
    private int top = 0;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private int openSites = 0;

    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException();
        len = N;
        grid = new boolean[N][N];
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int i, int j) {
        if (i < 1 || i > len || j < 1 || j > len) throw new IllegalArgumentException();

        if (grid[i - 1][j - 1]) return;

        grid[i - 1][j - 1] = true;

        if (i == 1) {
            uf.union(getSiteIndex(i, j), top);
        }
        if (i == len) {
            uf.union(getSiteIndex(i, j), bottom);
        }

        if (i > 1 && isOpen(i - 1, j)) {
            uf.union(getSiteIndex(i, j), getSiteIndex(i - 1, j));
        }
        if (i < len && isOpen(i + 1, j)) {
            uf.union(getSiteIndex(i, j), getSiteIndex(i + 1, j));
        }
        if (j > 1 && isOpen(i, j - 1)) {
            uf.union(getSiteIndex(i, j), getSiteIndex(i, j - 1));
        }
        if (j < len && isOpen(i, j + 1)) {
            uf.union(getSiteIndex(i, j), getSiteIndex(i, j + 1));
        }
        openSites++;
    }

    public boolean isOpen(int i, int j) {
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        if (i <= 0 || i > len || j <= 0 || j > len) throw new IndexOutOfBoundsException();
        return uf.connected(getSiteIndex(i, j), top);
    }

    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    private int getSiteIndex(int i, int j) {
        return len * (i - 1) + j;
    }

    public static void main(String[] args) {

    }
}
