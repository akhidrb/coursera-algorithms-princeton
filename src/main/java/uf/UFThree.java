package src.main.java.uf;

public class UFThree {

    int n;
    boolean[] relation;

    public UFThree(int n) {
        this.n = n;
        this.relation = new boolean[n];
        for (int i = 0; i < n; i++) {
            this.relation[i] = true;
        }
    }

    public void remove(int x) {
        this.relation[x] = false;
    }

    public int findSuccessor(int p) {
        for (int i = p + 1; i < n; i++) {
            if (relation[i]) {
                return i;
            }
        }
        return -1;
    }

}
