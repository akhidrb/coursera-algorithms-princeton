package src.main.java.uf;

public class QuickUnionUF implements UF {
    int n;
    int[] relation;

    public QuickUnionUF(int n) {
        this.n = n;
        this.relation = new int[n];
        for (int i = 0; i < n; i++) {
            this.relation[i] = i;
        }
    }

    public void union(int p, int q) {
        this.relation[getRoot(p)] = getRoot(q);
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
