package com.github.freenamu.parser;

import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.ParagraphGrammar;

import java.util.ArrayList;
import java.util.List;

public class FreeNAMUParser {
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        rawText = rawText.replaceAll("\r\n", "\n");
        result.addAll(new ParagraphGrammar().parse(rawText));

        return result;
    }
}
