package src.main.java.uf;

public class QuickFindUF implements UF {

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

    public int find(int p) {
        int largest = 0;
        for (int i = 0; i < n; i++) {
            if (relation[i] == relation[p] && i > largest) {
                largest = i;
            }
        }
        return largest;
    }

}
