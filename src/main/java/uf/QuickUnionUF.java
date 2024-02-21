package src.main.java.uf;

public class QuickUnionUF implements UF {
    int n;
    int[] relation;
    int[] size;

    public QuickUnionUF(int n) {
        this.n = n;
        this.relation = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            this.relation[i] = i;
            this.size[i] = 1;
        }
    }

    public void union(int p, int q) {
        q = getRoot(q);
        p = getRoot(p);
        if (size[q] < size[p]) {
            relation[q] = p;
            size[p] += size[q];
        }
        else {
            relation[p] = q;
            size[q] += size[p];
        }
    }

    public boolean connected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    private int getRoot(int p) {
        while (relation[p] != p) {
            p = relation[p];
        }
        return p;
    }

}
