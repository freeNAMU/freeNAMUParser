package com.github.freenamu.parser.grammar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class LeafGrammar implements Grammar {
    protected final Pattern pattern;
    private int start;
    private int end;

    public LeafGrammar(Pattern pattern) {
        this.pattern = pattern;
    }

    public boolean match(String rawText) {
        Matcher matcher = pattern.matcher(rawText);
        boolean result = matcher.find();
        if (result) {
            this.start = matcher.start();
            this.end = matcher.end();
        }
        return result;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
