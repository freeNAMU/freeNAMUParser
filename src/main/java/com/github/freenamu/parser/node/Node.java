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

    public void add(Node child) {
        this.children.add(child);
    }

    public void addAll(List<Node> children) {
        this.children.addAll(children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!type.equals(((Node) o).type)) return false;
        if (children.size() != ((Node) o).children.size()) return false;
        for (int i = 0; i < children.size(); i++) {
            if (!children.get(i).equals(((Node) o).children.get(i))) return false;
        }
        return true;
    }

    public enum Type {
        Bold,
        Italic,
        Underline,
        Strikeout,
        Superscript,
        Subscript,
        Footnote,
        LineFeed,
        HorizontalLine,
        Text
    }
}
