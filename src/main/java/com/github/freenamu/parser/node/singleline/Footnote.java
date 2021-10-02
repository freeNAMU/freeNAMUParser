package com.github.freenamu.parser.node.singleline;

import com.github.freenamu.parser.node.Node;

import java.util.List;

public class Footnote extends Node {
    private final String anchor;

    public Footnote(String anchor) {
        super(Type.Footnote);
        this.anchor = anchor;
    }

    public Footnote(String anchor, Node child) {
        super(Type.Footnote, child);
        this.anchor = anchor;
    }

    public Footnote(String anchor, List<Node> children) {
        super(Type.Footnote, children);
        this.anchor = anchor;
    }

    public String getAnchor() {
        return anchor;
    }
}
