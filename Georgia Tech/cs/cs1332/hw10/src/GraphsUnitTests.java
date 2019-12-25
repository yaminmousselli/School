import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests formulated from graphs that are easily visualized online
 *
 * @author Martin Kurien
 * @version 0.5555
 */
public class GraphsUnitTests {

    private static final int TIMEOUT = 200;

    private Graph<String> dfsWikipedia;
    // https://en.wikipedia.org/wiki/Depth-first_search#Example
    private Graph<Integer> CP341;
    private Graph<Integer> CP343;
    private Graph<Integer> CP344DAG;
    private Graph<Integer> CP349;
    private Graph<Integer> CP3417DAG;
    // https://visualgo.net/en/dfsbfs

    private Vertex<String> a = new Vertex<>("a");
    private Vertex<String> b = new Vertex<>("b");
    private Vertex<String> c = new Vertex<>("c");
    private Vertex<String> d = new Vertex<>("d");
    private Vertex<String> e = new Vertex<>("e");
    private Vertex<String> f = new Vertex<>("f");
    private Vertex<String> g = new Vertex<>("g");
    private Vertex<String> h = new Vertex<>("h");
    private Vertex<String> i = new Vertex<>("i");
    private Vertex<String> j = new Vertex<>("j");
    private Vertex<String> k = new Vertex<>("k");
    private Vertex<String> l = new Vertex<>("l");
    private Vertex<String> m = new Vertex<>("m");
    private Vertex<String> n = new Vertex<>("n");
    private Vertex<String> o = new Vertex<>("o");
    private Vertex<String> p = new Vertex<>("p");
    private Vertex<String> q = new Vertex<>("q");
    private Vertex<String> r = new Vertex<>("r");
    private Vertex<String> s = new Vertex<>("s");
    private Vertex<String> t = new Vertex<>("t");
    private Vertex<String> u = new Vertex<>("u");
    private Vertex<String> v = new Vertex<>("v");
    private Vertex<String> w = new Vertex<>("w");
    private Vertex<String> x = new Vertex<>("x");
    private Vertex<String> y = new Vertex<>("y");
    private Vertex<String> z = new Vertex<>("z");


    private Graph<Integer> directedGraph;
    private Vertex<Integer> zero = new Vertex<>(0);
    private Vertex<Integer> one = new Vertex<>(1);
    private Vertex<Integer> two = new Vertex<>(2);
    private Vertex<Integer> three = new Vertex<>(3);
    private Vertex<Integer> four = new Vertex<>(4);
    private Vertex<Integer> five = new Vertex<>(5);
    private Vertex<Integer> six = new Vertex<>(6);
    private Vertex<Integer> seven = new Vertex<>(7);
    private Vertex<Integer> eight = new Vertex<>(8);
    private Vertex<Integer> nine = new Vertex<>(9);
    private Vertex<Integer> ten = new Vertex<>(10);
    private Vertex<Integer> eleven = new Vertex<>(11);
    private Vertex<Integer> twelve = new Vertex<>(12);

