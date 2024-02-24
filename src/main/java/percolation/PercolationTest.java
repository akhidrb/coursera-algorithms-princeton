package src.main.java.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PercolationTest {

    @Test
    public void percolation() {
        int size = 5;
        Percolation perc = new Percolation(size);
        while (!perc.percolates() && perc.numberOfOpenSites() < size * size) {
            int row = StdRandom.uniformInt(1, size + 1);
            int col = StdRandom.uniformInt(1, size + 1);
            while (perc.isOpen(row, col)) {
                row = StdRandom.uniformInt(1, size + 1);
                col = StdRandom.uniformInt(1, size + 1);
            }
            perc.open(row, col);
        }
        System.out.println(
                "Percolation Threshold: " + (double) perc.numberOfOpenSites() / (size * size));
        Assertions.assertTrue(perc.percolates());
        // Assertions.assertTrue(iter < (size * size) * 0.60);
    }

    @Test
    public void p1() {
        int size = 5;
        Percolation perc = new Percolation(size);
        Assertions.assertTrue(!perc.isOpen(3, 3));
        perc.open(3, 3);
        Assertions.assertTrue(perc.isOpen(3, 3));
        boolean caught = false;
        try {
            perc.open(6, 3);
        }
        catch (IllegalArgumentException e) {
            caught = true;
        }
        Assertions.assertTrue(caught);
        perc.open(3, 4);
        Assertions.assertEquals(2, perc.numberOfOpenSites());
        //     1  2  3  4  5
        // 6  7  8  9  10
        // 11 12 13 14 15
        // 16 17 18 19 20
        // 21 22 23 24 25

    }

    @Test
    public void stats() {
        int N = 10;
        int T = 100000;
        PercolationStats ps = new PercolationStats(N, T);
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }

}
