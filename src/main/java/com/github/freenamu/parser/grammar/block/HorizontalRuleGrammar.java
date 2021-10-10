package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.HorizontalRule;
import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.LeafGrammar;
import com.github.freenamu.parser.grammar.inline.InlineGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HorizontalRuleGrammar extends LeafGrammar {
    public HorizontalRuleGrammar() {
        super(Pattern.compile("\n?^-{4,9}$\n?", Pattern.MULTILINE));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            if (matcher.start() > 0)
                result.addAll(new InlineGrammar().parse(rawText.substring(0, matcher.start())));

            result.add(new HorizontalRule());

            if (rawText.length() > matcher.end())
                result.addAll(new BlockGrammar().parse(rawText.substring(matcher.end())));
        }

        return result;
    }
}
