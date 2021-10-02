package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.grammar.CompositeGrammar;

public class SingleLineGrammar extends CompositeGrammar {
    public SingleLineGrammar() {
        this.grammarList.add(new HorizontalLineGrammar());
        this.grammarList.add(new LineFeedGrammar());
        this.grammarList.add(new FootnoteGrammar());
        this.grammarList.add(new QuoteGrammar());
        this.grammarList.add(new UnderlineGrammar());
        this.grammarList.add(new StrikeoutGrammar());
        this.grammarList.add(new SuperscriptGrammar());
        this.grammarList.add(new SubscriptGrammar());
    }
}
