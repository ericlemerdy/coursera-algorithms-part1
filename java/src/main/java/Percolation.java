import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;
import java.util.List;

public class Percolation {
    private static final int VIRTUAL_TOP_SITE = 0;
    private final int virtualBottomSite;
    private final WeightedQuickUnionUF unionFind;
    private final int n;
    private final boolean[] openedSites;
    private int numberOfOpenSites = 0;

    /**
     * Create n-by-n grid, with all sites blocked
     *
     * @param n
     */
    public Percolation(int n) {
        this(new WeightedQuickUnionUF(n * n + 2), n);
    }

    private Percolation(WeightedQuickUnionUF unionFind, int n) {
        this.n = n;
        this.unionFind = unionFind;
        this.virtualBottomSite = n * n + 1;
        for (int topSiteIndex = 1; topSiteIndex < n + 1; topSiteIndex++) {
            unionFind.union(VIRTUAL_TOP_SITE, topSiteIndex);
        }
        for (int bottomSiteIndex = n * n; bottomSiteIndex > n * n - n; bottomSiteIndex--) {
            unionFind.union(virtualBottomSite, bottomSiteIndex);
        }
        openedSites = new boolean[n * n + 1];
    }

    /**
     * Open site (row, col) if it is not open already
     *
     * @param row
     * @param col
     */
    public void open(final int row, final int col) {
        verifyRowAndColumnInRange(row, col);
        if (isOpen(row, col)) {
            return;
        }
        openedSites[toIndex(row, col)] = true;
        numberOfOpenSites++;
        List<List<Integer>> neighbours = Arrays.asList(
                Arrays.asList(row - 1, col),
                Arrays.asList(row, col + 1),
                Arrays.asList(row + 1, col),
                Arrays.asList(row, col - 1)
        );
        for (List<Integer> rowsAndCols : neighbours) {
            int neighborRow = rowsAndCols.get(0);
            int neighborCol = rowsAndCols.get(1);
            if (!rowAndColumnAreOutOfRange(neighborRow, neighborCol) && isOpen(neighborRow, neighborCol)) {
                unionFind.union(toIndex(row, col), toIndex(neighborRow, neighborCol));
            }
        }
    }

    private void verifyRowAndColumnInRange(int row, int col) {
        if (rowAndColumnAreOutOfRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean rowAndColumnAreOutOfRange(int row, int col) {
        return row < 1 || row > n || col < 1 || col > n;
    }

    private int toIndex(int row, int col) {
        return (row - 1) * n + col;
    }

    /**
     * Is site (row, col) open?
     *
     * @param row
     * @param col
     * @return <code>true</code> if site is open
     */
    public boolean isOpen(int row, int col) {
        verifyRowAndColumnInRange(row, col);
        return openedSites[toIndex(row, col)];
    }

    /**
     * Is site (row, col) full?
     *
     * @param row
     * @param col
     * @return <code>true</code> if site is full
     */
    public boolean isFull(int row, int col) {
        verifyRowAndColumnInRange(row, col);
        return unionFind.connected(0, toIndex(row, col));
    }

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /**
     * Does the system percolate?
     *
     * @return <code>true</code> if the system percolates.
     */
    public boolean percolates() {
        return unionFind.connected(VIRTUAL_TOP_SITE, virtualBottomSite);
    }

    /**
     * Test client (optional)
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