    @Before
    public void setUp() {

        LinkedHashSet<Edge<String>> dfsWikipediaEdges = new LinkedHashSet<>();
        LinkedHashSet<Edge<Integer>> CP341Edges = new LinkedHashSet<>();
        LinkedHashSet<Edge<Integer>> CP343Edges = new LinkedHashSet<>();
        LinkedHashSet<Edge<Integer>> CP344DAGEdges = new LinkedHashSet<>();
        LinkedHashSet<Edge<Integer>> CP349Edges = new LinkedHashSet<>();
        LinkedHashSet<Edge<Integer>> CP3417DAGEdges = new LinkedHashSet<>();
        Set<Vertex<Integer>> CP341Vertices = new HashSet<>();

        dfsWikipediaEdges.add(new Edge<>(a, b, 0, false));
        dfsWikipediaEdges.add(new Edge<>(a, c, 0, false));
        dfsWikipediaEdges.add(new Edge<>(a, e, 0, false));
        dfsWikipediaEdges.add(new Edge<>(b, d, 0, false));
        dfsWikipediaEdges.add(new Edge<>(b, f, 0, false));
        dfsWikipediaEdges.add(new Edge<>(c, g, 0, false));
        dfsWikipediaEdges.add(new Edge<>(f, e, 0, false));

        CP341Edges.add(new Edge<>(zero, one, 0, false));
        CP341Edges.add(new Edge<>(one, three, 0, false));
        CP341Edges.add(new Edge<>(four, three, 0, false));
        CP341Edges.add(new Edge<>(one, two, 0, false));
        CP341Edges.add(new Edge<>(three, two, 0, false));
        CP341Edges.add(new Edge<>(seven, six, 0, false));
        CP341Edges.add(new Edge<>(eight, six, 0, false));

        CP341Vertices.add(zero);
        CP341Vertices.add(one);
        CP341Vertices.add(two);
        CP341Vertices.add(three);
        CP341Vertices.add(four);
        CP341Vertices.add(five);
        CP341Vertices.add(six);
        CP341Vertices.add(seven);
        CP341Vertices.add(eight);


        CP343Edges.add(new Edge<>(zero, one, 0, false));
        CP343Edges.add(new Edge<>(zero, four, 0, false));
        CP343Edges.add(new Edge<>(one, two, 0, false));
        CP343Edges.add(new Edge<>(one, five, 0, false));
        CP343Edges.add(new Edge<>(two, six, 0, false));
        CP343Edges.add(new Edge<>(two, three, 0, false));
        CP343Edges.add(new Edge<>(three, seven, 0, false));
        CP343Edges.add(new Edge<>(four, eight, 0, false));
        CP343Edges.add(new Edge<>(five, six, 0, false));
        CP343Edges.add(new Edge<>(five, ten, 0, false));
        CP343Edges.add(new Edge<>(six, eleven, 0, false));
        CP343Edges.add(new Edge<>(seven, twelve, 0, false));
        CP343Edges.add(new Edge<>(eight, nine, 0, false));
        CP343Edges.add(new Edge<>(nine, ten, 0, false));
        CP343Edges.add(new Edge<>(ten, eleven, 0, false));
        CP343Edges.add(new Edge<>(eleven, twelve, 0, false));

        CP344DAGEdges.add(new Edge<>(zero, one, 0, true));
        CP344DAGEdges.add(new Edge<>(zero, two, 0, true));
        CP344DAGEdges.add(new Edge<>(one, two, 0, true));
        CP344DAGEdges.add(new Edge<>(one, three, 0, true));
        CP344DAGEdges.add(new Edge<>(two, three, 0, true));
        CP344DAGEdges.add(new Edge<>(three, four, 0, true));
        CP344DAGEdges.add(new Edge<>(two, five, 0, true));
        CP344DAGEdges.add(new Edge<>(seven, six, 0, true));

        CP349Edges.add(new Edge<>(zero, one, 0, true));
        CP349Edges.add(new Edge<>(one, three, 0, true));
        CP349Edges.add(new Edge<>(three, two, 0, true));
        CP349Edges.add(new Edge<>(two, one, 0, true));
        CP349Edges.add(new Edge<>(three, four, 0, true));
        CP349Edges.add(new Edge<>(four, five, 0, true));
        CP349Edges.add(new Edge<>(five, seven, 0, true));
        CP349Edges.add(new Edge<>(seven, six, 0, true));
        CP349Edges.add(new Edge<>(six, four, 0, true));

        CP3417DAGEdges.add(new Edge<>(zero, one, 0, true));
        CP3417DAGEdges.add(new Edge<>(zero, two, 0, true));
        CP3417DAGEdges.add(new Edge<>(zero, three, 0, true));
        CP3417DAGEdges.add(new Edge<>(one, three, 0, true));
        CP3417DAGEdges.add(new Edge<>(one, four, 0, true));
        CP3417DAGEdges.add(new Edge<>(two, four, 0, true));
        CP3417DAGEdges.add(new Edge<>(three, four, 0, true));


        dfsWikipedia = new Graph<>(dfsWikipediaEdges);
        CP341 = new Graph<>(CP341Vertices, CP341Edges);
        CP343 = new Graph<>(CP343Edges);
        CP344DAG = new Graph<>(CP344DAGEdges);
        CP349 = new Graph<>(CP349Edges);
        CP3417DAG = new Graph<>(CP3417DAGEdges);



    }

