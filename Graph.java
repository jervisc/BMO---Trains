/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmo.test;

/**
 *
 * @author CHENJE8
 */
import java.util.ArrayList;
import java.util.Hashtable;

public class Graph {
    public Hashtable<Node, Edge> routeTable;

    public Graph() {
        this.routeTable = new Hashtable<Node, Edge>();
    }

    public int getDistance(ArrayList<Node> cities) throws Exception {
        if (cities.size() < 2) {
            return 0;
        }
        int distance = 0;
        int depth = 0;
        int i = 0;
        while (i < cities.size() - 1) {
            if (this.routeTable.containsKey(cities.get(i))) {
                Edge route = this.routeTable.get(cities.get(i));
                while (route != null) {
                    if (route.to.equals(cities.get(i + 1))) {
                        distance += route.weight;
                        depth++;
                        break;
                    }
                    route = route.next;
                }
            } else {
                throw new Exception("NO SUCH ROUTE");
            }
            i++;
        }
        if (depth != cities.size() - 1) {
            throw new Exception("NO SUCH ROUTE");
        }
        return distance;
    }

    public int calculateNumbsOfStops(Node start, Node end, int depth, int maxStops) throws Exception {
        int routes = 0;
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            depth++;
            if (depth > maxStops) {
                return 0;
            }
            start.visited = true;
            Edge edge = this.routeTable.get(start);
            while (edge != null) {
                if (edge.to.equals(end)) {
                    routes++;
                    edge = edge.next;
                    continue;
                } else if (!edge.to.visited) {
                    routes += calculateNumbsOfStops(edge.to, end, depth, maxStops);
                    depth--;
                }
                edge = edge.next;
            }
        } else {
            throw new Exception("NO SUCH ROUTE");
        }
        start.visited = false;
        return routes;
    }

    public int calculateShortestPath(Node start, Node end, int weight, int shortestRoute) throws Exception {
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            start.visited = true;
            Edge edge = this.routeTable.get(start);
            while (edge != null) {
                if (edge.to == end || !edge.to.visited) {
                    weight += edge.weight;
                }
                if (edge.to.equals(end)) {
                    if (shortestRoute == 0 || weight < shortestRoute) {
                        shortestRoute = weight;
                    }
                    start.visited = false;
                    return shortestRoute;
                } else if (!edge.to.visited) {
                    shortestRoute = calculateShortestPath(edge.to, end, weight, shortestRoute);
                    weight -= edge.weight;
                }
                edge = edge.next;
            }
        } else {
            throw new Exception("NO SUCH ROUTE");
        }
        start.visited = false;
        return shortestRoute;
    }

    public int getNumsOfRoutes(Node start, Node end, int weight, int maxDistance) throws Exception {
        // for Maximum distance
        int routes = 0;
        if (this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            Edge edge = this.routeTable.get(start);
            while (edge != null) {
                weight += edge.weight;
                if (weight <= maxDistance) {
                    if (edge.to.equals(end)) {
                        routes++;
                        routes += getNumsOfRoutes(edge.to, end, weight, maxDistance);
                        edge = edge.next;
                        continue;
                    } else {
                        routes += getNumsOfRoutes(edge.to, end, weight, maxDistance);
                        weight -= edge.weight;
                    }
                } else {
                    weight -= edge.weight;
                }
                edge = edge.next;
            }
        } else {
            throw new Exception("NO SUCH ROUTE");
        }
        return routes;
    }
}
