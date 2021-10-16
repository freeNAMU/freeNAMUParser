package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Bold;
import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BoldGrammar extends LeafGrammar {
    public BoldGrammar() {
        super(Pattern.compile("'''[^\n]+?'''"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        List<Node> children = new InlineGrammar().parse(rawText.substring(3, rawText.length() - 3));
        result.add(new Bold(children));

        return result;
    }
}
