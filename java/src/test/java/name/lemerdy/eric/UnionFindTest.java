package name.lemerdy.eric;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnionFindTest {

    @Test
    public void should_validate_quick_find_connections() {
        UnionFind quickFind = new QuickFind(10);
        unionNodes(quickFind);

        validateConnections(quickFind);
    }

    @Test
    public void should_find_largest_element_quick_union() {
        UnionFind quickUnion = new QuickUnion(10);
        unionNodes(quickUnion);

        assertEquals(quickUnion.find(0), 7);
        assertEquals(quickUnion.find(1), 7);
        assertEquals(quickUnion.find(2), 7);
        assertEquals(quickUnion.find(3), 9);
        assertEquals(quickUnion.find(4), 9);
        assertEquals(quickUnion.find(5), 7);
        assertEquals(quickUnion.find(6), 7);
        assertEquals(quickUnion.find(7), 7);
        assertEquals(quickUnion.find(8), 9);
        assertEquals(quickUnion.find(9), 9);
    }

    public void should_print_quick_union_ids() {
        UnionFind quickUnion = new QuickUnion(10);
        unionNodes(quickUnion);

        String expected = "" +
                "0 1 2 3 4 5 6 7 8 9 \n" +
                "1 1 1 8 3 0 5 1 8 8 ";
        // "{0 1 2 5 6 7} {3 4 8 9}"
        assertEquals(expected, quickUnion.toString());
    }

    @Test
    public void should_validate_quick_union_connections() {
        UnionFind quickUnion = new QuickUnion(10);
        unionNodes(quickUnion);

        validateConnections(quickUnion);
    }

    @Test
    public void should_validate_weighted_quick_union_connections() {
        UnionFind weightedQuickUnion = new WeightedQuickUnion(10);
        unionNodes(weightedQuickUnion);

        validateConnections(weightedQuickUnion);
    }

    @Test
    public void should_validate_quick_union_with_path_compression_connections() {
        UnionFind quickUnionWithPathCompression = new QuickUnionWithPathCompression(10);
        unionNodes(quickUnionWithPathCompression);

        validateConnections(quickUnionWithPathCompression);
    }

    private void unionNodes(UnionFind quickFind) {
        quickFind.union(4, 3);
        quickFind.union(3, 8);
        quickFind.union(6, 5);
        quickFind.union(9, 4);
        quickFind.union(2, 1);
        quickFind.union(8, 9);
        quickFind.union(5, 0);
        quickFind.union(7, 2);
        quickFind.union(6, 1);
        quickFind.union(1, 0);
        quickFind.union(6, 7);
    }

    private void validateConnections(UnionFind unionFind) {
        assertTrue(unionFind.connected(0, 1));
        assertTrue(unionFind.connected(0, 2));
        assertFalse(unionFind.connected(0, 3));
        assertFalse(unionFind.connected(0, 4));
        assertTrue(unionFind.connected(0, 5));
        assertTrue(unionFind.connected(0, 6));
        assertTrue(unionFind.connected(0, 7));
        assertFalse(unionFind.connected(0, 8));
        assertFalse(unionFind.connected(0, 9));
        assertTrue(unionFind.connected(1, 2));
        assertFalse(unionFind.connected(1, 3));
        assertFalse(unionFind.connected(1, 4));
        assertTrue(unionFind.connected(1, 5));
        assertTrue(unionFind.connected(1, 6));
        assertTrue(unionFind.connected(1, 7));
        assertFalse(unionFind.connected(1, 8));
        assertFalse(unionFind.connected(1, 9));
        assertFalse(unionFind.connected(2, 3));
        assertFalse(unionFind.connected(2, 4));
        assertTrue(unionFind.connected(2, 5));
        assertTrue(unionFind.connected(2, 6));
        assertTrue(unionFind.connected(2, 7));
        assertFalse(unionFind.connected(2, 8));
        assertFalse(unionFind.connected(2, 9));
        assertTrue(unionFind.connected(3, 4));
        assertFalse(unionFind.connected(3, 5));
        assertFalse(unionFind.connected(3, 6));
        assertFalse(unionFind.connected(3, 7));
        assertTrue(unionFind.connected(3, 8));
        assertTrue(unionFind.connected(3, 9));
        assertFalse(unionFind.connected(4, 5));
        assertFalse(unionFind.connected(4, 6));
        assertFalse(unionFind.connected(4, 7));
        assertTrue(unionFind.connected(4, 8));
        assertTrue(unionFind.connected(4, 9));
        assertTrue(unionFind.connected(5, 6));
        assertTrue(unionFind.connected(5, 7));
        assertFalse(unionFind.connected(5, 8));
        assertFalse(unionFind.connected(5, 9));
        assertTrue(unionFind.connected(6, 7));
        assertFalse(unionFind.connected(6, 8));
        assertFalse(unionFind.connected(6, 9));
        assertFalse(unionFind.connected(7, 8));
        assertFalse(unionFind.connected(7, 9));
        assertTrue(unionFind.connected(8, 9));
    }

}
