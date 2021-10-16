package com.github.freenamu.parser.grammar;

import com.github.freenamu.node.Node;

import java.util.List;

public interface Grammar {
    List<Node> parse(String rawText);
}
