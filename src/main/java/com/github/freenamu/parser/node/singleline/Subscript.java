package com.github.freenamu.parser.node.singleline;

import com.github.freenamu.parser.node.Node;

import java.util.List;

public class Subscript extends Node {
    public Subscript(Node child) {
        super(Type.Subscript, child);
    }

    public Subscript(List<Node> children) {
        super(Type.Subscript, children);
    }
}
