package test.java.uf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.java.uf.QuickUnionUF;
import src.main.java.uf.UF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class UFTest {

    static final String quickFind = "quickFind";
    static final String quickUnion = "quickUnion";

    @Test
    public void compareQuickFindUF() {
        compareUF(quickFind);
    }

    @Test
    public void compareQuickUnionUF() {
        compareUF(quickUnion);
    }

    @Test
    public void benchmarkQuickUnionUF() {
        Random rand = new Random();
        int N = 100000000;
        UF uf = getUFType(quickUnion, N);
        for (int i = 0; i < 100; i++) {
            int p = rand.nextInt(100000000);
            int q = rand.nextInt(100000000);
            uf.union(p, q);
        }
        for (int i = 0; i < 100; i++) {
            int p = rand.nextInt(100000000);
            int q = rand.nextInt(100000000);
            System.out.print(uf.connected(p, q));
        }
    }

    private void compareUF(String ufType) {
        try {
            File myObj = new File("assets/union-find.txt");
            Scanner myReader = new Scanner(myObj);
            int N = myReader.nextInt();
            UF uf = getUFType(ufType, N);
            int inp = myReader.nextInt();
            for (int i = 0; i < inp; i++) {
                int p = myReader.nextInt();
                int q = myReader.nextInt();
                uf.union(p, q);
            }
            int outp = myReader.nextInt();
            for (int i = 0; i < outp; i++) {
                int p = myReader.nextInt();
                int q = myReader.nextInt();
                boolean isConn = myReader.nextBoolean();
                Assertions.assertEquals(isConn, uf.connected(p, q), p + ", " + q);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private UF getUFType(String ufType, int N) {
        switch (ufType) {
            case quickFind:
                return new QuickUnionUF(N);
            default:
                return new QuickUnionUF(N);
        }
    }


}
