package com.github.freenamu.parser.node.single;

import com.github.freenamu.parser.node.Node;

import java.util.List;

public class Underline extends Node {
    public Underline(Node child) {
        super(Type.Underline, child);
    }

    public Underline(List<Node> children) {
        super(Type.Underline, children);
    }
}
