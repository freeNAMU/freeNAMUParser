package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.grammar.LeafGrammar;
import com.github.freenamu.parser.node.Footnote;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FootnoteGrammar extends LeafGrammar {
    public FootnoteGrammar() {
        super(Pattern.compile("\\[\\*[^\n]+?]"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            result.add(new Text(rawText.substring(0, matcher.start())));
            String substring = rawText.substring(matcher.start() + 2, matcher.end() - 1);
            int indexOfFirstSpace = substring.indexOf(' ');
            Footnote footnote;
            if (indexOfFirstSpace == -1) {
                footnote = new Footnote(substring);
            } else {
                List<Node> footnoteText = new SingleLineGrammar().parse(substring.substring(indexOfFirstSpace + 1));
                if (indexOfFirstSpace == 0) {
                    footnote = new Footnote(null, footnoteText);
                } else {
                    footnote = new Footnote(substring.substring(0, indexOfFirstSpace), footnoteText);
                }
            }
            result.add(footnote);
            result.addAll(new SingleLineGrammar().parse(rawText.substring(matcher.end())));
        }

        return result;
    }
}
