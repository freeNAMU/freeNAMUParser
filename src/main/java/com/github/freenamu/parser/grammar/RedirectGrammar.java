package com.github.freenamu.parser.grammar;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Redirect;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isWhitespace;

public class RedirectGrammar extends LeafGrammar {
    public RedirectGrammar() {
        super(Pattern.compile("\\A#redirect .+$", Pattern.MULTILINE));
    }

    @Override
    public boolean match(String rawText) {
        Matcher matcher = pattern.matcher(rawText);
        boolean result = matcher.find();
        if (result) {
            String documentName = rawText.substring(10);
            if (isAllWhitespace(documentName)) {
                return false;
            } else {
                setStart(matcher.start());
                setEnd(matcher.end());
            }
        }
        return result;
    }

    @Override
    public List<Node> parse(String rawText) {
        List<Node> result = new ArrayList<>();

        String documentName = rawText.substring(10);
        if (documentName.charAt(documentName.length() - 1) == '\n')
            documentName = documentName.substring(0, documentName.length() - 1);
        result.add(new Redirect(documentName));

        return result;
    }

    private boolean isAllWhitespace(String documentName) {
        for (int i = 0; i < documentName.length(); i++) {
            if (!isWhitespace(documentName.charAt(i)))
                return false;
        }
        return true;
    }
}
