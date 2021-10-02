package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.grammar.CompositeGrammar;

public class StrikeoutGrammar extends CompositeGrammar {
    public StrikeoutGrammar() {
        grammarList.add(new TildeGrammar());
        grammarList.add(new HyphenGrammar());
    }
}
