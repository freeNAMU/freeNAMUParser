package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Break;
import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BreakGrammar extends LeafGrammar {
    public BreakGrammar() {
        super(Pattern.compile("\n"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        result.add(new Break());

        return result;
    }
}
