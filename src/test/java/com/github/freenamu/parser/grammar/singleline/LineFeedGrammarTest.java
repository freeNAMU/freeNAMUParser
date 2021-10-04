package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.singleline.LineFeed;
import com.github.freenamu.node.singleline.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LineFeedGrammarTest {
    private LineFeedGrammar lineFeedGrammar;

    @Before
    public void setUp() {
        lineFeedGrammar = new LineFeedGrammar();
    }

    @Test
    public void matchLineFeedGrammar() {
        // Given
        String rawText = "test1\ntest2";
        Integer expected = 5;

        // When
        Integer actual = lineFeedGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void parseLineFeedGrammar() {
        // Given
        String rawText = "test1\ntest2";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new LineFeed());
        expected.add(new Text("test2"));

        // When
        List<Node> actual = lineFeedGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseLineFeedGrammarOnly() {
        // Given
        String rawText = "\n";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new LineFeed());
        expected.add(new Text(""));

        // When
        List<Node> actual = lineFeedGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
