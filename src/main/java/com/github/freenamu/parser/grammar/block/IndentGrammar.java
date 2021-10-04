package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.Break;
import com.github.freenamu.node.Indent;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import com.github.freenamu.parser.grammar.LeafGrammar;
import com.github.freenamu.parser.grammar.inline.InlineGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndentGrammar extends LeafGrammar {
    public IndentGrammar() {
        super(Pattern.compile("(\n?^ .*?$\n?)+", Pattern.MULTILINE));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            if (matcher.start() > 0)
                result.add(new Text(rawText.substring(0, matcher.start())));

            String innerText = rawText.substring(matcher.start(), matcher.end()).replaceAll("\n ", "\n");
            List<Node> children = new BlockGrammar().parse(innerText);
            if (children.get(0).equals(new Break()))
                children.remove(0);
            if (children.get(children.size() - 1).equals(new Break()))
                children.remove(children.size() - 1);
            result.add(new Indent(children));

            if (rawText.length() > matcher.end())
                result.addAll(new InlineGrammar().parse(rawText.substring(matcher.end())));
        }

        return result;
    }
}