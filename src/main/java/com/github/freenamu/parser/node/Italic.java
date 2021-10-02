package com.github.freenamu.parser.node;

import java.util.List;

public class Italic extends Node {
    public Italic(Node child) {
        super(Type.Italic, child);
    }

    public Italic(List<Node> children) {
        super(Type.Italic, children);
    }
}
