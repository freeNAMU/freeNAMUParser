package com.github.freenamu.parser.node;

import java.util.List;

public class Bold extends Node {
    public Bold(Node child) {
        super(Type.Bold, child);
    }

    public Bold(List<Node> children) {
        super(Type.Bold, children);
    }
}
