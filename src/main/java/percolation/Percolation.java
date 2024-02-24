package src.main.java.percolation;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked

    private int n;
    private int openSites;
    private int[] relation;
    private boolean[] site;
    private int[] size;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.openSites = 0;
        this.site = new boolean[(n * n) + 2];
        this.relation = new int[(n * n) + 2];
        this.size = new int[(n * n) + 2];
        for (int i = 0; i < (n * n) + 2; i++) {
            this.relation[i] = i;
            this.size[i] = 1;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int ind = getIndex(row, col);
        if (this.site[ind]) {
            return;
        }
        this.site[ind] = true;
        openSites++;
        if (ind <= n) {
            site[0] = true;
            union(ind, 0);
        }
        else if (ind > n * (n - 1)) {
            site[n * n + 1] = true;
            union(ind, n * n + 1);
        }
        for (int i = 1; i <= 4; i++) {
            int dirIndex = getIndexFromDir(i, row, col);
            if (dirIndex == -1 || !site[dirIndex] || connected(ind, dirIndex)) {
                continue;
            }
            union(ind, dirIndex);
        }
    }

    private boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int getIndexFromDir(int dir, int row, int col) {
        switch (dir) {
            case 1:
                if (row == 1) {
                    return -1;
                }
                row--;
                break;
            case 2:
                if (col == n) {
                    return -1;
                }
                col++;
                break;
            case 3:
                if (row == n) {
                    return -1;
                }
                row++;
                break;
            case 4:
                if (col == 1) {
                    return -1;
                }
                col--;
                break;
        }
        return getIndex(row, col);
    }

    private int getIndex(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException();
        }
        row--;
        int ind = (row * n) + col;
        return ind;
    }

    private void union(int p, int q) {
        q = find(q);
        p = find(p);
        if (p == q) return;

        if (size[q] < size[p]) {
            relation[q] = p;
            size[p] += size[q];
        }
        else {
            relation[p] = q;
            size[q] += size[p];
        }
    }

    private int find(int p) {
        while (relation[p] != p) {
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
        int root = find(ind);
        return root == 0;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return connected(0, n * n + 1);
    }

    public static void main(String[] args) {

    }

}
