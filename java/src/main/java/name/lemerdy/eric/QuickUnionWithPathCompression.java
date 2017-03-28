package name.lemerdy.eric;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class QuickUnionWithPathCompression implements UnionFind {
    private int[] id;

    public QuickUnionWithPathCompression(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    public int find(int p) {
        throw new NotImplementedException();
    }

    public int count() {
        throw new NotImplementedException();
    }
}
