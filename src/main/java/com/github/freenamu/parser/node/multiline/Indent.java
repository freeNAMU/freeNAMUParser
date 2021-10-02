package com.github.freenamu.parser.node.multiline;

import com.github.freenamu.parser.node.Node;

import java.util.List;

public class Indent extends Node {
    public Indent(Node child) {
        super(Type.Indent, child);
    }

    public Indent(List<Node> children) {
        super(Type.Indent, children);
    }
}
