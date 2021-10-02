package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.node.Italic;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItalicGrammarTest {
    private ItalicGrammar italicGrammar;

    @BeforeEach
    public void setUp() {
        italicGrammar = new ItalicGrammar();
    }

    @Test
    public void matchItalicGrammar() {
        // Given
        String rawText = "test1''test2''test3";
        int expected = 5;

        // When
        Integer actual = italicGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchItalicGrammarWithLineFeed() {
        // Given
        String rawText = "test1''te\nst2''test3";

        // When
        Integer actual = italicGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseItalicGrammar() {
        // Given
        String rawText = "test1''test2''test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Italic(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = italicGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseItalicGrammarOnly() {
        // Given
        String rawText = "''test2''";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new Italic(new Text("test2")));
        expected.add(new Text(""));

        // When
        List<Node> actual = italicGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
