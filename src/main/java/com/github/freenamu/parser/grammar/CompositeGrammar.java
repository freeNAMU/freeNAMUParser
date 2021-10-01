package com.github.freenamu.parser.grammar;

import java.util.List;

public abstract class CompositeGrammar implements Grammar {
    protected final List<Grammar> grammarList;

    public CompositeGrammar(List<Grammar> grammarList) {
        this.grammarList = grammarList;
    }

    @Override
    public Integer getFirstMatchIndex(String rawText) {
        Integer minFirstMatchIndex = null;
        for (Grammar grammar : grammarList) {
            Integer firstMatchIndex = grammar.getFirstMatchIndex(rawText);
            if (firstMatchIndex != null && (minFirstMatchIndex == null || firstMatchIndex < minFirstMatchIndex)) {
                minFirstMatchIndex = firstMatchIndex;
            }
        }
        return minFirstMatchIndex;
    }
}
