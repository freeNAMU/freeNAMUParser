package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.grammar.LeafGrammar;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.singleline.Link;
import com.github.freenamu.parser.node.singleline.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkGrammar extends LeafGrammar {
    public LinkGrammar() {
        super(Pattern.compile("\\[\\[[^|\n]+?(\\|[^\n]+?)?]]"));
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        if (matcher.find()) {
            result.add(new Text(rawText.substring(0, matcher.start())));
            String substring = rawText.substring(matcher.start() + 2, matcher.end() - 2);
            int indexOfFirstPipe = substring.indexOf('|');
            Link link;
            if (indexOfFirstPipe == -1) {
                link = new Link(substring);
            } else {
                List<Node> displayText = new SingleLineGrammar().parse(substring.substring(indexOfFirstPipe + 1));
                link = new Link(substring, displayText);
            }
            result.add(link);
            result.addAll(new SingleLineGrammar().parse(rawText.substring(matcher.end())));
        }

        return result;
    }
}
