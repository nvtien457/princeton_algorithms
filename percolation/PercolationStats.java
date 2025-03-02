// package percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;

    private double[] thresholds;
    private int gridSize;
    private int sampleSize;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("PercolationStats: n <= 0");
        }

        if (trials <= 0) {
            throw new IllegalArgumentException("PercolationStats: trials <= 0");
        }

        gridSize = n;
        sampleSize = trials;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);

            int[] indices = StdRandom.permutation(n*n);
            for (int j = 0; j <= n*n; j++) {
                if (test.percolates()) {
                    break;
                }

                int row = indices[j] / n + 1;
                int col = indices[j] % n + 1;
                test.open(row, col);
            }

            thresholds[i] = (double) test.numberOfOpenSites() / (gridSize * gridSize);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(sampleSize));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(sampleSize));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats tests = new PercolationStats(n, t);

        StdOut.printf("%s = %f\n", "mean", tests.mean());
        StdOut.printf("%s = %f\n", "stddev", tests.stddev());
        StdOut.printf("%s = [%f, %f]\n", "95% confidence interval", tests.confidenceLo(), tests.confidenceHi());
    }
}
