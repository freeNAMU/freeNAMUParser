package com.github.freenamu.parser;

import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.ParagraphGrammar;
import com.github.freenamu.parser.grammar.RedirectGrammar;

import java.util.ArrayList;
import java.util.List;

public class FreeNAMUParser {
    private final RedirectGrammar redirectGrammar = new RedirectGrammar();
    private final ParagraphGrammar paragraphGrammar = new ParagraphGrammar();

    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        rawText = rawText.replaceAll("\r\n", "\n");
        if (redirectGrammar.match(rawText)) {
            result.addAll(redirectGrammar.parse(rawText));
        } else {
            result.addAll(paragraphGrammar.parse(rawText));
        }

        return result;
    }
}
