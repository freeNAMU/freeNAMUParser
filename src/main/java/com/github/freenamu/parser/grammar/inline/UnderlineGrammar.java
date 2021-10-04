package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import com.github.freenamu.node.Underline;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnderlineGrammar extends LeafGrammar {
    public UnderlineGrammar() {
        super(Pattern.compile("__[^\n]+?__"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            if (matcher.start() > 0)
                result.add(new Text(rawText.substring(0, matcher.start())));

            List<Node> children = new InlineGrammar().parse(rawText.substring(matcher.start() + 2, matcher.end() - 2));
            result.add(new Underline(children));

            if (rawText.length() > matcher.end())
                result.addAll(new InlineGrammar().parse(rawText.substring(matcher.end())));
        }

        return result;
    }
}