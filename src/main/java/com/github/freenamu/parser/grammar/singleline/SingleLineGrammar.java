package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.grammar.CompositeGrammar;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Text;

import java.util.ArrayList;
import java.util.List;

public class SingleLineGrammar extends CompositeGrammar {
    public SingleLineGrammar() {
        this.grammarList.add(new QuoteGrammar());
        this.grammarList.add(new UnderlineGrammar());
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
