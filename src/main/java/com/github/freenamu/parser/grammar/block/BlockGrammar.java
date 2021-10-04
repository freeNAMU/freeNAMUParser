package com.github.freenamu.parser.grammar.block;

import com.github.freenamu.parser.grammar.CompositeGrammar;
import com.github.freenamu.parser.grammar.inline.InlineGrammar;

public class BlockGrammar extends CompositeGrammar {
    public BlockGrammar() {
        this.grammarList.add(new BlockquoteGrammar());
        this.grammarList.add(new IndentGrammar());
        this.grammarList.add(new HorizontalRuleGrammar());
        this.grammarList.add(new InlineGrammar());
    }
}
