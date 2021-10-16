package com.github.freenamu.parser.grammar;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TextGrammar extends LeafGrammar {
    public TextGrammar() {
        super(Pattern.compile(".*"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        if (!rawText.equals(""))
            result.add(new Text(rawText));

        return result;
    }
}
