/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

package src.main.java.uf;

public interface UF {
    public void union(int p, int q);

    public boolean connected(int p, int q);
}
