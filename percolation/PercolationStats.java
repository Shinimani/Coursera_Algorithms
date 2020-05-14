/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double NUM = 1.96;
    private final double[] values;
    private final int trials;
    private final double mn, sd, l, r;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        values = new double[trials];
        this.trials = trials;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int r, c;
            while (!p.percolates()) {
                r = StdRandom.uniform(n) + 1;
                c = StdRandom.uniform(n) + 1;
                p.open(r, c);
            }
            values[i] = (double) p.numberOfOpenSites() / (double) (n * n);
        }
        mn = StdStats.mean(values);
        sd = StdStats.stddev(values);
        l = mn - ((NUM * sd) / Math.sqrt(trials));
        r = mn + ((NUM * sd) / Math.sqrt(trials));

    }

    // sample mean of percolation threshold
    public double mean() {
        return mn;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return sd;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return l;
        // return StdStats.c
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return r;
    }

    // test client (see below)
    public static void main(String[] args) {
        String s = " ";
        // int n = 0, t = 0;
        while (!StdIn.isEmpty()) {
            s = StdIn.readLine();
            // n = Integer.parseInt(StdIn.rea)
        }
        String[] ss = s.split(" ");
        int n = Integer.parseInt(ss[0]);
        int t = Integer.parseInt(ss[1]);

        // System.out.println("hello");
        // System.out.println(n + " " + T);

        PercolationStats percolationStats = new PercolationStats(n, t);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                  = " + percolationStats.stddev());
        StdOut.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                               + percolationStats.confidenceHi() + "]");

    }

}
