package com.github.freenamu.parser.grammar;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeGrammar implements Grammar {
    protected final List<Grammar> grammarList = new ArrayList<>();

    @Override
    public Integer getFirstMatchStartIndex(String rawText) {
        Integer minFirstMatchIndex = null;
        for (Grammar grammar : grammarList) {
            Integer firstMatchIndex = grammar.getFirstMatchStartIndex(rawText);
            if (firstMatchIndex != null && (minFirstMatchIndex == null || firstMatchIndex < minFirstMatchIndex)) {
                minFirstMatchIndex = firstMatchIndex;
            }
        }
        return minFirstMatchIndex;
    }
}
