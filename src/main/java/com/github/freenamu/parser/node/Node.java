package com.github.freenamu.parser.node;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {
    private final Type type;
    private final List<Node> children;

    public Node(Type type) {
        this.type = type;
        this.children = new ArrayList<>();
    }

    public Type getType() {
        return type;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void add(List<Node> newChildren) {
        this.children.addAll(newChildren);
    }

    public enum Type {
    }
}
