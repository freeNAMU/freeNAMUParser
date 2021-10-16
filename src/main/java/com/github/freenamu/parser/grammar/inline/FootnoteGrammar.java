package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Footnote;
import com.github.freenamu.node.Node;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FootnoteGrammar extends LeafGrammar {
    public FootnoteGrammar() {
        super(Pattern.compile("\\[\\*[^ \n]* "));
    }

    @Override
    public boolean match(String rawText) {
        Matcher matcher = pattern.matcher(rawText);
        int bracket = 0;
        if (matcher.find()) {
            for (int i = matcher.end(); i < rawText.length(); i++) {
                if (rawText.charAt(i) == '[') bracket++;
                else if (rawText.charAt(i) == ']') bracket--;
                else if (rawText.charAt(i) == '\n') return false;
                if (bracket == -1) {
                    setStart(matcher.start());
                    setEnd(i + 1);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        String innerText = rawText.substring(2, rawText.length() - 1);
        int indexOfFirstSpace = innerText.indexOf(' ');
        String anchor = indexOfFirstSpace == 0 ? null : innerText.substring(0, indexOfFirstSpace);
        List<Node> children = new InlineGrammar().parse(innerText.substring(indexOfFirstSpace + 1));
        result.add(new Footnote(anchor, children));

        return result;
    }
}
