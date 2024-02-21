package src.main.java;

public class QuickFindUF {

    int n;
    int[] relation;

    public QuickFindUF(int n) {
        this.n = n;
        this.relation = new int[n];
        for (int i = 0; i < n; i++) {
            this.relation[i] = i;
        }
    }

    public void union(int p, int q) {
        int val1 = this.relation[p];
        int val2 = this.relation[q];
        for (int i = 0; i < n; i++) {
            if (this.relation[i] == val1) {
                this.relation[i] = val2;
            }
        }
    }

    public boolean connected(int p, int q) {
        return this.relation[p] == this.relation[q];
    }

}
