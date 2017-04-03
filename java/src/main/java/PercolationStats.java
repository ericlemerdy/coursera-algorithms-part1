import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] percolationThreshold;

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
            while (!percolation.percolates() || percolation.numberOfOpenSites() == n) {
                int maxRandom = n * n - percolation.numberOfOpenSites() - 1;
                int nextInt = StdRandom.uniform(maxRandom) + 1;
                if (nextInt < 1 || nextInt >= maxRandom) {
                    throw new RuntimeException();
                }
                int randomRow = ((nextInt - 1) / n) + 1;
                int randomCol = nextInt % n == 0 ? n : nextInt % n;
                if ((nextInt == 1 || nextInt == 2 || nextInt == 3) && randomRow != 1) {
                    throw new RuntimeException();
                }
                if ((nextInt == 4 || nextInt == 5 || nextInt == 6) && randomRow != 2) {
                    throw new RuntimeException();
                }
                if ((nextInt == 7 || nextInt == 8 || nextInt == 9) && randomRow != 3) {
                    throw new RuntimeException();
                }
                if ((nextInt == 1 || nextInt == 4 || nextInt == 7) && randomCol != 1) {
                    throw new RuntimeException();
                }
                if ((nextInt == 2 || nextInt == 5 || nextInt == 8) && randomCol != 2) {
                    throw new RuntimeException();
                }
                if ((nextInt == 3 || nextInt == 6 || nextInt == 9) && randomCol != 3) {
                    throw new RuntimeException();
                }
                int row = 1, col = 1;
                while (!(row == randomRow && col == randomCol)) {
                    if (col == n) {
                        row++;
                        col = 0;
                    }
                    col++;
                    if (percolation.isOpen(row, col)) {
                        nextInt++;
                    }
                }
                percolation.open(randomRow, randomCol);
            }
            percolationThreshold[trial] = percolation.numberOfOpenSites() / n * n;
        }
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(percolationThreshold);
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(percolationThreshold);
    }

    /**
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return 0d;
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return 0d;
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
        System.out.printf("mean                     = %f\n", percolationStats.mean());
        System.out.printf("stddev                   = %f\n", percolationStats.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n",
                percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}