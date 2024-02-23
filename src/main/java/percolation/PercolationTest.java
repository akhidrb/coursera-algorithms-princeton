package src.main.java.percolation;

import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class PercolationTest {

    @Test
    public void percolation() {
        int size = 20;
        Percolation perc = new Percolation(size);
        Random rand = new Random();
        while (!perc.percolates() && perc.numberOfOpenSites() < size * size) {
            int row = rand.nextInt(size) + 1;
            int col = rand.nextInt(size) + 1;
            while (perc.isOpen(row, col)) {
                row = rand.nextInt(size) + 1;
                col = rand.nextInt(size) + 1;
            }
            perc.open(row, col);
        }
        System.out.println(perc.numberOfOpenSites());
        Assertions.assertTrue(perc.percolates());
        // Assertions.assertTrue(iter < (size * size) * 0.60);
    }

    @Test
    public void stats() {
        int N = 400;
        int T = 200;
        PercolationStats ps = new PercolationStats(N, T);
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }

}
