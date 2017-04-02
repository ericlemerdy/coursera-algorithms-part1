package name.lemerdy.eric;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class QuickUnion implements UnionFind {
    private int[] id;

    public QuickUnion(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
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
        return root(p);
    }

    public int count() {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        StringBuffer toString = new StringBuffer();
        for (int i = 0; i < id.length; i++) {
            toString.append(i).append(' ');
        }
        toString.append('\n');
        for (int i = 0; i < id.length; i++) {
            toString.append(id[i]).append(' ');
        }
        return toString.toString();
    }
}
