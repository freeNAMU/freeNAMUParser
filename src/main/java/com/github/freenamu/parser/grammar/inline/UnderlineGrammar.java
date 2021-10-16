package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Underline;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UnderlineGrammar extends LeafGrammar {
    public UnderlineGrammar() {
        super(Pattern.compile("__[^\n]+?__"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        List<Node> children = new InlineGrammar().parse(rawText.substring(2, rawText.length() - 2));
        result.add(new Underline(children));

        return result;
    }
}
