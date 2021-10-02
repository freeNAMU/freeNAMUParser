package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.node.LineFeed;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LineFeedGrammarTest {
    private LineFeedGrammar lineFeedGrammar;

    @BeforeEach
    void setUp() {
        lineFeedGrammar = new LineFeedGrammar();
    }

    @Test
    public void matchLineFeedGrammar() {
        // Given
        String rawText = "test1\n\ntest2";
        int expected = 6;

        // When
        Integer actual = lineFeedGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchLineFeedGrammarWittOneLineFeed() {
        // Given
        String rawText = "test1\ntest2";

        // When
        Integer actual = lineFeedGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseLineFeedGrammar() {
        // Given
        String rawText = "test1\n\ntest2";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1\n"));
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
