package com.github.freenamu.parser.grammar;

import com.github.freenamu.node.Node;
import com.github.freenamu.node.Redirect;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.freenamu.parser.TestUtil.assertNodeListEquals;
import static org.junit.Assert.*;

public class RedirectGrammarTest {
    private RedirectGrammar redirectGrammar;

    @Before
    public void setUp() {
        redirectGrammar = new RedirectGrammar();
    }

    @Test
    public void should_match_1_line_redirect_grammar() {
        // Given
        String rawText = "#redirect test";
        int expectedStart = 0;
        int expectedEnd = 14;

        // When
        boolean actualMatch = redirectGrammar.match(rawText);
        int actualStart = redirectGrammar.getStart();
        int actualEnd = redirectGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_match_more_than_1_line_redirect_grammar() {
        // Given
        String rawText = "#redirect test\ntest";
        int expectedStart = 0;
        int expectedEnd = 14;

        // When
        boolean actualMatch = redirectGrammar.match(rawText);
        int actualStart = redirectGrammar.getStart();
        int actualEnd = redirectGrammar.getEnd();

        // Then
        assertTrue(actualMatch);
        assertEquals(expectedStart, actualStart);
        assertEquals(expectedEnd, actualEnd);
    }

    @Test
    public void should_not_match_redirect_grammar_with_white_space_as_document_name() {
        // Given
        List<String> rawTexts = new ArrayList<>();
        rawTexts.add("#redirect " + " ");
        rawTexts.add("#redirect " + "\t");

        for (String rawText : rawTexts) {
            // When
            boolean actualMatch = redirectGrammar.match(rawText);

            // Then
            assertFalse(actualMatch);
        }
    }

    @Test
    public void should_parse_redirect_grammar() {
        // Given
        String rawText = "#redirect test";
        List<Node> expected = new ArrayList<>();
        expected.add(new Redirect("test"));

        // When
        redirectGrammar.match(rawText);
        List<Node> actual = redirectGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }

    @Test
    public void should_trim_line_feed_at_last() {
        // Given
        String rawText = "#redirect test\n";
        List<Node> expected = new ArrayList<>();
        expected.add(new Redirect("test"));

        // When
        redirectGrammar.match(rawText);
        List<Node> actual = redirectGrammar.parse(rawText);

        // Then
        assertNodeListEquals(expected, actual);
    }
}
