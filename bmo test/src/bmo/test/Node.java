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
public class Node {

    public String name;
    public boolean visited;

    public Node(String name) {
        this.name = name;
        this.visited = false;
    }

    @Override
    public boolean equals(Object b) {
        if (b == null || b.getClass() != getClass()) {
            return false;
        }
        Node bx = (Node) b;
        return this.name.equals(bx.name);
    }
}
