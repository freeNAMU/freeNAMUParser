package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommentGrammar extends LeafGrammar {
    public CommentGrammar() {
        super(Pattern.compile("\n?^##[^\n]+?$\n?", Pattern.MULTILINE));
    }

    @Override
    public List<Node> parse(String rawText) {
        return new ArrayList<>();
    }
}
