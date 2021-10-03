package com.github.freenamu.parser;

import com.github.freenamu.parser.grammar.paragraph.ParagraphGrammar;
import com.github.freenamu.parser.node.Article;

public class FreeNAMUParser {
    public Article parse(String rawText) {
        return new Article(new ParagraphGrammar().parse(rawText));
    }
}
