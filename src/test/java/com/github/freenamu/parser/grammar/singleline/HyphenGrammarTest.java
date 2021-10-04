package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.singleline.Strikeout;
import com.github.freenamu.node.singleline.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HyphenGrammarTest {
    private HyphenGrammar hyphenGrammar;

    @Before
    public void setUp() {
        hyphenGrammar = new HyphenGrammar();
    }

    @Test
    public void matchHyphenGrammar() {
        // Given
        String rawText = "test1--test2--test3";
        Integer expected = 5;

        // When
        Integer actual = hyphenGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchHyphenGrammarWithLineFeed() {
        // Given
        String rawText = "test1--te\nst2--test3";

        // When
        Integer actual = hyphenGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseHyphenGrammar() {
        // Given
        String rawText = "test1--test2--test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Strikeout(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = hyphenGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseHyphenGrammarOnly() {
        // Given
        String rawText = "--test2--";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new Strikeout(new Text("test2")));
        expected.add(new Text(""));

        // When
        List<Node> actual = hyphenGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}