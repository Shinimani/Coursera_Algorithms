/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Trial {
    public static void main(String[] args) {
        WeightedQuickUnionUF w = new WeightedQuickUnionUF(7);
        w.union(0, 6);
        System.out.println(w.count());

    }
}
