package com.github.freenamu.parser.grammar.paragraph;

import com.github.freenamu.parser.grammar.LeafGrammar;
import com.github.freenamu.parser.grammar.multiline.MultiLineGrammar;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.paragraph.Paragraph;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeveledParagraphGrammar extends LeafGrammar {
    private final int level;

    public LeveledParagraphGrammar(int level) {
        super(Pattern.compile(String.format("^={%1$d}( .*? |# .*? #)={%1$d}$", level), Pattern.MULTILINE));
        this.level = level;
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        List<String> sibling = split(rawText);
        result.addAll(new MultiLineGrammar().parse(sibling.get(0)));
        for (int i = 1; i < sibling.size(); i++) {
            String substring = sibling.get(i);
            Matcher matcher = pattern.matcher(substring);
            if (matcher.find()) {
                String title = substring.substring(matcher.start() + level, matcher.end() - level);
                boolean fold = title.charAt(0) == '#';
                if (fold) {
                    title = title.substring(2, title.length() - 2);
                } else {
                    title = title.substring(1, title.length() - 1);
                }
                String subText;
                if (substring.length() > matcher.end()) {
                    subText = substring.substring(matcher.end() + 1);
                } else {
                    subText = "";
                }
                if (level < 6) {
                    result.add(new Paragraph(level, fold, title, new LeveledParagraphGrammar(level + 1).parse(subText)));
                } else {
                    result.add(new Paragraph(level, fold, title, new MultiLineGrammar().parse(subText)));
                }
            }
        }
        return result;
    }

    private List<String> split(String rawText) {
        List<String> sibling = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);
        int start = 0;
        while (matcher.find()) {
            if (matcher.start() == 0) {
                sibling.add("");
            } else {
                sibling.add(rawText.substring(start, matcher.start() - 1));
            }
            start = matcher.start();
        }
        sibling.add(rawText.substring(start));
        return sibling;
    }
}
