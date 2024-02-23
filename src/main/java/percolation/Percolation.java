package src.main.java.percolation;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked

    int n;
    int[] relation;
    boolean[] site;

    public Percolation(int n) {
        this.n = n;
        this.site = new boolean[(n * n) + 2];
        this.relation = new int[(n * n) + 2];
        for (int i = 0; i < (n * n) + 2; i++) {
            this.relation[i] = i;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int ind = getIndex(row, col);
        this.site[ind] = true;
        if (ind <= n) {
            site[0] = true;
            union(ind, 0);
        }
        else if (ind > n * (n - 1)) {
            site[n * n] = true;
            union(ind, n * n);
        }
        for (int i = 1; i <= 4; i++) {
            int dirIndex = getIndexFromDir(i, row, col);
            if (dirIndex < 0 || !site[dirIndex] || connected(ind, dirIndex)) {
                continue;
            }
            union(ind, dirIndex);
        }
    }

    private boolean connected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    private int getIndexFromDir(int dir, int row, int col) {
        row--;
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
        if (row < 0 || row >= n || col < 0 || col > n) {
            throw new IllegalArgumentException();
        }
        int ind = (row * n) + col;
        return ind;
    }

    private void union(int p, int q) {
        q = getRoot(q);
        p = getRoot(p);
        if (p < q) {
            relation[q] = p;
        }
        else {
            relation[p] = q;
        }
    }

    private int getRoot(int p) {
        while (relation[p] != p) {
            relation[p] = relation[relation[p]];
            p = relation[p];
        }
        return p;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int ind = getIndex(row, col);
        return site[ind];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int ind = getIndex(row, col);
        int root = getRoot(ind);
        return root == 0;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int size = 0;
        for (int i = 0; i < n * n; i++) {
            if (site[i]) size++;
        }
        return size;
    }

    // does the system percolate?
    public boolean percolates() {
        return connected(0, n * n);
    }

    private void printRelation() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int ind = getIndex(i, j);
                System.out.print(relation[ind] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
