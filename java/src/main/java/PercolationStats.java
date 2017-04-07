import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double mean;

    private final double confidence;

    private double[] percolationThreshold;

    private double stddev;

    /**
     * perform trials independent experiments on an n-by-n grid
     *
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        if (trials <= 0) {
            throw new IllegalArgumentException();
        }
        percolationThreshold = new double[trials];
        for (int trial = 0; trial < trials; trial++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int maxRandom = n * n - percolation.numberOfOpenSites() - 1;
                int nextInt = StdRandom.uniform(maxRandom);
                int row = 1, col = 0;
                for (int i = 0; i <= nextInt; i++) {
                    do {
                        col++;
                        if (col > n) {
                            col = 1;
                            row++;
                        }
                    } while (percolation.isOpen(row, col));
                }
                percolation.open(row, col);
            }
            percolationThreshold[trial] = (double) percolation.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(percolationThreshold);
        stddev = StdStats.stddev(percolationThreshold);
        confidence = ((1.96f * stddev()) / Math.sqrt(trials));
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }

    /**
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean - confidence;
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean + confidence;
    }

    /**
     * test client (described below)
     *
     * @param args
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.printf("mean                    = %f\n", percolationStats.mean());
        System.out.printf("stddev                  = %f\n", percolationStats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n",
                percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}