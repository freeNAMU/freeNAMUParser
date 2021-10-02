package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.grammar.CompositeGrammar;

public class QuoteGrammar extends CompositeGrammar {
    public QuoteGrammar() {
        grammarList.add(new BoldGrammar());
        grammarList.add(new ItalicGrammar());
    }
}