    @Test(timeout = TIMEOUT)
    public void dfsWikipedia() {
        List<Vertex<String>> dfsWikipediaSoln = new ArrayList<>();
        dfsWikipediaSoln.add(a);
        dfsWikipediaSoln.add(b);
        dfsWikipediaSoln.add(d);
        dfsWikipediaSoln.add(f);
        dfsWikipediaSoln.add(e);
        dfsWikipediaSoln.add(c);
        dfsWikipediaSoln.add(g);
        List<Vertex<String>> dfsWikipediaAns = GraphAlgorithms
                .depthFirstSearch(a, dfsWikipedia);
        assertEquals(dfsWikipediaSoln, dfsWikipediaAns);

    }

    @Test(timeout = TIMEOUT)
    public void dfsCP341() {
        List<Vertex<Integer>> dfsZeroSoln = new ArrayList<>();
        dfsZeroSoln.add(zero);
        dfsZeroSoln.add(one);
        dfsZeroSoln.add(three);
        dfsZeroSoln.add(four);
        dfsZeroSoln.add(two);

        List<Vertex<Integer>> dfsZeroAns = GraphAlgorithms.depthFirstSearch
                (zero, CP341);
        assertEquals(dfsZeroSoln, dfsZeroAns);

        List<Vertex<Integer>> dfsFiveSoln = new ArrayList<>();
        dfsFiveSoln.add(five);

        List<Vertex<Integer>> dfsFiveAns = GraphAlgorithms.depthFirstSearch
                (five, CP341);
        assertEquals(dfsFiveSoln, dfsFiveAns);

        List<Vertex<Integer>> dfsSixSoln = new ArrayList<>();
        dfsSixSoln.add(six);
        dfsSixSoln.add(seven);
        dfsSixSoln.add(eight);

        List<Vertex<Integer>> dfsSixAns = GraphAlgorithms.depthFirstSearch
                (six, CP341);
        assertEquals(dfsSixSoln, dfsSixAns);
    }

    @Test(timeout = TIMEOUT)
    public void dfsCP343() {
        List<Vertex<Integer>> dfsZeroSoln = new ArrayList<>();
        dfsZeroSoln.add(zero);
        dfsZeroSoln.add(one);
        dfsZeroSoln.add(two);
        dfsZeroSoln.add(six);
        dfsZeroSoln.add(five);
        dfsZeroSoln.add(ten);
        dfsZeroSoln.add(nine);
        dfsZeroSoln.add(eight);
        dfsZeroSoln.add(four);
        dfsZeroSoln.add(eleven);
        dfsZeroSoln.add(twelve);
        dfsZeroSoln.add(seven);
        dfsZeroSoln.add(three);

        List<Vertex<Integer>> dfsZeroAns = GraphAlgorithms.depthFirstSearch
                (zero, CP343);
        assertEquals(dfsZeroSoln, dfsZeroAns);


        List<Vertex<Integer>> dfsFiveSoln = new ArrayList<>();
        dfsFiveSoln.add(five);
        dfsFiveSoln.add(one);
        dfsFiveSoln.add(zero);
        dfsFiveSoln.add(four);
        dfsFiveSoln.add(eight);
        dfsFiveSoln.add(nine);
        dfsFiveSoln.add(ten);
        dfsFiveSoln.add(eleven);
        dfsFiveSoln.add(six);
        dfsFiveSoln.add(two);
        dfsFiveSoln.add(three);
        dfsFiveSoln.add(seven);
        dfsFiveSoln.add(twelve);

        List<Vertex<Integer>> dfsFiveAns = GraphAlgorithms.depthFirstSearch
                (five, CP343);
        assertEquals(dfsFiveSoln, dfsFiveAns);
    }

