package com.github.freenamu.parser.node;

import java.util.List;

public class Strikeout extends Node {
    public Strikeout(Node child) {
        super(Type.Strikeout, child);
    }

    public Strikeout(List<Node> children) {
        super(Type.Strikeout, children);
    }
}
