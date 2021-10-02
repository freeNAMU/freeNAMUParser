package com.github.freenamu.parser.node;

import java.util.List;

public class Underline extends Node {
    public Underline(Node child) {
        super(Type.Underline, child);
    }

    public Underline(List<Node> children) {
        super(Type.Underline, children);
    }
}
