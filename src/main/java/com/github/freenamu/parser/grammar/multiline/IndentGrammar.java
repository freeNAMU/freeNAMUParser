package com.github.freenamu.parser.grammar.multiline;

import com.github.freenamu.parser.grammar.LeafGrammar;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.multiline.Indent;
import com.github.freenamu.parser.node.singleline.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndentGrammar extends LeafGrammar {
    public IndentGrammar() {
        super(Pattern.compile("(^ .*\n)*(^ .*$)", Pattern.MULTILINE));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            if (matcher.start() == 0) {
                result.add(new Text(""));
            } else {
                result.add(new Text(rawText.substring(0, matcher.start() - 1)));
            }
            String substring = rawText.substring(matcher.start() + 1, matcher.end()).replaceAll("\n ", "\n");
            result.add(new Indent(new MultiLineGrammar().parse(substring)));
            if (rawText.length() > matcher.end()) {
                result.addAll(new MultiLineGrammar().parse(rawText.substring(matcher.end() + 1)));
            } else {
                result.add(new Text(""));
            }
        }

        return result;
    }
}
