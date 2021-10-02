package com.github.freenamu.parser.node;

import java.util.List;

public class Document extends Node {
    public Document(List<Node> children) {
        super(Type.Document, children);
    }
}
