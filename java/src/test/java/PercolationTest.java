import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.reflect.Whitebox.invokeMethod;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Percolation.class})
public class PercolationTest {

    @Mock
    private WeightedQuickUnionUF unionFind;

    @Test
    public void constructor_should_connect_site_to_virtual_top_site_and_virtual_bottom() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);

        new Percolation(3);

        verifyConnectionWithVirtualTopSiteAndVirtualBottomSite();
    }

    protected void verifyConnectionWithVirtualTopSiteAndVirtualBottomSite() {
        verify(unionFind).union(0, 1);
        verify(unionFind).union(0, 2);
        verify(unionFind).union(0, 3);
        verify(unionFind).union(10, 7);
        verify(unionFind).union(10, 8);
        verify(unionFind).union(10, 9);
    }

    @Test
    public void shuold_convert_row_col_to_index() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        Percolation percolation = new Percolation(3);

        int toIndex = invokeMethod(percolation, "toIndex", 2, 2);

        assertEquals(5, toIndex);
    }

    @Test
    public void open_should_union_to_neighbors() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        Percolation percolation = new Percolation(3);
        percolation.open(1, 2);
        percolation.open(2, 3);
        percolation.open(3, 2);
        percolation.open(2, 1);

        percolation.open(2, 2);

        verifyConnectionWithVirtualTopSiteAndVirtualBottomSite();
        verify(unionFind).union(5, 2);
        verify(unionFind).union(5, 6);
        verify(unionFind).union(5, 8);
        verify(unionFind).union(5, 4);
    }

    @Test
    public void a_site_should_be_blocked_by_default() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        Percolation percolation = new Percolation(3);

        assertFalse(percolation.isOpen(2, 2));
    }

    @Test
    public void an_opened_site_should_be_opened() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        Percolation percolation = new Percolation(3);
        percolation.open(2, 2);

        assertTrue(percolation.isOpen(2, 2));
    }

    @Test
    public void a_full_site_should_be_connected_to_the_virtual_top_site() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        Percolation percolation = new Percolation(3);
        percolation.open(1, 2);
        percolation.open(2, 2);

        percolation.isFull(2, 2);

        verify(unionFind).connected(0, 5);
    }

    @Test
    public void should_count_the_number_of_open_site() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        Percolation percolation = new Percolation(3);
        percolation.open(1, 2);
        percolation.open(2, 1);
        percolation.open(2, 3);

        assertEquals(3, percolation.numberOfOpenSites());
    }

    @Test
    public void should_percolates() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        Percolation percolation = new Percolation(3);

        percolation.percolates();

        verify(unionFind).connected(0, 10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_open_row_less_than_1() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).open(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_open_column_less_than_1() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).open(2, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_open_row_more_than_n() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).open(4, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_open_column_more_than_n() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).open(2, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_isOpen_row_less_than_1() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).isOpen(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_isOpen_column_less_than_1() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).isOpen(2, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_isOpen_row_more_than_n() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).isOpen(4, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_isOpen_column_more_than_n() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).isOpen(2, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_isFull_row_less_than_1() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).isFull(0, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_isFull_column_less_than_1() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).isFull(2, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_isFull_row_more_than_n() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).isFull(4, 2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void corner_case_isFull_column_more_than_n() throws Exception {
        whenNew(WeightedQuickUnionUF.class).withAnyArguments().thenReturn(unionFind);
        new Percolation(3).isFull(2, 4);
    }

}