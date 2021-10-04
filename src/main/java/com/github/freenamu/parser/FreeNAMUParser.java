package com.github.freenamu.parser;

import com.github.freenamu.node.Article;
import com.github.freenamu.parser.grammar.paragraph.ParagraphGrammar;

public class FreeNAMUParser {
    public Article parse(String rawText) {
        return new Article(new ParagraphGrammar().parse(rawText));
    }
}
