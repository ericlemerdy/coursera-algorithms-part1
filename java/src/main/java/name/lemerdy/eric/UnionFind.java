package name.lemerdy.eric;

public interface UnionFind {
    /**
     * Add connection between p and q.
     *
     * @param p first object
     * @param q second object
     */
    void union(int p, int q);

    /**
     * Are p and q in the same component?
     *
     * @param p first object
     * @param q second object
     * @return <code>true</code> if <code>p</code> and <code>q</code> are in the same component.
     */
    boolean connected(int p, int q);

    /**
     * Component identifier for p (0 to N – 1)
     *
     * @param p the object
     * @return the component identifier
     */
    int find(int p);

    /**
     * Number of components
     *
     * @return the number of components
     */
    int count();
}
