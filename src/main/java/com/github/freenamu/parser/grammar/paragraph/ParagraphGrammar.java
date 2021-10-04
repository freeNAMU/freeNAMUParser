package com.github.freenamu.parser.grammar.paragraph;

import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.Grammar;

import java.util.List;

public class ParagraphGrammar implements Grammar {
    private final Grammar level2ParagraphGrammar;

    public ParagraphGrammar() {
        this.level2ParagraphGrammar = new LeveledParagraphGrammar(2);
    }

    @Override
    public Integer getFirstMatchStartIndex(String rawText) {
        return level2ParagraphGrammar.getFirstMatchStartIndex(rawText);
    }

    @Override
    public List<Node> parse(String rawText) {
        return level2ParagraphGrammar.parse(rawText);
    }
}
