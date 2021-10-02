package com.github.freenamu.parser.node;

public class Footnote extends Node {
    private final String anchor;

    public Footnote(String anchor) {
        super(Type.Footnote);
        this.anchor = anchor;
    }

    public String getAnchor() {
        return anchor;
    }
}
