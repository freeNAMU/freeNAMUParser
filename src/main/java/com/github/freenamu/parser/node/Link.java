package com.github.freenamu.parser.node;

import java.util.List;

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
}