    @Test(timeout = TIMEOUT)
    public void dfsCP344DAG() {
        List<Vertex<Integer>> dfsZeroSoln = new ArrayList<>();
        dfsZeroSoln.add(zero);
        dfsZeroSoln.add(one);
        dfsZeroSoln.add(two);
        dfsZeroSoln.add(three);
        dfsZeroSoln.add(four);
        dfsZeroSoln.add(five);

        List<Vertex<Integer>> dfsZeroAns = GraphAlgorithms.depthFirstSearch
                (zero, CP344DAG);
        assertEquals(dfsZeroSoln, dfsZeroAns);

        List<Vertex<Integer>> dfsFiveSoln = new ArrayList<>();
        dfsFiveSoln.add(five);

        List<Vertex<Integer>> dfsFiveAns = GraphAlgorithms.depthFirstSearch
                (five, CP344DAG);
        assertEquals(dfsFiveSoln, dfsFiveAns);

        List<Vertex<Integer>> dfsSevenSoln = new ArrayList<>();
        dfsSevenSoln.add(seven);
        dfsSevenSoln.add(six);

        List<Vertex<Integer>> dfsSevenAns = GraphAlgorithms.depthFirstSearch
                (seven, CP344DAG);
        assertEquals(dfsSevenSoln, dfsSevenAns);
    }

    @Test(timeout = TIMEOUT)
    public void dfsCP349() {
        List<Vertex<Integer>> dfsZeroSoln = new ArrayList<>();
        dfsZeroSoln.add(zero);
        dfsZeroSoln.add(one);
        dfsZeroSoln.add(three);
        dfsZeroSoln.add(two);
        dfsZeroSoln.add(four);
        dfsZeroSoln.add(five);
        dfsZeroSoln.add(seven);
        dfsZeroSoln.add(six);

        List<Vertex<Integer>> dfsZeroAns = GraphAlgorithms.depthFirstSearch
                (zero, CP349);
        assertEquals(dfsZeroSoln, dfsZeroAns);

    }

    @Test(timeout = TIMEOUT)
    public void dfsCP3417DAG() {
        List<Vertex<Integer>> dfsZeroSoln = new ArrayList<>();
        dfsZeroSoln.add(zero);
        dfsZeroSoln.add(one);
        dfsZeroSoln.add(three);
        dfsZeroSoln.add(four);
        dfsZeroSoln.add(two);

        List<Vertex<Integer>> dfsZeroAns = GraphAlgorithms.depthFirstSearch
                (zero, CP3417DAG);
        assertEquals(dfsZeroSoln, dfsZeroAns);
    }


    @Test(timeout = TIMEOUT)
    public void bfsCP341() {
        List<Vertex<Integer>> bfsZeroSoln = new ArrayList<>();
        bfsZeroSoln.add(zero);
        bfsZeroSoln.add(one);
        bfsZeroSoln.add(three);
        bfsZeroSoln.add(two);
        bfsZeroSoln.add(four);

        List<Vertex<Integer>> bfsZeroAns = GraphAlgorithms.breadthFirstSearch
                (zero, CP341);
        assertEquals(bfsZeroSoln, bfsZeroAns);


        List<Vertex<Integer>> bfsFiveSoln = new ArrayList<>();
        bfsFiveSoln.add(five);

        List<Vertex<Integer>> bfsFiveAns = GraphAlgorithms.breadthFirstSearch
                (five, CP341);
        assertEquals(bfsFiveSoln, bfsFiveAns);


        List<Vertex<Integer>> bfsSixSoln = new ArrayList<>();
        bfsSixSoln.add(six);
        bfsSixSoln.add(seven);
        bfsSixSoln.add(eight);

        List<Vertex<Integer>> bfsSixAns = GraphAlgorithms.breadthFirstSearch
                (six, CP341);
        assertEquals(bfsSixSoln, bfsSixAns);

    }

