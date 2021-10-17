package com.github.freenamu.parser.grammar;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Paragraph;
import com.github.freenamu.parser.grammar.block.BlockGrammar;
import com.github.freenamu.parser.grammar.inline.InlineGrammar;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphGrammar implements Grammar {
    private final Pattern pattern;

    public ParagraphGrammar() {
        this.pattern = Pattern.compile(getParagraphPattern(), Pattern.MULTILINE);
    }

    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        List<String> subText = splitRawText(rawText);
        if (subText.get(0).length() > 0)
            result.addAll(new BlockGrammar().parse(subText.get(0)));
        for (int i = 1; i < subText.size(); i++)
            result.add(parseParagraph(subText.get(i)));

        return result;
    }

    private String getParagraphPattern() {
        List<String> patterns = new ArrayList<>();
        for (int i = 2; i <= 6; i++) {
            patterns.add(String.format("(\n?^={%1$d}( .*? |# .*? #)={%1$d}$\n?)", i));
        }
        return String.join("|", patterns);
    }

    private List<String> splitRawText(String rawText) {
        List<String> subText = new ArrayList<>();
        Matcher matcher = pattern.matcher(rawText);

        List<Integer> matches = new ArrayList<>();
        matches.add(0);
        while (matcher.find())
            matches.add(matcher.start());
        matches.add(rawText.length());
        for (int i = 0; i < matches.size() - 1; i++)
            subText.add(rawText.substring(matches.get(i), matches.get(i + 1)));

        return subText;
    }

    private Node parseParagraph(String rawText) {
        Matcher matcher = pattern.matcher(rawText);
        matcher.find();

        String titleText = rawText.substring(matcher.start(), matcher.end()).replaceAll("\n", "");
        int level = 0;
        while (titleText.charAt(level) == '=')
            level++;
        boolean fold = titleText.charAt(level) == '#';
        if (fold)
            titleText = titleText.substring(level + 2, titleText.length() - level - 2);
        else
            titleText = titleText.substring(level + 1, titleText.length() - level - 1);
        List<Node> title = new ArrayList<>(new InlineGrammar().parse(titleText));

        List<Node> children = new ArrayList<>();
        if (rawText.length() > matcher.end())
            children.addAll(new BlockGrammar().parse(rawText.substring(matcher.end())));

        return new Paragraph(title, level, fold, children);
    }
}
