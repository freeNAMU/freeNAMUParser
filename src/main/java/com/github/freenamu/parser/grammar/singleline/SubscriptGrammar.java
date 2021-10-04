package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.singleline.Subscript;
import com.github.freenamu.node.singleline.Text;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubscriptGrammar extends LeafGrammar {
    public SubscriptGrammar() {
        super(Pattern.compile(",,[^\n]+?,,"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            result.add(new Text(rawText.substring(0, matcher.start())));
            result.add(new Subscript(new SingleLineGrammar().parse(rawText.substring(matcher.start() + 2, matcher.end() - 2))));
            result.addAll(new SingleLineGrammar().parse(rawText.substring(matcher.end())));
        }

        return result;
    }
}