    @Test(timeout = TIMEOUT)
    public void bfsCP343() {
        List<Vertex<Integer>> bfsZeroSoln = new ArrayList<>();
        bfsZeroSoln.add(zero);
        bfsZeroSoln.add(one);
        bfsZeroSoln.add(four);
        bfsZeroSoln.add(two);
        bfsZeroSoln.add(five);
        bfsZeroSoln.add(eight);
        bfsZeroSoln.add(six);
        bfsZeroSoln.add(three);
        bfsZeroSoln.add(ten);
        bfsZeroSoln.add(nine);
        bfsZeroSoln.add(eleven);
        bfsZeroSoln.add(seven);
        bfsZeroSoln.add(twelve);

        List<Vertex<Integer>> bfsZeroAns = GraphAlgorithms.breadthFirstSearch
                (zero, CP343);
        assertEquals(bfsZeroSoln, bfsZeroAns);

        List<Vertex<Integer>> bfsFiveSoln = new ArrayList<>();
        bfsFiveSoln.add(five);
        bfsFiveSoln.add(one);
        bfsFiveSoln.add(six);
        bfsFiveSoln.add(ten);
        bfsFiveSoln.add(zero);
        bfsFiveSoln.add(two);
        bfsFiveSoln.add(eleven);
        bfsFiveSoln.add(nine);
        bfsFiveSoln.add(four);
        bfsFiveSoln.add(three);
        bfsFiveSoln.add(twelve);
        bfsFiveSoln.add(eight);
        bfsFiveSoln.add(seven);

        List<Vertex<Integer>> bfsFiveAns = GraphAlgorithms.breadthFirstSearch
                (five, CP343);
        assertEquals(bfsFiveSoln, bfsFiveAns);
    }

    @Test(timeout = TIMEOUT)
    public void bfsCP344DAG() {
        List<Vertex<Integer>> bfsZeroSoln = new ArrayList<>();
        bfsZeroSoln.add(zero);
        bfsZeroSoln.add(one);
        bfsZeroSoln.add(two);
        bfsZeroSoln.add(three);
        bfsZeroSoln.add(five);
        bfsZeroSoln.add(four);


        List<Vertex<Integer>> bfsZeroAns = GraphAlgorithms.breadthFirstSearch
                (zero, CP344DAG);
        assertEquals(bfsZeroSoln, bfsZeroAns);


        List<Vertex<Integer>> bfsFiveSoln = new ArrayList<>();
        bfsFiveSoln.add(five);

        List<Vertex<Integer>> bfsFiveAns = GraphAlgorithms.breadthFirstSearch
                (five, CP344DAG);
        assertEquals(bfsFiveSoln, bfsFiveAns);

        List<Vertex<Integer>> bfsSevenSoln = new ArrayList<>();
        bfsSevenSoln.add(seven);
        bfsSevenSoln.add(six);

        List<Vertex<Integer>> bfsSevenAns = GraphAlgorithms.breadthFirstSearch
                (seven, CP344DAG);
        assertEquals(bfsSevenSoln, bfsSevenAns);
    }

    @Test(timeout = TIMEOUT)
    public void bfsExceptions() {
        boolean mitt = false;
        try {
            GraphAlgorithms.breadthFirstSearch(null, CP341);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check null start", mitt);

        mitt = false;
        try {
            GraphAlgorithms.breadthFirstSearch(zero, null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check null graph", mitt);

        mitt = false;
        try {
            GraphAlgorithms.breadthFirstSearch(twelve, CP341);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check start not in graph", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void dfsExceptions() {
        boolean mitt = false;
        try {
            GraphAlgorithms.depthFirstSearch(null, CP341);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check null start", mitt);

        mitt = false;
        try {
            GraphAlgorithms.depthFirstSearch(zero, null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check null graph", mitt);

        mitt = false;
        try {
            GraphAlgorithms.depthFirstSearch(twelve, CP341);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check start not in graph", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void dijkstraExceptions() {
        boolean mitt = false;
        try {
            GraphAlgorithms.dijkstras(null, CP341);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check null start", mitt);

        mitt = false;
        try {
            GraphAlgorithms.dijkstras(zero, null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check null graph", mitt);

        mitt = false;
        try {
            GraphAlgorithms.dijkstras(twelve, CP341);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check start not in graph", mitt);
    }

    @Test(timeout = TIMEOUT)
    public void primsExceptions() {
        boolean mitt = false;
        try {
            GraphAlgorithms.prims(null, CP341);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check null start", mitt);

        mitt = false;
        try {
            GraphAlgorithms.prims(zero, null);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check null graph", mitt);

        mitt = false;
        try {
            GraphAlgorithms.prims(twelve, CP341);
        } catch (IllegalArgumentException e) {
            mitt = true;
        }
        assertTrue("Check start not in graph", mitt);
    }

}
