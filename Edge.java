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
public class Edge {

    public Node from;
    public Node to;
    public int weight;
    public Edge next;

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.next = null;
    }

    public Edge next(Edge edge) {
        this.next = edge;
        return this;
    }
}
