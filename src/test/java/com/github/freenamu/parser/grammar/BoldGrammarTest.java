package com.github.freenamu.parser.grammar;

import com.github.freenamu.parser.node.Bold;
import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoldGrammarTest {
    private BoldGrammar boldGrammar;

    @BeforeEach
    void setUp() {
        boldGrammar = new BoldGrammar();
    }

    @Test
    public void matchBoldGrammar() {
        // Given
        String rawText = "test1'''test2'''test3";
        int expected = 5;

        // When
        Integer actual = boldGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchBoldGrammarWithLineFeed() {
        // Given
        String rawText = "test1'''te\nst2'''test3";

        // When
        Integer actual = boldGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseBoldGrammar() {
        // Given
        String rawText = "test1'''test2'''test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        Bold bold = new Bold();
        bold.add(new Text("test2"));
        expected.add(bold);
        expected.add(new Text("test3"));

        // When
        List<Node> actual = boldGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseBoldGrammarOnly() {
        // Given
        String rawText = "'''test2'''";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        Bold bold = new Bold();
        bold.add(new Text("test2"));
        expected.add(bold);
        expected.add(new Text(""));

        // When
        List<Node> actual = boldGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
