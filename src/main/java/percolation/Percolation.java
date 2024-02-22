package src.main.java.percolation;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked

    int n;
    int[] relation;

    public Percolation(int n) {
        this.n = n;
        this.relation = new int[n];
        for (int i = 0; i < n * n; i++) {
            this.relation[i] = i;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int ind = getIndex(row, col);
        if (ind < n) {
            relation[ind] = -1;
        }
        else if (ind >= (n * (n - 1))) {
            relation[ind] = -2;
        }

        for (int i = 1; i <= 4; i++) {
            int dir = getIndexFromDir(1, row - 1, col - 1);
            if (dir < 0) {
                continue;
            }
            union(ind, dir);
        }
    }

    private int getIndexFromDir(int dir, int row, int col) {
        switch (dir) {
            case 1:
                if (row - 1 < 0) {
                    return -1;
                }
                return ((row - 1) * n) + col;
            case 2:
                if (col + 1 >= n) {
                    return -1;
                }
                return (row * n) + (col + 1);
            case 3:
                if (row + 1 >= n) {
                    return -1;
                }
                return ((row + 1) * n) + col;
            case 4:
                if (col - 1 < 0) {
                    return -1;
                }
                return (row * n) + (col - 1);
        }
        return getIndex(row + 1, col + 1);
    }

    private int getIndex(int row, int col) {
        row--;
        col--;
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException();
        }
        int ind = ((row - 1) * n) + (col - 1);
        return ind;
    }

    private void union(int p, int q) {
        int rootQ = getRoot(q);
        int rootP = getRoot(p);
        if (rootP > rootQ) {
            relation[q] = rootP;
            return;
        }
        relation[p] = rootQ;
    }

    private int getRoot(int p) {
        while (relation[p] >= 0) {
            p = relation[p];
        }
        return p;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int ind = getIndex(row, col);
        return relation[ind] != ind;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int ind = getIndex(row, col);
        int root = getRoot(ind);
        if (root == -1) {
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int size = 0;
        for (int i = 0; i < n * n; i++) {
            if (relation[i] != i) size++;
        }
        return size;
    }

    // does the system percolate?
    public boolean percolates() {
        int row = n;
        int col = 1;
        for (int i = 0; i < n; i++) {
            if (isFull(row, col + i)) {
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
