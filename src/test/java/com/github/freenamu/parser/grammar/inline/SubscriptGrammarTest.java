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
    public void match_subscript_grammar() {
        // Given
        String rawText = "test1,,test2,,test3";
        Integer expected = 5;

        // When
        Integer actual = subscriptGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void not_match_subscript_grammar_with_line_feed() {
        // Given
        String rawText = "test1,,te\nst2,,test3";

        // When
        Integer actual = subscriptGrammar.getFirstMatchStartIndex(rawText);

        // Then
        assertNull(actual);
    }

    @Test
    public void parse_subscript_grammar() {
        // Given
        String rawText = "test1,,test2,,test3";
        List<Node> expected = new ArrayList<>();
        expected.add(new Text("test1"));
        expected.add(new Subscript(new Text("test2")));
        expected.add(new Text("test3"));

        // When
        List<Node> actual = subscriptGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void parse_subscript_grammar_only() {
        // Given
        String rawText = ",,test1,,";
        List<Node> expected = new ArrayList<>();
        expected.add(new Subscript(new Text("test1")));

        // When
        List<Node> actual = subscriptGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}