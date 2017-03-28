import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF unionFind;
    private final int n;
    private final boolean[] openedSites;
    private int numberOfOpenSites = 0;
    private final int virtualBottomSite;
    private final int virtualTopSite = 0;

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
            unionFind.union(virtualTopSite, topSiteIndex);
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
    public void open(int row, int col) {
        verifyRowAndColumnInRange(row, col);
        if (isOpen(row, col)) {
            return;
        }
        openedSites[toIndex(row, col)] = true;
        numberOfOpenSites++;
        try {
            if (isOpen(row - 1, col)) {
                unionFind.union(toIndex(row, col), toIndex(row - 1, col));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (isOpen(row, col + 1)) {
                unionFind.union(toIndex(row, col), toIndex(row, col + 1));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (isOpen(row + 1, col)) {
                unionFind.union(toIndex(row, col), toIndex(row + 1, col));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        try {
            if (isOpen(row, col - 1)) {
                unionFind.union(toIndex(row, col), toIndex(row, col - 1));
            }
        } catch (IndexOutOfBoundsException e) {
        }
    }

    protected void verifyRowAndColumnInRange(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IndexOutOfBoundsException();
        }
    }

    protected int toIndex(int row, int col) {
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
        return unionFind.connected(virtualTopSite, virtualBottomSite);
    }

    /**
     * Test client (optional)
     *
     * @param args
     */
    public static void main(String[] args) {
    }
}
