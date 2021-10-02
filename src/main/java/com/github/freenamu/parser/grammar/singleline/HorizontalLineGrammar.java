package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.grammar.LeafGrammar;
import com.github.freenamu.parser.node.HorizontalLine;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HorizontalLineGrammar extends LeafGrammar {
    public HorizontalLineGrammar() {
        super(Pattern.compile("^-{4,9}$", Pattern.MULTILINE));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            result.add(new Text(rawText.substring(0, matcher.start())));
            result.add(new HorizontalLine());
            if (rawText.length() > matcher.end()) {
                result.addAll(new SingleLineGrammar().parse(rawText.substring(matcher.end() + 1)));
            } else {
                result.add(new Text(""));
            }
        }

        return result;
    }
}
