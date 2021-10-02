package com.github.freenamu.parser.node.singleline;

import com.github.freenamu.parser.node.Node;

import java.util.List;

public class Italic extends Node {
    public Italic(Node child) {
        super(Type.Italic, child);
    }

    public Italic(List<Node> children) {
        super(Type.Italic, children);
    }
}
