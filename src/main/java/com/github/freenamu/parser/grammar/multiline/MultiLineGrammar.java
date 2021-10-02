package com.github.freenamu.parser.grammar.multiline;

import com.github.freenamu.parser.grammar.CompositeGrammar;
import com.github.freenamu.parser.grammar.singleline.SingleLineGrammar;

public class MultiLineGrammar extends CompositeGrammar {
    public MultiLineGrammar() {
        this.grammarList.add(new QuotationGrammar());
        this.grammarList.add(new SingleLineGrammar());
    }
}
