package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Anchor;
import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AnchorGrammar extends LeafGrammar {
    public AnchorGrammar() {
        super(Pattern.compile("\\[\\[[^|\n]+?(\\|[^\n]+?)?]]"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        String innerText = rawText.substring(2, rawText.length() - 2);
        int indexOfFirstPipe = innerText.indexOf('|');
        String reference = indexOfFirstPipe == -1 ? innerText : innerText.substring(0, indexOfFirstPipe);
        List<Node> children = new InlineGrammar().parse(innerText.substring(indexOfFirstPipe + 1));
        result.add(new Anchor(reference, children));

        return result;
    }
}
