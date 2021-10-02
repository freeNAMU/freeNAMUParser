package com.github.freenamu.parser.node.singleline;

import com.github.freenamu.parser.node.Node;

import java.util.List;
import java.util.Objects;

public class Link extends Node {
    private final String link;

    public Link(String link) {
        super(Type.Link);
        this.link = link;
    }

    public Link(String link, Node child) {
        super(Type.Link, child);
        this.link = link;
    }

    public Link(String link, List<Node> children) {
        super(Type.Link, children);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Link link1 = (Link) o;
        return Objects.equals(link, link1.link);
    }
}
