package com.github.freenamu.parser.grammar.singleline;

import com.github.freenamu.parser.node.Node;
import com.github.freenamu.parser.node.Subscript;
import com.github.freenamu.parser.node.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptGrammarTest {
    private SubscriptGrammar subscriptGrammar;

    @BeforeEach
    public void setUp() {
        subscriptGrammar = new SubscriptGrammar();
    }

    @Test
    public void matchSubscriptGrammar() {
        // Given
        String rawText = "test1,,test2,,test3";
        int expected = 5;

        // When
        Integer actual = subscriptGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void notMatchSubscriptGrammarWithLineFeed() {
        // Given
        String rawText = "test1,,te\nst2,,test3";

        // When
        Integer actual = subscriptGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parseSubscriptGrammar() {
        // Given
        String rawText = "test1,,test2,,test3";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Subscript(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = subscriptGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    @Test
    public void parseSubscriptGrammarOnly() {
        // Given
        String rawText = ",,test2,,";

        List<Node> expected = new ArrayList<>();
        expected.add(new Text(""));
        expected.add(new Subscript(new Text("test2")));
        expected.add(new Text(""));

        // When
        List<Node> actual = subscriptGrammar.parse(rawText);

        // Then
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}