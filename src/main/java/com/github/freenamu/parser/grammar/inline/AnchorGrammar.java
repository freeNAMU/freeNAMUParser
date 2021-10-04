package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Anchor;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnchorGrammar extends LeafGrammar {
    public AnchorGrammar() {
        super(Pattern.compile("\\[\\[[^|\n]+?(\\|[^\n]+?)?]]"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            if (matcher.start() > 0)
                result.add(new Text(rawText.substring(0, matcher.start())));

            String innerText = rawText.substring(matcher.start() + 2, matcher.end() - 2);
            int indexOfFirstPipe = innerText.indexOf('|');
            String reference = indexOfFirstPipe == -1 ? innerText : innerText.substring(0, indexOfFirstPipe);
            List<Node> children = new InlineGrammar().parse(innerText.substring(indexOfFirstPipe + 1));
            result.add(new Anchor(reference, children));

            if (rawText.length() > matcher.end())
                result.addAll(new InlineGrammar().parse(rawText.substring(matcher.end())));
        }

        return result;
    }
}
