package com.github.freenamu.parser.node.singleline;

import com.github.freenamu.parser.node.Node;

import java.util.List;

public class Strikeout extends Node {
    public Strikeout(Node child) {
        super(Type.Strikeout, child);
    }

    public Strikeout(List<Node> children) {
        super(Type.Strikeout, children);
    }
}
