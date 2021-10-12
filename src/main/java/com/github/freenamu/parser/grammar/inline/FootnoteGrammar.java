package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Footnote;
import com.github.freenamu.node.Node;
import com.github.freenamu.node.Text;
import com.github.freenamu.parser.grammar.LeafGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FootnoteGrammar extends LeafGrammar {
    private Integer end = null;

    public FootnoteGrammar() {
        super(Pattern.compile("\\[\\*[^ \n]* "));
    }

    @Override
    public Integer getFirstMatchStartIndex(String rawText) {
        Matcher matcher = pattern.matcher(rawText);
        int bracket = 0;
        if (matcher.find()) {
            for (int i = matcher.end(); i < rawText.length(); i++) {
                if (rawText.charAt(i) == '[') bracket++;
                else if (rawText.charAt(i) == ']') bracket--;
                else if (rawText.charAt(i) == '\n') return null;
                if (bracket == -1) {
                    this.end = i + 1;
                    return matcher.start();
                }
            }
        }
        return null;
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            if (matcher.start() > 0)
                result.add(new Text(rawText.substring(0, matcher.start())));

            String innerText = rawText.substring(matcher.start() + 2, getEnd(rawText) - 1);
            int indexOfFirstSpace = innerText.indexOf(' ');
            String anchor = indexOfFirstSpace == 0 ? null : innerText.substring(0, indexOfFirstSpace);
            List<Node> children = new InlineGrammar().parse(innerText.substring(indexOfFirstSpace + 1));
            result.add(new Footnote(anchor, children));

            if (rawText.length() > getEnd(rawText))
                result.addAll(new InlineGrammar().parse(rawText.substring(getEnd(rawText))));
        }

        return result;
    }

    private int getEnd(String rawText) {
        if (this.end == null) getFirstMatchStartIndex(rawText);
        return this.end;
    }
}
