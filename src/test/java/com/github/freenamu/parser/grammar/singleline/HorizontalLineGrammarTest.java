package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.singleline.HorizontalLine;
import com.github.freenamu.parser.node.singleline.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalLineGrammarTest {
    private HorizontalLineGrammar horizontalLineGrammar;

    @BeforeEach
    void setUp() {
        horizontalLineGrammar = new HorizontalLineGrammar();
    }

    @Test
    public void matchHorizontalLineGrammarWith4Hyphen() {
        // Given
        String rawText = "test1\n----\ntest2";
        int expected = 6;

        // When
        Integer actual = horizontalLineGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void matchHorizontalLineGrammarWith9Hyphen() {
        // Given
        String rawText = "test1\n---------\ntest2";
        int expected = 6;

        // When
        Integer actual = horizontalLineGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchHorizontalLineGrammarWith3Hyphen() {
        // Given
        String rawText = "test1\n---\ntest2";

        // When
        Integer actual = horizontalLineGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void notMatchHorizontalLineGrammarWith10Hyphen() {
        // Given
        String rawText = "test1\n----------\ntest2";

        // When
        Integer actual = horizontalLineGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseHorizontalLineGrammar() {
        // Given
        String rawText = "test1\n----\ntest2";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1\n"));
        expected.add(new HorizontalLine());
        expected.add(new Text("test2"));

        // When
        List<Node> actual = horizontalLineGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseHorizontalLineGrammarOnly() {
        // Given
        String rawText = "----";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new HorizontalLine());
        expected.add(new Text(""));

        // When
        List<Node> actual = horizontalLineGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
