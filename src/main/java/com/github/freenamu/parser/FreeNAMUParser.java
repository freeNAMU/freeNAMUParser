package com.github.freenamu.parser;

import com.github.freenamu.parser.grammar.paragraph.ParagraphGrammar;
import com.github.freenamu.parser.node.Document;

public class FreeNAMUParser {
    public Document parse(String rawText) {
        return new Document(new ParagraphGrammar().parse(rawText));
    }
}
