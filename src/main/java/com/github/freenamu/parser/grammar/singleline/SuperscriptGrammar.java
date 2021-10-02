package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.grammar.LeafGrammar;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Superscript;
import com.github.freenamu.parser.node.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SuperscriptGrammar extends LeafGrammar {
    public SuperscriptGrammar() {
        super(Pattern.compile("\\^\\^[^\n]+?\\^\\^"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            result.add(new Text(rawText.substring(0, matcher.start())));
            Superscript superscript = new Superscript();
            superscript.addAll(new SingleLineGrammar().parse(rawText.substring(matcher.start() + 2, matcher.end() - 2)));
            result.add(superscript);
            result.addAll(new SingleLineGrammar().parse(rawText.substring(matcher.end())));
        }

        return result;
    }
}
