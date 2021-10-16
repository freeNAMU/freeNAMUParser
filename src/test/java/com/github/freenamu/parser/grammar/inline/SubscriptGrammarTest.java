package com.github.freenamu.parser.grammar.inline;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Subscript;
import com.github.freenamu.node.Text;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class SubscriptGrammarTest {
    private SubscriptGrammar subscriptGrammar;

    @Before
    public void setUp() {
        subscriptGrammar = new SubscriptGrammar();
    }

    @Test
    public void should_match_subscript_grammar() {
        // Given
        String rawText = "test1,,test2,,test3";
        int expectedStart = 5;
        int expectedEnd = 14;

        // When
        boolean actualMatch = subscriptGrammar.match(rawText);
        int actualStart = subscriptGrammar.getStart();
        int actualEnd = subscriptGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_not_match_subscript_grammar_with_line_feed() {
        // Given
        String rawText = "test1,,te\nst2,,test3";

        // When
        boolean actualMatch = subscriptGrammar.match(rawText);

        // Then
        assertFalse(actualMatch);
    }

    @Test
    public void should_parse_subscript_grammar() {
        // Given
        String rawText = ",,test,,";
        List<Node> expected = new ArrayList<>();
        expected.add(new Subscript(new Text("test")));

        // When
        subscriptGrammar.match(rawText);
        List<Node> actual = subscriptGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
