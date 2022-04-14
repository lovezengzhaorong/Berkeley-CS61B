import java.util.Arrays;

public class UnionFind {

    private final int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        if (n <= 0) {
            throw new IllegalArgumentException("need positive n");
        }
        Arrays.fill(parent, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex > parent.length) {
            throw new IllegalArgumentException(vertex + "is not a valid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -parent[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return (find(v1) == find(v2));
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 == root2) {
            return;
        }
        if (sizeOf(v1) < sizeOf(v2)) {
            parent[root1] = root2;
            parent[root2] = -(sizeOf(v1) + sizeOf(v2));
        } else {
            parent[root2] = root1;
            parent[root1] = -(sizeOf(v1)+ sizeOf(v2));
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int origin = vertex;
        // 如果 verte 和它 parent值不相等，那让vertex指向parent值
        while (parent[origin] >= 0) {
            origin = parent[origin];
        }
        while (parent[vertex] >= 0) {
            int temp = parent[vertex];
            parent[vertex] = origin;
            vertex = temp;
        }
        return origin;
    }

}
