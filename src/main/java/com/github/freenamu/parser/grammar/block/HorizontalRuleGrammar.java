package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.HorizontalRule;
import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HorizontalRuleGrammar extends LeafGrammar {
    public HorizontalRuleGrammar() {
        super(Pattern.compile("\n?^-{4,9}$\n?", Pattern.MULTILINE));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        result.add(new HorizontalRule());

        return result;
    }
}
