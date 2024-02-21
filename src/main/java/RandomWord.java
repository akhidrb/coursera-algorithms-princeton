package src.main.java;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double count = 0;
        String res = "";
        while (!StdIn.isEmpty()) {
            count++;
            String val = StdIn.readString();
            if (StdRandom.bernoulli(1 / count)) {
                res = val;
            }
        }
        StdOut.println(res);
    }
}
