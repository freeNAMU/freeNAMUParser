package com.github.freenamu.parser.node;

import java.util.List;

public class Subscript extends Node {
    public Subscript(Node child) {
        super(Type.Subscript, child);
    }

    public Subscript(List<Node> children) {
        super(Type.Subscript, children);
    }
}
