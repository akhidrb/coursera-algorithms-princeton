package test.java.uf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.main.java.uf.QuickFindUF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UFTest {

    @Test
    public void compare() {
        try {
            File myObj = new File("assets/union-find.txt");
            Scanner myReader = new Scanner(myObj);
            int N = myReader.nextInt();
            QuickFindUF uf = new QuickFindUF(N);
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
                Assertions.assertEquals(isConn, uf.connected(p, q), p + " " + q);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
