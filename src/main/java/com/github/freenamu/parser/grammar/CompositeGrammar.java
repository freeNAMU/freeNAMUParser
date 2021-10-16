package com.github.freenamu.parser.grammar;

import com.github.freenamu.node.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeGrammar implements Grammar {
    protected final List<LeafGrammar> grammarList = new ArrayList<>();
    protected final Grammar nextPriorityGrammar;

    public CompositeGrammar(Grammar nextPriorityGrammar) {
        this.nextPriorityGrammar = nextPriorityGrammar;
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        boolean matchedAny;
        do {
            matchedAny = false;
            Integer minFirstMatchStartIndex = null;
            Integer indexOfGrammarWithMinFirstMatchStartIndex = null;
            for (int i = 0; i < grammarList.size(); i++) {
                LeafGrammar leafGrammar = grammarList.get(i);
                if (leafGrammar.match(rawText)) {
                    matchedAny = true;
                    if (minFirstMatchStartIndex == null || leafGrammar.getStart() < minFirstMatchStartIndex) {
                        minFirstMatchStartIndex = leafGrammar.getStart();
                        indexOfGrammarWithMinFirstMatchStartIndex = i;
                    }
                }
            }
            if (matchedAny) {
                int start = grammarList.get(indexOfGrammarWithMinFirstMatchStartIndex).getStart();
                int end = grammarList.get(indexOfGrammarWithMinFirstMatchStartIndex).getEnd();
                result.addAll(nextPriorityGrammar.parse(rawText.substring(0, start)));
                result.addAll(grammarList.get(indexOfGrammarWithMinFirstMatchStartIndex).parse(rawText.substring(start, end)));
                if (rawText.length() > end)
                    rawText = rawText.substring(end);
                else
                    break;
            } else {
                result.addAll(nextPriorityGrammar.parse(rawText));
            }
        } while (matchedAny);

        return result;
    }
}
