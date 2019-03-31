package bmo.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;

public class GraphTest {
    static Graph graph;
    static Node a, b, c, d, e;
    
    @BeforeClass
    public static void initializeGraph() throws Exception {
        graph = new Graph();
        a = new Node("A");
        b = new Node("B");
        c = new Node("C");
        d = new Node("D");
        e = new Node("E");
        /*Input Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7*/
        graph.routeTable.put(a, new Edge(a, b, 5).next(new Edge(a, d, 5).next(new Edge(a, e, 7))));
        graph.routeTable.put(b, new Edge(b, c, 4));
        graph.routeTable.put(c, new Edge(c, d, 8).next(new Edge(c, e, 2)));
        graph.routeTable.put(d, new Edge(d, c, 8).next(new Edge(d, e, 6)));
        graph.routeTable.put(e, new Edge(e, b, 3));
    }

    @Test
    public void DistanceBetween_ABC() throws Exception {
        ArrayList<Node> route = new ArrayList<>();
        route.add(a);
        route.add(b);
        route.add(c);
        assertEquals(9, graph.getDistance(route));
    }

    @Test
    public void DistanceBetween_AD() throws Exception {
        ArrayList<Node> route = new ArrayList<>();
        route.add(a);
        route.add(d);
        assertEquals(5, graph.getDistance(route));
    }

    @Test
    public void DistanceBetween_ADC() throws Exception {
        ArrayList<Node> route = new ArrayList<>();
        route.add(a);
        route.add(d);
        route.add(c);
        assertEquals(13, graph.getDistance(route));
    }

    @Test
    public void DistanceBetween_AEBCD() throws Exception {
        ArrayList<Node> route = new ArrayList<>();
        route.add(a);
        route.add(e);
        route.add(b);
        route.add(c);
        route.add(d);
        assertEquals(22, graph.getDistance(route));
    }

    @Test(expected = Exception.class)
    public void DistanceBetween_AED() throws Exception {
        ArrayList<Node> route = new ArrayList<>();
        route.add(a);
        route.add(e);
        route.add(d);
        assertEquals(-1, graph.getDistance(route));
    }

    @Test
    public void NumStops_CC3() throws Exception {
        int res = graph.calculateNumbsOfStops(c, c, 0, 3);
        assertEquals(2, res);
    }

    @Test
    public void NumStops_AC4() throws Exception {
        int res = graph.calculateNumbsOfStops(a, c, 0, 4);
        assertEquals(4, res);
    }

    @Test
    public void ShortestRoute_AC() throws Exception {
        int res = graph.calculateShortestPath(a, c, 0, 0);
        assertEquals(9, res);
    }

    @Test
    public void ShortestRoute_BB() throws Exception {
        int res = graph.calculateShortestPath(b, b, 0, 0);
        assertEquals(9, res);
    }

    @Test
    public void numRoutes_CC_within_30() throws Exception {
        int res = graph.getNumsOfRoutes(c, c, 0, 30);
        assertEquals(7, res);
    }

    @Test
    public void equalsNode() {
        Node a1 = new Node("A");
        Node a2 = new Node("A");
        Node b = new Node("B");
        assertEquals(true, a1.equals(a2));
        assertEquals(false, a1.equals(b));
        assertEquals(true, (new Node("Test").equals(new Node("Test"))));
    }

}
