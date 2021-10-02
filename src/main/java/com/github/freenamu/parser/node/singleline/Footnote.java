package com.github.freenamu.parser.node.singleline;

import com.github.freenamu.parser.node.Node;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Footnote footnote = (Footnote) o;
        return Objects.equals(anchor, footnote.anchor);
    }
}
