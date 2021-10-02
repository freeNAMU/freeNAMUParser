package com.github.freenamu.parser.grammar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LeafGrammar implements Grammar {
    protected final Pattern pattern;

    public LeafGrammar(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Integer getFirstMatchStartIndex(String rawText) {
        Matcher matcher = pattern.matcher(rawText);
        if (matcher.find()) {
            return matcher.start();
        } else {
            return null;
        }
    }
}
