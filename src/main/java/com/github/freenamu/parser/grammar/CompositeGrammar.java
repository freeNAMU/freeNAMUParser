package com.github.freenamu.parser.grammar;

import com.github.freenamu.node.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeGrammar implements Grammar {
    protected final List<Grammar> grammarList = new ArrayList<>();
    protected final Grammar defaultGrammar;

    public CompositeGrammar(Grammar defaultGrammar) {
        this.defaultGrammar = defaultGrammar;
    }

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

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        Integer minFirstMatchStartIndex = null;
        Integer grammarIndexOfMinFirstMatchStartIndex = null;
        for (int i = 0; i < grammarList.size(); i++) {
            Integer firstMatchStartIndex = grammarList.get(i).getFirstMatchStartIndex(rawText);
            if (firstMatchStartIndex != null) {
                if (minFirstMatchStartIndex == null || firstMatchStartIndex < minFirstMatchStartIndex) {
                    minFirstMatchStartIndex = firstMatchStartIndex;
                    grammarIndexOfMinFirstMatchStartIndex = i;
                }
            }
        }
        if (minFirstMatchStartIndex != null) {
            return grammarList.get(grammarIndexOfMinFirstMatchStartIndex).parse(rawText);
        } else {
            return defaultGrammar.parse(rawText);
        }
    }
}
