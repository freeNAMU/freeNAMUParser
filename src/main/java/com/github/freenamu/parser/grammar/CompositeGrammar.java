package com.github.freenamu.parser.grammar;

import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Text;

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
            result.add(new Text(rawText));
        }

        return result;
    }
}
