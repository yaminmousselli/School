import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 *
 * @author rekt_n00b
 * @version 0.00
 */

@SuppressWarnings("unchecked")
public class bleh {

    private static final int TIMEOUT = 2000;

    // Graph with 2 nodes and no edges. Should return null.
    @Test(timeout = TIMEOUT)
    public void primsTrivialEdgeCase() {
        LinkedHashSet<Edge<Integer>> edgeSet = new LinkedHashSet<>();

        Vertex<Integer> a = new Vertex<>(1);
        Vertex<Integer> b = new Vertex<>(2);

        Set<Vertex<Integer>> nodes = new HashSet<>();
        nodes.add(a); nodes.add(b);
        LinkedHashSet<Edge<Integer>> edges = new LinkedHashSet<>();
        Graph lonely = new Graph<>(nodes, edges);

        assertEquals(null, GraphAlgorithms.prims(a, lonely));
    }

    /*
        Okay so this test checks whether your prims' implementation is indeed O(E log E + V).
        Although this graph is sparse and has a pretty simple structure, it can detect subtle
        bugs in Prims' implementations that can cause it to become O(V ^ 2).

        If your code times out on this case, try drawing out the graph to visualize whats happening.
        The bug is because you are considering all possible MSTs instead of just one. If a node has
        already been 'covered', you shouldn't look at its neighbours again. If you do so, your
        implementation will become O(V ^ 2).
    */
    @Test(timeout = TIMEOUT)
    public void primsRekt() {
        LinkedHashSet<Edge<Integer>> edgeSet = new LinkedHashSet<>();

        int n = 100000;
        Vertex<Integer>[] nodes = new Vertex[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Vertex<Integer>(i);
        }

        int source = 0;
        int middle = n / 4 + 1;
        for (int i = 1; i <= n / 4; i++) {
            edgeSet.add(new Edge<>(nodes[source], nodes[i], 1, false));
            edgeSet.add(new Edge<>(nodes[i], nodes[middle], 1, false));
        }
        for (int i = middle + 1; i < n; i++) {
            edgeSet.add(new Edge<>(nodes[middle], nodes[i], 1, false));
            // The edge below *should* be ignored
            edgeSet.add(new Edge<>(nodes[source], nodes[i], 10000, false));
        }

        Graph<Integer> gonnaGetRekt = new Graph<>(edgeSet);

        Set<Edge<Integer>> mySolution = GraphAlgorithms.prims(nodes[source], gonnaGetRekt);

        // My graph has multiple possible MSTs, so I'll just check if its any one of
        // the multiple valid MSTs.

        assertEquals(n - 1, mySolution.size()); // Exactly n - 1 edges.
        for (Edge e : mySolution) {
            assertEquals(1, e.getWeight()); // Each edge has weight 1.
        }

        // Must span all nodes.
        boolean[] spanned = new boolean[n];
        for (Edge e : mySolution) {
            int u = (int) e.getU().getData();
            int v = (int) e.getV().getData();
            spanned[u] = true;
            spanned[v] = true;
        }
        for (int i = 0; i < n; i++) {
            if (!spanned[i]) {
                assertTrue("The graph you returned has cycles!", false);
            }
        }
    }

    /*
        Okay so this test checks whether your dijkstra implementation is indeed O(E log E + V).
        Although this graph is sparse and has a pretty simple structure, it can detect subtle
        bugs in dijkstra implementations that can cause it to become O(V ^ 2).

        If your code times out on this case, try drawing out the graph to visualize whats happening.
        The bug is because you are considering all possible shortest paths instead of just one.
        For example, if there are k incoming edges to a node 'v', all of which are optimal for 'v',
        you should consider *exactly one* of them, and not all. If you consider all of them, you
        will look at v's adjacency list too many times, and since 'v' can have O(V) nodes in its
        adjacency list, your code will become O(V^2).
    */
    @Test(timeout = TIMEOUT)
    public void dijkstraRekt() {
        LinkedHashSet<Edge<Integer>> edgeSet = new LinkedHashSet<>();

        int n = 100000;
        Vertex<Integer>[] nodes = new Vertex[n + 2];
        for (int i = 0; i <= n + 1; i++) {
            nodes[i] = new Vertex<Integer>(i);
        }

        int source = 0;
        int middle = n / 4 + 1;
        for (int i = 1; i <= n / 4; i++) {
            edgeSet.add(new Edge<>(nodes[source], nodes[i], 1, false));
            edgeSet.add(new Edge<>(nodes[i], nodes[middle], 1, false));
        }
        for (int i = middle + 1; i < n; i++) {
            edgeSet.add(new Edge<>(nodes[middle], nodes[i], 1, false));
            // The edge below *should* be ignored.
            edgeSet.add(new Edge<>(nodes[source], nodes[i], 10000, false));
        }

        edgeSet.add(new Edge<>(nodes[n], nodes[n + 1], 1, false));

        Map<Vertex, Integer> solution = new HashMap<>();
        solution.put(nodes[source], 0);
        for (int i = 1; i <= n / 4; i++) {
            solution.put(nodes[i], 1);
        }
        solution.put(nodes[middle], 2);
        for (int i = middle + 1; i < n; i++) {
            solution.put(nodes[i], 3);
        }
        solution.put(nodes[n], Integer.MAX_VALUE); // Unreachable from source.
        solution.put(nodes[n + 1], Integer.MAX_VALUE); // Unreachable from source.

        Graph<Integer> gonnaGetRekt = new Graph<>(edgeSet);
        Map<Vertex<Integer>, Integer> mySolution =
            GraphAlgorithms.dijkstras(nodes[source], gonnaGetRekt);

        assertEquals(n + 2, mySolution.keySet().size()); // No extra keys.
        for (int i = 0; i <= n + 1; i++) {
            assertEquals(solution.get(nodes[i]), mySolution.get(nodes[i])); // <K, V> matches.
        }
    }
}