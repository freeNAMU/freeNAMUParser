package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.node.Break;
import com.github.freenamu.node.Indent;
import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IndentGrammar extends LeafGrammar {
    public IndentGrammar() {
        super(Pattern.compile("(\n?^ .*?$\n?)+", Pattern.MULTILINE));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        String substring = rawText.substring((rawText.charAt(0) == '\n' ? 0 : 1));
        String innerText = substring.replaceAll("\n ", "\n");
        List<Node> children = new BlockGrammar().parse(innerText);
        if (children.size() > 0 && children.get(0).equals(new Break()))
            children.remove(0);
        if (children.size() > 0 && children.get(children.size() - 1).equals(new Break()))
            children.remove(children.size() - 1);
        result.add(new Indent(children));

        return result;
    }
}
