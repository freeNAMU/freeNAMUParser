package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.parser.grammar.CompositeGrammar;
import com.github.freenamu.parser.grammar.TextGrammar;

public class InlineGrammar extends CompositeGrammar {
    public InlineGrammar() {
        super(new TextGrammar());
        this.grammarList.add(new BreakGrammar());
        this.grammarList.add(new FootnoteGrammar());
        this.grammarList.add(new AnchorGrammar());
        this.grammarList.add(new BoldGrammar());
        this.grammarList.add(new ItalicGrammar());
        this.grammarList.add(new UnderlineGrammar());
        this.grammarList.add(new StrikeoutGrammar());
        this.grammarList.add(new SuperscriptGrammar());
        this.grammarList.add(new SubscriptGrammar());
    }
}
